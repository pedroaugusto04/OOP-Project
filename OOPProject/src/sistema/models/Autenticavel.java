package sistema.models;

import sistema.model.exceptions.FalhaAoAutenticarException;

public interface Autenticavel {

    public void autentica(Usuario u) throws FalhaAoAutenticarException,
            IllegalArgumentException;
}
