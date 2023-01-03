package sistema.controls;

import java.util.List;
import sistema.date.IRepositorioAnamnese;
import sistema.date.IRepositorioPaciente;
import sistema.date.RepositorioPaciente;
import sistema.date.exceptions.AnamneseNaoEncontradaException;
import sistema.date.exceptions.InserirAnamneseException;
import sistema.models.Anamnese;
import sistema.models.Paciente;
import sistema.view.AnamneseView;

public class ControleAnamnese {

    private AnamneseView anamneseView;
    private IRepositorioAnamnese repositorioAnamnese;
    private static ControleAnamnese instance;

    private ControleAnamnese(IRepositorioAnamnese repositorioAnamnese) {
        anamneseView = new AnamneseView();
        this.repositorioAnamnese = repositorioAnamnese;
    }

    public static ControleAnamnese getInstance(IRepositorioAnamnese repositorioAnamnese) {
        if (instance == null) {
            instance = new ControleAnamnese(repositorioAnamnese);
        }
        return instance;
    }

    public void add() {
        IRepositorioPaciente repositorioPaciente = RepositorioPaciente.getInstance();
        ControlePaciente controlePaciente = ControlePaciente.getInstance(repositorioPaciente);
        controlePaciente.listar();
        long numCnsPaciente = anamneseView.lerNumCnsPaciente();
        Paciente p = controlePaciente.buscar(numCnsPaciente);
        if (p != null) {
            Anamnese a = anamneseView.lerAnamnese(p);
            controlePaciente.setarPacienteDaAnamnese(a, p);
            try {
                repositorioAnamnese.add(a);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InserirAnamneseException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Paciente não existe!");
        }
    }

    public void add(Anamnese a) {
        try {
            repositorioAnamnese.add(a);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (InserirAnamneseException e) {
            System.out.println(e.getMessage());
        }
    }

    public void alterar() {
        listar();
        String nomePaciente = anamneseView.getNomePaciente();
        List<Anamnese> anamnesesMesmoNome = repositorioAnamnese.buscarAnamnesesMesmoNome(nomePaciente);
        if (anamnesesMesmoNome != null) {
            Anamnese a = anamneseView.modificarAnamnese(anamnesesMesmoNome);
            if (a != null) {
                try {
                    repositorioAnamnese.alterar(a);
                    System.out.println("Anamnese alterada com sucesso!");
                } catch (AnamneseNaoEncontradaException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Esse paciente não possui anamnese.");
            }
        } else {
            System.out.println("Paciente não existe ou não possui anamnese.");
        }
    }

    public void listar() {
        List<Anamnese> anamneses = repositorioAnamnese.getAnamneses();
        anamneseView.listar(anamneses);
    }

    public void excluir() {
        listar();
        long id = anamneseView.LerIdAnamnese();
        Anamnese a = repositorioAnamnese.buscar(id);
        if (a != null) {
            try {
                repositorioAnamnese.excluir(a);
            } catch (AnamneseNaoEncontradaException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Anamnese não existe.");
        }
    }

    public boolean verificaExcluir(Paciente p) {
        return repositorioAnamnese.verificaExcluir(p);
    }
}
