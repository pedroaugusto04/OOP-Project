package sistema.models;

public class Anamnese implements Cloneable {

    private long id;
    private Paciente paciente;
    private String motivo;
    private String historico;
    private String queixa;

    private Anamnese(Paciente paciente, String motivo, String historico,
            String queixa) {
        this.motivo = motivo;
        this.historico = historico;
        this.queixa = queixa;
        this.paciente = paciente;
    }

    public static Anamnese getInstance(Paciente paciente, String motivo,
            String historico, String queixa) {
        if (paciente != null && motivo != null && historico != null
                && queixa != null) {
            return new Anamnese(paciente, motivo, historico, queixa);
        }
        return null;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public String getHistorico() {
        return this.historico;
    }

    public String getQueixa() {
        return this.queixa;
    }

    public Paciente getPaciente() {
        return this.paciente;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public void setQueixa(String queixa) {
        this.queixa = queixa;
    }

    @Override
    public Anamnese clone() throws CloneNotSupportedException {
        return (Anamnese) super.clone();
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Anamnese) {
            Anamnese a = (Anamnese) obj;
            if (id == a.id) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
