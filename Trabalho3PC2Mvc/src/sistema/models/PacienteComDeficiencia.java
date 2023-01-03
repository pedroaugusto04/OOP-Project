package sistema.models;

import java.util.Date;

public class PacienteComDeficiencia extends Paciente implements Cloneable {

    private String fatorComplicador;
    private TipoDeficiencia tipoDeficiencia;

    private PacienteComDeficiencia(String fatorComplicador,
            TipoDeficiencia tipoDeficiencia, String nome, String nomeMae, Date dataNasc,
            Sexo sexo, Endereco endereco, String telefone) {
        super(nome, nomeMae, dataNasc, sexo, endereco, telefone);
        this.fatorComplicador = fatorComplicador;
        this.tipoDeficiencia = tipoDeficiencia;
    }

    public static PacienteComDeficiencia getInstance(String fatorComplicador, TipoDeficiencia tipoDeficiencia, String nome, String nomeMae,
            Date dataNasc, Sexo sexo, Endereco endereco, String telefone) {
        if (nome != null && nomeMae != null && dataNasc != null && sexo != null
                && telefone != null && fatorComplicador != null
                && tipoDeficiencia != null) {
            return new PacienteComDeficiencia(fatorComplicador, tipoDeficiencia,
                    nome, nomeMae, dataNasc, sexo, endereco, telefone);
        }
        return null;
    }

    @Override
    public Paciente clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public TipoDeficiencia getTipoDeficiencia() {
        return tipoDeficiencia;
    }

}
