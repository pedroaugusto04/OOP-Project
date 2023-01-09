package sistema.models;

import sistema.models.exceptions.FalhaAoAutenticarException;

public class Usuario implements Cloneable, Autenticavel {

    private long id;
    private String nome;
    private String nomeLogin;
    private String senha;
    private Tipo tipo;

    private Usuario(String nome, String nomeLogin, String senha, Tipo tipo) {
        this.nome = nome;
        this.nomeLogin = nomeLogin;
        this.senha = senha;
        this.tipo = tipo;
    }

    public static Usuario getInstance(String nome, String nomeLogin,
            String senha, Tipo tipo) {
        if (nome != null && nomeLogin != null && senha != null
                && tipo != null) {
            return new Usuario(nome, nomeLogin, senha, tipo);
        }
        return null;
    }

    public String getNomeLogin() {
        return this.nomeLogin;
    }

    public String getNome() {
        return this.nome;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Tipo getTipo() {
        return this.tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNomeLogin(String nomeLogin) {
        this.nomeLogin = nomeLogin;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public Usuario clone() throws CloneNotSupportedException {
        return (Usuario) super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Usuario) {
            Usuario u = (Usuario) obj;
            if (id == u.id) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    @Override
    public void autentica(Usuario u) throws FalhaAoAutenticarException, IllegalArgumentException {
        if (u != null) {
            if (!(senha.equals(u.senha))) {
                throw new FalhaAoAutenticarException("Senha incorreta. Tente novamente.");
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
}
