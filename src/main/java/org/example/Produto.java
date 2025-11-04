package org.example;

public abstract class Produto {
    private Long id;
    private String nome;
    private double preco;
    private int  quantidadeEstoque;

    public abstract String getDescricao();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public void darBaixaEstoque(int quantidade) {
        this.quantidadeEstoque -= quantidade;
    }
}
