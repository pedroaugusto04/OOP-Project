package sistema.date;

import java.util.List;
import sistema.date.exceptions.AnamneseNaoEncontradaException;
import sistema.date.exceptions.InserirAnamneseException;
import sistema.models.Anamnese;
import sistema.models.Paciente;

public interface IRepositorioAnamnese {

    public void add(Anamnese a)
            throws IllegalArgumentException, InserirAnamneseException;

    public List<Anamnese> buscarAnamnesesMesmoNome(String nome);

    public void alterar(Anamnese a)
            throws AnamneseNaoEncontradaException;

    public Anamnese buscar(long id);

    public void excluir(Anamnese a)
            throws AnamneseNaoEncontradaException;

    public List<Anamnese> getAnamneses();

    public boolean verificaExcluir(Paciente p);

}
