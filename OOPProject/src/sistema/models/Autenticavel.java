package sistema.models;

import sistema.models.exceptions.FalhaAoAutenticarException;

public interface Autenticavel {

    public void autentica(Usuario u) throws FalhaAoAutenticarException,
            IllegalArgumentException;
}
