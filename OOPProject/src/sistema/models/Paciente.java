package sistema.models;

import java.util.Date;

public class Paciente implements Cloneable {

    protected long numCns;
    protected String nome;
    protected String nomeMae;
    protected Date dataNasc;
    protected Sexo sexo;
    protected String telefone;
    protected Endereco endereco;

    protected Paciente(String nome, String nomeMae, Date dataNasc, Sexo sexo,
            Endereco endereco, String telefone) {
        this.nome = nome;
        this.nomeMae = nomeMae;
        this.dataNasc = dataNasc;
        switch (sexo.getSexo()) {
            case "M":
                this.sexo = Sexo.MASCULINO;
                break;
            case "F":
                this.sexo = Sexo.FEMININO;
                break;
            case "I":
                this.sexo = Sexo.INDEFINIDO;
                break;
            default:
                break;
        }
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public static Paciente getInstance(String nome, String nomeMae,
            Date dataNasc, Sexo sexo, Endereco endereco, String telefone) {
        if (nome != null && nomeMae != null && dataNasc != null && sexo != null && telefone != null) {
            return new Paciente(nome, nomeMae, dataNasc, sexo, endereco,
                    telefone);
        }
        return null;
    }

    public long getNumCns() {
        return this.numCns;
    }

    public String getNome() {
        return this.nome;
    }

    public String getNomeMae() {
        return this.nomeMae;
    }

    public Date getDataNasc() {
        return this.dataNasc;
    }

    public Sexo getSexo() {
        return this.sexo;
    }

    public Endereco getEndereco() {
        return this.endereco;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void add(Endereco e) {
        this.endereco = e;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setNumCns(long numCns) {
        this.numCns = numCns;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Paciente) {
            Paciente p = (Paciente) obj;
            if (numCns == p.getNumCns()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public Paciente clone() throws CloneNotSupportedException {
        return (Paciente) super.clone();
    }
}
