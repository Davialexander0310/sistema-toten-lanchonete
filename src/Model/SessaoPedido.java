package Model;

// Classe responsável por guardar o pedido atual enquanto o cliente navega
public class SessaoPedido {

    private static Pedido pedidoAtual;
    private static String tipoConsumo;
    private static String formaPagamento;

    public static Pedido getPedido() {
        return pedidoAtual;
    }

    public static void setPedido(Pedido pedido) {
        pedidoAtual = pedido;
    }

    public static String getTipoConsumo() {
        return tipoConsumo;
    }

    public static void setTipoConsumo(String tipo) {
        tipoConsumo = tipo;
    }

    public static String getFormaPagamento() {
        return formaPagamento;
    }

    public static void setFormaPagamento(String pagamento) {
        formaPagamento = pagamento;
    }

    public static void novoPedido() {
        pedidoAtual = new Pedido();
        tipoConsumo = null;
        formaPagamento = null;
    }

    public static void limpar() {
        pedidoAtual = null;
        tipoConsumo = null;
        formaPagamento = null;
    }
}