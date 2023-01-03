package sistema.view;

import java.util.Scanner;

public class MenuView {

    private static Scanner sc = new Scanner(System.in);

    public int menuPrincipal() {
        System.out.println("------MENU--------");
        System.out.println("[0] Sair");
        System.out.println("[1] Logar");
        System.out.println("[2] Menu de Usuários");
        System.out.println("-------------------");
        System.out.print("Selecione uma opção: ");
        return sc.nextInt();
    }

    public int menuUsuario() {
        System.out.println("------MENU USUARIO--------");
        System.out.println("[0] Voltar");
        System.out.println("[1] Cadastrar usuário");
        System.out.println("[2] Excluir usuário");
        System.out.println("[3] Alterar usuário");
        System.out.println("[4] Listar usuários");
        System.out.println("-------------------");
        System.out.print("Selecione uma opção: ");
        return sc.nextInt();
    }

    public int menuAtendente() {
        System.out.println("------MENU ATENDENTE--------");
        System.out.println("[0] - Voltar");
        System.out.println("[1] - Cadastrar Paciente");
        System.out.println("[2] - Excluir Paciente");
        System.out.println("[3] - Alterar Paciente");
        System.out.println("[4] - Listar Pacientes");
        System.out.println("[5] - Adicionar endereço");
        System.out.println("[6] - Logout");
        System.out.println("-------------------");
        System.out.print("Selecione uma opção: ");
        return sc.nextInt();
    }

    public int menuMedico() {
        System.out.println("------MENU MEDICO--------");
        System.out.println("[0] - Voltar");
        System.out.println("[1] - Cadastrar Anamnese");
        System.out.println("[2] - Excluir Anamnese");
        System.out.println("[3] - Alterar Anamnese");
        System.out.println("[4] - Listar Anamneses");
        System.out.println("[5] - Logout");
        System.out.println("-------------------");
        System.out.print("Selecione uma opção: ");
        return sc.nextInt();
    }
}
