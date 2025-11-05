package org.example;

import org.example.menus.MenuPessoa;
import org.example.menus.MenuPrincipal;

public class Main {

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
                    System.out.println("Opção 'Contratos' ainda não implementada.");
                    break;
                case 3:
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida.");
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
                    System.out.println("Lógica para LISTAR pessoas...");
                    break;
                case 2:
                    System.out.println("Lógica para LOCALIZAR pessoa...");
                    break;
                case 3:
                    System.out.println("Lógica para CADASTRAR pessoa...");
                    break;
                case 4:
                    System.out.println("Lógica para ATUALIZAR pessoa...");
                    break;
                case 5:
                    executandoPessoa = false;
                    break;
            }
        }
    }
}