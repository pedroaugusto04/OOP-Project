package sistema.models;

public enum Sexo {
    MASCULINO("M"), FEMININO("F"), INDEFINIDO("I");

    private String sexo;

    public String getSexo() {
        return this.sexo;
    }

    private Sexo(String s) {
        this.sexo = s;
    }
}
