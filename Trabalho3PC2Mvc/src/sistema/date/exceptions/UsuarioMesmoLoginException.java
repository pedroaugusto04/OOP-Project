package sistema.date.exceptions;

public class UsuarioMesmoLoginException extends Exception {

    public UsuarioMesmoLoginException() {
        super();
    }

    public UsuarioMesmoLoginException(String msg) {
        super(msg);
    }
}
