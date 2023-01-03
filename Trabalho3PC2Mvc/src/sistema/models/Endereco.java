package sistema.models;

public class Endereco implements Cloneable {

    private String cidade;
    private String logradouro;
    private String uf;
    private int numero;

    public Endereco(String cidade, String logradouro, String uf, int numero) {
        this.cidade = cidade;
        this.logradouro = logradouro;
        this.uf = uf;
        this.numero = numero;
    }

    public String getCidade() {
        return this.cidade;
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    public String getUf() {
        return this.uf;
    }

    public int getNumero() {
        return this.numero;
    }

    @Override
    public Endereco clone() throws CloneNotSupportedException {
        return (Endereco) super.clone();
    }
}
