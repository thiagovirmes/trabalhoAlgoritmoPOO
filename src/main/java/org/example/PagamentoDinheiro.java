package org.example;

public class PagamentoDinheiro implements MetodoPagamento {

    @Override
    public boolean processarPagamento(double valor) {
        // Assume que o caixa está lidando com o dinheiro físico.
        System.out.println("Pagamento em dinheiro de R$" + valor + " recebido.");
        return true; // Para dinheiro, sempre consideramos sucesso.
    }
}