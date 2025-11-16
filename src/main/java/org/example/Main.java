package org.example;

import org.example.menus.MenuPessoa;
import org.example.menus.MenuPrincipal;
import org.example.menus.MenuContratos;
import org.example.menus.MenuProdutos;
import org.example.utilitarios.Teclado;
import org.example.utilitarios.Video;
import java.util.List;

public class Main {

    private static ClienteController clienteController = new ClienteController();
    private static FuncionarioController funcionarioController = new FuncionarioController();
    private static ProdutoController produtoController = new ProdutoController();
    private static PedidoController pedidoController = new PedidoController();

    public static void main(String[] args) {
        loopMenuPrincipal();
        System.out.println("Obrigado por usar o sistema da padaria!");
    }

    private static void loopMenuPrincipal() {
        boolean executando = true;

        while (executando) {
            int opcao = MenuPrincipal.exibir();
            switch (opcao) {
                case 1:
                    loopMenuPessoa();
                    break;
                case 2:
                    loopMenuVendas();
                    break;
                case 3:
                    executando = false;
                    break;
            }
        }
    }

    private static void loopMenuPessoa() {
        boolean executandoPessoa = true;

        while (executandoPessoa) {
            int opcao = MenuPessoa.exibir();

            switch (opcao) {
                case 1:
                    Video.cabecalho("Listar Pessoas");

                    System.out.println("--- Clientes ---");
                    List<Cliente> clientes = clienteController.listarClientes();
                    for (Cliente cliente : clientes) {
                        System.out.println("ID: " + cliente.getId() + " | Nome: " + cliente.getNome() + " | CPF: " + cliente.getCpf());
                    }
                    if (clientes.isEmpty()) {
                        System.out.println("Nenhum cliente cadastrado.");
                    }

                    System.out.println("\n--- Funcionários ---");
                    List<Funcionario> funcionarios = funcionarioController.listarFuncionarios();
                    for (Funcionario func : funcionarios) {
                        System.out.println("ID: " + func.getId() + " | Nome: " + func.getNome() + " | Cargo: " + func.getCargo());
                    }
                    if (funcionarios.isEmpty()) {
                        System.out.println("Nenhum funcionário cadastrado.");
                    }

                    Video.pausa();
                    break;

                case 2:
                    Video.cabecalho("Remover Pessoa");
                    int tipoRemover = Teclado.readInt("Remover (1) Cliente ou (2) Funcionário? ");

                    if (tipoRemover == 1) {
                        long id = Teclado.readInt("Digite o ID do Cliente para REMOVER: ");
                        Cliente cliente = clienteController.buscarCliente(id);
                        if (cliente != null) {
                            clienteController.removerCliente(id);
                            Video.mensagemOk("Cliente '" + cliente.getNome() + "' removido com sucesso!");
                        } else {
                            Video.mensagemErro("Cliente com ID " + id + " não encontrado.");
                        }

                    } else if (tipoRemover == 2) {
                        long id = Teclado.readInt("Digite o ID do Funcionário para REMOVER: ");
                        Funcionario func = funcionarioController.buscarFuncionario(id);
                        if (func != null) {
                            funcionarioController.removerFuncionario(id);
                            Video.mensagemOk("Funcionário '" + func.getNome() + "' removido com sucesso!");
                        } else {
                            Video.mensagemErro("Funcionário com ID " + id + " não encontrado.");
                        }
                    } else {
                        Video.mensagemErro("Opção inválida.");
                    }
                    Video.pausa();
                    break;

                case 3:
                    Video.cabecalho("Cadastrar Pessoa");
                    int tipoCadastrar = Teclado.readInt("Cadastrar (1) Cliente ou (2) Funcionário? ");

                    if (tipoCadastrar == 1) {
                        String nome = Teclado.readString("Nome do Cliente: ");
                        String cpf = Teclado.readString("CPF do Cliente: ");
                        clienteController.criarCliente(nome, cpf);
                        Video.mensagemOk("Cliente salvo com sucesso!");

                    } else if (tipoCadastrar == 2) {
                        String nome = Teclado.readString("Nome do Funcionário: ");
                        String cargo = Teclado.readString("Cargo do Funcionário: ");
                        funcionarioController.criarFuncionario(nome, cargo);
                        Video.mensagemOk("Funcionário salvo com sucesso!");

                    } else {
                        Video.mensagemErro("Opção inválida.");
                    }
                    Video.pausa();
                    break;

                case 4:
                    Video.cabecalho("Atualizar Pessoa");
                    int tipoAtualizar = Teclado.readInt("Atualizar (1) Cliente ou (2) Funcionário? ");

                    if (tipoAtualizar == 1) {
                        long id = Teclado.readInt("Digite o ID do Cliente para atualizar: ");
                        Cliente cliente = clienteController.buscarCliente(id);

                        if (cliente != null) {
                            System.out.println("Dados atuais: Nome: " + cliente.getNome() + " | CPF: " + cliente.getCpf());
                            String novoNome = Teclado.readString("Novo Nome: ");
                            String novoCpf = Teclado.readString("Novo CPF: ");

                            clienteController.atualizarCliente(id, novoNome, novoCpf);
                            Video.mensagemOk("Cliente atualizado com sucesso!");
                        } else {
                            Video.mensagemErro("Cliente com ID " + id + " não encontrado.");
                        }

                    } else if (tipoAtualizar == 2) {
                        long id = Teclado.readInt("Digite o ID do Funcionário para atualizar: ");
                        Funcionario func = funcionarioController.buscarFuncionario(id);

                        if (func != null) {
                            System.out.println("Dados atuais: Nome: " + func.getNome() + " | Cargo: " + func.getCargo());
                            String novoNome = Teclado.readString("Novo Nome: ");
                            String novoCargo = Teclado.readString("Novo Cargo: ");

                            funcionarioController.atualizarFuncionario(id, novoNome, novoCargo);
                            Video.mensagemOk("Funcionário atualizado com sucesso!");
                        } else {
                            Video.mensagemErro("Funcionário com ID " + id + " não encontrado.");
                        }
                    } else {
                        Video.mensagemErro("Opção inválida.");
                    }
                    Video.pausa();
                    break;

                case 5:
                    executandoPessoa = false;
                    break;
            }
        }
    }

    private static void loopMenuVendas() {
        boolean executando = true;
        while (executando) {
            int opcao = MenuContratos.exibir();
            switch (opcao) {
                case 1:
                    registrarNovaVenda();
                    break;
                case 2:
                    listarVendas();
                    break;
                case 3:
                    loopMenuProdutos();
                    break;
                case 4:
                    executando = false;
                    break;
            }
        }
    }

    private static void registrarNovaVenda() {
        Video.cabecalho("Registrar Nova Venda");

        System.out.println("--- Clientes Disponíveis ---");
        List<Cliente> clientes = clienteController.listarClientes();
        if (clientes.isEmpty()) {
            Video.mensagemErro("Nenhum cliente cadastrado. Cadastre um cliente primeiro.");
            Video.pausa();
            return;
        }
        clientes.forEach(c -> System.out.println("ID: " + c.getId() + " | Nome: " + c.getNome()));
        long clienteId = Teclado.readInt("Digite o ID do Cliente: ");

        long funcId = Teclado.readInt("Digite o ID do Funcionário (ou 0 se não houver): ");

        Pedido novoPedido = pedidoController.iniciarNovoPedido(clienteId, (funcId == 0) ? null : funcId);
        if (novoPedido == null) {
            Video.mensagemErro("Não foi possível iniciar o pedido (Cliente não encontrado?).");
            Video.pausa();
            return;
        }

        boolean adicionandoItens = true;
        while (adicionandoItens) {
            System.out.println("\n--- Produtos Disponíveis ---");
            List<Produto> produtos = produtoController.listarProdutos();
            if (produtos.isEmpty()) {
                Video.mensagemErro("Nenhum produto cadastrado. Adicione produtos no menu 'Gerenciar Produtos'.");
                adicionandoItens = false;
                continue;
            }
            for (Produto p : produtos) {
                System.out.println("ID: " + p.getId() + " | " + p.getNome() + " | R$ " + p.getPreco() + " | Estoque: " + p.getQuantidadeEstoque());
            }

            long produtoId = Teclado.readInt("\nDigite o ID do Produto (ou 0 para finalizar): ");
            if (produtoId == 0) {
                adicionandoItens = false;
                continue;
            }

            int qtd = Teclado.readInt("Digite a Quantidade: ");

            boolean sucessoAdd = pedidoController.adicionarItemAoPedido(novoPedido, produtoId, qtd);
            if (sucessoAdd) {
                Video.mensagemOk("Item adicionado!");
            } else {
                Video.mensagemErro("Erro ao adicionar item (Produto não encontrado ou estoque insuficiente).");
            }
        }

        if (novoPedido.getItens().isEmpty()) {
            Video.mensagemAlerta("Pedido cancelado (nenhum item adicionado).");
            Video.pausa();
            return;
        }

        System.out.println("\nValor Total do Pedido: R$" + novoPedido.getValorTotal());
        int tipoPgto = Teclado.readInt("Forma de Pagamento: (1) Dinheiro (2) PIX: ");

        MetodoPagamento metodo;
        if (tipoPgto == 1) {
            metodo = new PagamentoDinheiro();
        } else if (tipoPgto == 2) {
            metodo = new PagamentoPix();
        } else {
            Video.mensagemErro("Pagamento cancelado. Pedido não salvo.");
            Video.pausa();
            return;
        }

        boolean sucessoFinal = pedidoController.finalizarPedido(novoPedido, metodo);
        if (sucessoFinal) {
            Video.mensagemOk("Venda registrada e salva no banco!");
        } else {
            Video.mensagemErro("Falha no pagamento. A venda não foi registrada.");
        }
        Video.pausa();
    }

    private static void listarVendas() {
        Video.cabecalho("Listar Vendas Registradas");
        List<Pedido> pedidos = pedidoController.listarPedidos();

        if (pedidos.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
        }

        for (Pedido pedido : pedidos) {
            System.out.println(Video.NEGRITO + "\n--- Pedido ID: " + pedido.getId() + " | Data: " + pedido.getDataPedido() + Video.RESET);
            System.out.println("Cliente: " + pedido.getCliente().getNome());

            if (pedido.getItens() != null && !pedido.getItens().isEmpty()) {
                System.out.println("Itens:");
                for (ItemPedido item : pedido.getItens()) {
                    System.out.println("  - " + item.getProduto().getNome() + " (Qtd: " + item.getQuantidade() + ")");
                }
            } else {
                System.out.println("Itens: (Informação não carregada)");
            }

            System.out.println("Total: R$" + pedido.getValorTotal() + " | Pago: " + pedido.isPago());
        }
        Video.pausa();
    }

    private static void loopMenuProdutos() {
        boolean executando = true;
        while(executando) {
            int opcao = MenuProdutos.exibir();
            switch(opcao) {
                case 1:
                    Video.cabecalho("Listar Produtos");
                    List<Produto> produtos = produtoController.listarProdutos();
                    if (produtos.isEmpty()) System.out.println("Nenhum produto cadastrado.");
                    for (Produto p : produtos) {
                        String tipo = (p instanceof Pao) ? "Pão" : (p instanceof Bolo) ? "Bolo" : "Produto";
                        System.out.println("ID: " + p.getId() + " | Tipo: " + tipo + " | Nome: " + p.getNome() + " | R$: " + p.getPreco() + " | Estoque: " + p.getQuantidadeEstoque());
                    }
                    Video.pausa();
                    break;
                case 2:
                    Video.cabecalho("Adicionar Produto");
                    int tipo = Teclado.readInt("Tipo (1) Pão (2) Bolo: ");
                    String nome = Teclado.readString("Nome: ");
                    double preco = Teclado.readDouble("Preço (ex: 4.50): ");
                    int estoque = Teclado.readInt("Estoque Inicial: ");

                    Produto novoProduto;
                    if (tipo == 1) {
                        novoProduto = new Pao();
                    } else if (tipo == 2) {
                        novoProduto = new Bolo();
                    } else {
                        Video.mensagemErro("Tipo inválido.");
                        break;
                    }
                    novoProduto.setNome(nome);
                    novoProduto.setPreco(preco);
                    novoProduto.setQuantidadeEstoque(estoque);

                    produtoController.salvarProduto(novoProduto);
                    Video.mensagemOk("Produto salvo!");
                    Video.pausa();
                    break;
                case 3:
                    Video.cabecalho("Atualizar Estoque/Preço");
                    long idAtt = Teclado.readInt("ID do Produto: ");
                    Produto pAtt = produtoController.buscarProduto(idAtt);
                    if (pAtt == null) {
                        Video.mensagemErro("Produto não encontrado.");
                        break;
                    }
                    System.out.println("Atualizando: " + pAtt.getNome());
                    double novoPreco = Teclado.readDouble("Novo Preço (Atual: " + pAtt.getPreco() + "): ");
                    int novoEstoque = Teclado.readInt("Novo Estoque (Atual: " + pAtt.getQuantidadeEstoque() + "): ");

                    pAtt.setPreco(novoPreco);
                    pAtt.setQuantidadeEstoque(novoEstoque);

                    produtoController.atualizarProduto(pAtt);
                    Video.mensagemOk("Produto atualizado!");
                    Video.pausa();
                    break;
                case 4:
                    Video.cabecalho("Remover Produto");
                    long idRem = Teclado.readInt("ID do Produto a remover: ");
                    Produto pRem = produtoController.buscarProduto(idRem);
                    if (pRem == null) {
                        Video.mensagemErro("Produto não encontrado.");
                        break;
                    }
                    produtoController.removerProduto(idRem);
                    Video.mensagemOk("Produto '" + pRem.getNome() + "' removido!");
                    Video.pausa();
                    break;
                case 5:
                    executando = false;
                    break;
            }
        }
    }
}