package sistema.date;

import java.util.List;
import sistema.date.exceptions.InserirPacienteException;
import sistema.date.exceptions.PacienteNaoEncontradoException;
import sistema.date.exceptions.NomeIgualPacienteException;
import sistema.models.Anamnese;
import sistema.models.Paciente;

public interface IRepositorioPaciente {

    public void add(Paciente p)
            throws IllegalArgumentException, InserirPacienteException, NomeIgualPacienteException;

    public Paciente buscar(long numCNS);

    public boolean alterar(Paciente p)
            throws PacienteNaoEncontradoException;

    public List<Paciente> getPacientes();

    public void excluir(Paciente p)
            throws PacienteNaoEncontradoException;

    public void setarPacienteDaAnamnese(Anamnese a, Paciente p);
}
