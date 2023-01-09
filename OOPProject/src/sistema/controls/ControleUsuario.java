package sistema.controls;

import java.util.List;
import main.Sistema;
import sistema.date.IRepositorioUsuario;
import sistema.date.exceptions.InserirUsuarioException;
import sistema.date.exceptions.LoginNaoEncontradoException;
import sistema.date.exceptions.UsuarioMesmoLoginException;
import sistema.date.exceptions.UsuarioNaoEncontradoException;
import sistema.models.exceptions.FalhaAoAutenticarException;
import sistema.models.Usuario;
import sistema.view.MenuView;
import sistema.view.UsuarioView;

public class ControleUsuario {

    private UsuarioView usuarioView;
    private IRepositorioUsuario repositorioUsuario;
    private static ControleUsuario instance;

    private ControleUsuario(IRepositorioUsuario repositorioUsuario) {
        usuarioView = new UsuarioView();
        this.repositorioUsuario = repositorioUsuario;
    }

    public static ControleUsuario getInstance(IRepositorioUsuario repositorioUsuario) {
        if (instance == null) {
            instance = new ControleUsuario(repositorioUsuario);
        }
        return instance;
    }

    public void add() {
        Usuario u = usuarioView.lerUsuario();
        try {
            repositorioUsuario.add(u);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (InserirUsuarioException e) {
            System.out.println(e.getMessage());
        } catch (UsuarioMesmoLoginException e) {
            System.out.println(e.getMessage());
        }
    }

    public void add(Usuario u) {
        try {
            repositorioUsuario.add(u);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (InserirUsuarioException e) {
            System.out.println(e.getMessage());
        } catch (UsuarioMesmoLoginException e) {
            System.out.println(e.getMessage());
        }
    }

    public void alterar() {
        listar();
        long id = usuarioView.getIdUsuario();
        Usuario u = repositorioUsuario.buscar(id);
        if (u != null) {
            Usuario usuarioAlterado = usuarioView.alterar(u);
            try {
                repositorioUsuario.alterar(usuarioAlterado);
                System.out.println("Usuário alterado com sucesso!");
            } catch (UsuarioNaoEncontradoException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public void excluir() {
        listar();
        long idUsuario = usuarioView.getIdUsuario();
        try {
            repositorioUsuario.excluir(idUsuario);
        } catch (UsuarioNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }

    }

    public void listar() {
        List<Usuario> usuarios = repositorioUsuario.getUsuarios();
        usuarioView.listar(usuarios);
    }

    public Usuario logar() {
        Usuario uLogin;
        Usuario uClone = null;
        boolean autenticou;
        MenuView menuView = new MenuView();
        int opcaoLogin;
        do {
            autenticou = true;
            do {
                opcaoLogin = menuView.menuLogin();
                if (opcaoLogin == 0) {
                    Sistema sistema = Sistema.getInstance();
                    sistema.iniciarMenuPrincipal();
                } else if (opcaoLogin == 1) {
                    break;
                }
            } while (opcaoLogin != 0 && opcaoLogin != 1);

            uLogin = usuarioView.dadosLogin();
            try {
                uClone = repositorioUsuario.buscarUsuarioMesmoLogin(uLogin);
            } catch (LoginNaoEncontradoException e) {
                System.out.println(e.getMessage());
                autenticou = false;
                continue;
            }
            try {
                uLogin.autentica(uClone);
            } catch (FalhaAoAutenticarException e) {
                System.out.println(e.getMessage());
                autenticou = false;
            } catch (IllegalArgumentException e) {
                autenticou = false;
            }
        } while (!autenticou);
        System.out.println("Logou com sucesso!");
        return uClone;
    }

    public Usuario logout(Usuario uLogin) {
        if (uLogin != null) {
            uLogin = null;
            System.out.println("Deslogou com sucesso!");
            uLogin = logar();
        } else {
            System.out.println("Você precisa estar logado para deslogar!");
        }
        return uLogin;
    }

}
