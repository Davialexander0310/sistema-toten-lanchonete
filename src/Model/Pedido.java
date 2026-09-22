package Model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int id;
    private String tipoConsumo;      // "LOCAL" ou "LEVAR"
    private String formaPagamento;   // "PIX", "CARTAO", "DINHEIRO"
    private List<Produto> itens;
    private String senha;

    public Pedido() {
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(Produto p) {
        itens.add(p);
    }

    public void removerTodosItens() {
        itens.clear();
    }

    public double calcularTotal() {
        double total = 0;
        for (Produto p : itens) {
            total += p.getPreco();
        }
        return total;
    }

    // Getters e Setters
    public int getId()                      { return id; }
    public void setId(int id)               { this.id = id; }

    public String getTipoConsumo()          { return tipoConsumo; }
    public void setTipoConsumo(String t)    { this.tipoConsumo = t; }

    public String getFormaPagamento()       { return formaPagamento; }
    public void setFormaPagamento(String f) { this.formaPagamento = f; }

    public List<Produto> getItens()         { return itens; }

    public String getSenha()                { return senha; }
    public void setSenha(String senha)      { this.senha = senha; }
}