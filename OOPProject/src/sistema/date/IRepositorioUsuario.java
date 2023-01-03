package sistema.date;

import java.util.List;

import sistema.date.exceptions.InserirUsuarioException;
import sistema.date.exceptions.LoginNaoEncontradoException;
import sistema.date.exceptions.UsuarioMesmoLoginException;
import sistema.date.exceptions.UsuarioNaoEncontradoException;
import sistema.models.Usuario;

public interface IRepositorioUsuario {

    public void add(Usuario u)
            throws IllegalArgumentException, InserirUsuarioException, 
            UsuarioMesmoLoginException;

    public List<Usuario> getUsuarios();

    public void excluir(long id)
            throws UsuarioNaoEncontradoException;

    public Usuario buscar(long idUsuario);

    public void alterar(Usuario usuarioAlt)
            throws UsuarioNaoEncontradoException;

    public Usuario buscarUsuarioMesmoLogin(Usuario u) throws LoginNaoEncontradoException;

}
