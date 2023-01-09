package sistema.date;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import sistema.date.exceptions.InserirUsuarioException;
import sistema.date.exceptions.LoginNaoEncontradoException;
import sistema.date.exceptions.UsuarioMesmoLoginException;
import sistema.date.exceptions.UsuarioNaoEncontradoException;
import sistema.models.Usuario;

public class RepositorioUsuario implements IRepositorioUsuario {

    private List<Usuario> usuarios;
    private long id;
    private static RepositorioUsuario instance;

    private RepositorioUsuario() {
        usuarios = new ArrayList<Usuario>(20);
        id = 1;
    }

    public static RepositorioUsuario getInstance() {
        if (instance == null) {
            instance = new RepositorioUsuario();
        }
        return instance;
    }

    @Override
    public void add(Usuario u)
            throws IllegalArgumentException, InserirUsuarioException,
            UsuarioMesmoLoginException {
        if (u == null) {
            throw new IllegalArgumentException("Usuário não existe");
        } else {
            for (Usuario uAux : usuarios) {
                if (uAux.getNomeLogin().equals(u.getNomeLogin())) {
                    throw new UsuarioMesmoLoginException("Erro. Login já cadastrado.");
                }
            }
            u.setId(id);
            id++;
            if (!usuarios.add(u)) {
                throw new InserirUsuarioException("Falha ao armazenar");
            } else {
                System.out.println("Usuário cadastrado com sucesso!");
            }
        }
    }

    @Override
    public List<Usuario> getUsuarios() {
        List<Usuario> copiaUsuarios = new ArrayList<Usuario>(usuarios.size());
        for (Usuario u : usuarios) {
            try {
                copiaUsuarios.add(u.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return copiaUsuarios;
    }

    @Override
    public void excluir(long id) throws UsuarioNaoEncontradoException {
        boolean excluiu = false;
        Iterator<Usuario> iter = usuarios.iterator();
        while (iter.hasNext()) {
            if (iter.next().getId() == id) {
                iter.remove();
                excluiu = true;
                System.out.println("Usuário deletado com sucesso!");
            }
        }
        if (!excluiu) {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado");
        }
    }

    @Override
    public Usuario buscar(long id) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                try {
                    return u.clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public void alterar(Usuario usuarioAlterado)
            throws UsuarioNaoEncontradoException {
        boolean encontrouUsuario = false;
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i) != null) {
                if (usuarios.get(i).equals(usuarioAlterado)) {
                    usuarios.set(i, usuarioAlterado);
                    encontrouUsuario = true;
                }
            }
        }
        if (!encontrouUsuario){
            throw new UsuarioNaoEncontradoException("Usuario não encontrado.");    
        }
    }

    @Override
    public Usuario buscarUsuarioMesmoLogin(Usuario uLogin) throws LoginNaoEncontradoException {
        List<Usuario> copiaUsuarios = getUsuarios();
        Iterator<Usuario> iter = copiaUsuarios.iterator();
        while (iter.hasNext()) {
            Usuario u = iter.next();
            if (u.getNomeLogin().equals(uLogin.getNomeLogin())) {
                try {
                    return u.clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }
        throw new LoginNaoEncontradoException("Login não cadastrado!");
    }
}
