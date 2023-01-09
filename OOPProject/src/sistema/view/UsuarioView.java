package sistema.view;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import sistema.models.Tipo;
import sistema.models.Usuario;

public class UsuarioView {

    private Scanner sc;

    public UsuarioView() {
        sc = new Scanner(System.in);
    }

    public Usuario lerUsuario() {
        System.out.println("-----Cadastrando Usuário-----");
        System.out.print("Nome: ");
        String nome = sc.next();
        sc.nextLine();
        System.out.print("Nome Login: ");
        String nomeLogin = sc.next();
        sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.next();
        sc.nextLine();
        Tipo tipo = Tipo.INDEFINIDO;
        System.out.print("Tipo: ");
        try {
            String tipoString = sc.next().toUpperCase();
            sc.nextLine();
            if (tipoString.equals("ATENDENTE")) {
                tipo = Tipo.ATENDENTE;
            } else if (tipoString.equals("MEDICO")) {
                tipo = Tipo.MEDICO;
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            sc.nextLine();
        }
        Usuario u = Usuario.getInstance(nome, nomeLogin, senha, tipo);
        return u;

    }

    public static void listar(List<Usuario> usuarios) {
        String leftAlignFormat = "| %-7s | %-13s | %-14s | %-11s |%n";
        System.out.format("+---------+---------------+----------------+-------------+%n");
        System.out.format("| ID      | NOME          | NOME_LOGIN     | TIPO        |%n");
        System.out.format("+---------+---------------+----------------+-------------+%n");
        Iterator<Usuario> iter1 = usuarios.iterator();
        while (iter1.hasNext()) {
            Usuario u = iter1.next();
            int idUsuarioInt = Math.toIntExact(u.getId());
            System.out.format(leftAlignFormat, String.format("%1.7s", idUsuarioInt), String.format("%1.13s", u.getNome()), String.format("%1.14s", u.getNomeLogin()), String.format("%1.11s", u.getTipo()));
            System.out.format("+---------+---------------+----------------+-------------+%n");
        }
    }

    public long getIdUsuario() {
        System.out.print("Digite o ID do usuário: ");
        boolean confirmacaoId = true;
        long id = 0;
        while (confirmacaoId) {
            try {
                id = sc.nextLong();
                confirmacaoId = false;
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Digite um ID válido");
            }
        }
        return id;
    }

    public Usuario alterar(Usuario u) {
        Usuario uAlterado = null;
        if (u != null) {
            try {
                uAlterado = u.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            int opcao;
            do {
                System.out.println("-------------------------------");
                System.out.println("0 - Sair do menu de alteração");
                System.out.println("1 - Alterar Nome");
                System.out.println("2 - Alterar Login ");
                System.out.println("3 - Alterar Senha ");
                System.out.println("4 - Alterar tipo");
                System.out.println("-------------------------------");
                opcao = sc.nextInt();
                sc.nextLine();
                switch (opcao) {
                    case 0:
                        break;
                    case 1:
                        System.out.println("Digite o novo nome do Usuário: ");
                        uAlterado.setNome(sc.nextLine());
                        System.out.println("Nome alterado com sucesso!");
                        break;
                    case 2:
                        System.out.println("Digite o novo login do Usuário: ");
                        uAlterado.setNomeLogin(sc.nextLine());
                        System.out.println("Login alterado com sucesso!");
                        break;
                    case 3:
                        System.out.println("Digite a nova senha do Usuário: ");
                        uAlterado.setSenha(sc.nextLine());
                        System.out.println("Senha alterada com sucesso!");
                        break;
                    case 4:
                        System.out.println("Digite o novo tipo do Usuário: ");
                        Tipo tipo = Tipo.INDEFINIDO;
                        try {
                            String tipoString = sc.next().toUpperCase();
                            sc.nextLine();
                            if (tipoString.equals("ATENDENTE")) {
                                tipo = Tipo.ATENDENTE;
                            } else if (tipoString.equals("MEDICO")) {
                                tipo = Tipo.MEDICO;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println(e.getMessage());
                            sc.nextLine();
                        }
                        uAlterado.setTipo(tipo);
                        System.out.println("Tipo alterado com sucesso!");
                        break;
                    default:
                        System.out.println("Opção inválida");
                }
            } while (opcao != 0);
        } else {
            System.out.println("Usuário não existe!");
        }
        return uAlterado;
    }

    public Usuario dadosLogin() {
        String login, senha;
        System.out.println("Faça o login para continuar: ");
        System.out.print("Login: ");
        login = sc.next();
        sc.nextLine();
        System.out.print("Senha: ");
        senha = sc.next();
        sc.nextLine();
        Usuario uLogin = Usuario.getInstance("", login, senha, Tipo.INDEFINIDO);
        return uLogin;
    }

}
