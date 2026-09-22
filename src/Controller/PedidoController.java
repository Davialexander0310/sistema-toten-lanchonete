package Controller;

import Model.Pedido;
import Model.Produto;
import java.sql.*;

public class PedidoController {

    // Salva o pedido no banco e retorna o ID gerado
    public int salvarPedido(Pedido pedido) {
        String sqlPedido = "INSERT INTO pedido (tipo_consumo, forma_pagamento, total, senha, status) VALUES (?,?,?,?,'CONFIRMADO')";
    String sqlItem   = "INSERT INTO item_pedido (pedido_id, produto_id) VALUES (?,?)";

    try (Connection conn = ConexaoDB.getConexao()) {
        conn.setAutoCommit(false);

        // Garante valores padrão se vier nulo
        String tipo = pedido.getTipoConsumo() != null ? pedido.getTipoConsumo() : "LOCAL";
        String pagamento = pedido.getFormaPagamento() != null ? pedido.getFormaPagamento() : "DINHEIRO";

        PreparedStatement psPedido = conn.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS);
        psPedido.setString(1, tipo);
        psPedido.setString(2, pagamento);
        psPedido.setDouble(3, pedido.calcularTotal());
        psPedido.setString(4, pedido.getSenha());
        psPedido.executeUpdate();

        ResultSet rs = psPedido.getGeneratedKeys();
        int pedidoId = 0;
        if (rs.next()) {
            pedidoId = rs.getInt(1);
            pedido.setId(pedidoId);
        }

        PreparedStatement psItem = conn.prepareStatement(sqlItem);
        for (Produto p : pedido.getItens()) {
            psItem.setInt(1, pedidoId);
            psItem.setInt(2, p.getId());
            psItem.addBatch();
        }
        psItem.executeBatch();

        conn.commit();
        return pedidoId;

    } catch (SQLException e) {
        e.printStackTrace();
        return -1;
    }
    }

    // Gera uma senha sequencial simples (ex: #001, #002...)
    public String gerarSenha() {
        String sql = "SELECT COUNT(*) FROM pedido WHERE DATE(criado_em) = CURDATE()";
        try (Connection conn = ConexaoDB.getConexao();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) {
                int numero = rs.getInt(1) + 1;
                return String.format("#%03d", numero);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "#001";
    }
}