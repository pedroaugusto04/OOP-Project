package sistema.controls;

import java.util.List;
import sistema.date.IRepositorioAnamnese;
import sistema.date.IRepositorioPaciente;
import sistema.date.RepositorioAnamnese;
import sistema.date.exceptions.InserirPacienteException;
import sistema.date.exceptions.NomeIgualPacienteException;
import sistema.date.exceptions.PacienteNaoEncontradoException;
import sistema.models.Anamnese;
import sistema.models.Paciente;
import sistema.view.PacienteView;

public class ControlePaciente {

    private PacienteView pacienteView;
    private IRepositorioPaciente repositorioPaciente;
    private static ControlePaciente instance;

    private ControlePaciente(IRepositorioPaciente repositorioPaciente) {
        pacienteView = new PacienteView();
        this.repositorioPaciente = repositorioPaciente;
    }

    public static ControlePaciente getInstance(IRepositorioPaciente repositorioPaciente) {
        if (instance == null) {
            instance = new ControlePaciente(repositorioPaciente);
        }
        return instance;
    }

    public void add() {
        Paciente p = pacienteView.lerPaciente();
        try {
            repositorioPaciente.add(p);
        } catch (NomeIgualPacienteException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (InserirPacienteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void add(Paciente p) {
        try {
            repositorioPaciente.add(p);
        } catch (NomeIgualPacienteException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (InserirPacienteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void alterar() {
        System.out.println("------MENU ALTERACAO--------");
        listar();
        long numCns = pacienteView.lerNumCnsPaciente();
        Paciente p = repositorioPaciente.buscar(numCns);
        if (p != null) {
            Paciente pacienteAlterado = pacienteView.alterar(p);
            try {
                repositorioPaciente.alterar(pacienteAlterado);
                System.out.println("Paciente alterado com sucesso.");
            } catch (PacienteNaoEncontradoException e) {
                System.out.println("Falha em alterar.");
            }
        } else {
            System.out.println("Paciente não encontrado.");
        }
    }

    public void listar() {
        List<Paciente> pacientes = repositorioPaciente.getPacientes();
        pacienteView.mostrarPacientes(pacientes);
    }

    public void excluir() {
        listar();
        long numCns = pacienteView.lerNumCnsPaciente();
        Paciente p = repositorioPaciente.buscar(numCns);
        if (p != null) {
            IRepositorioAnamnese repositorioAnamnese = RepositorioAnamnese.getInstance();
            if (ControleAnamnese.getInstance(repositorioAnamnese).verificaExcluir(p)) {
                try {
                    repositorioPaciente.excluir(p);
                } catch (PacienteNaoEncontradoException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Paciente possui anamnese e não pode ser excluído.");
            }
        } else {
            System.out.println("Paciente não existe!");
        }
    }

    public void adicionarEndereco() {
        listar();
        long numCns = pacienteView.lerNumCnsPaciente();
        Paciente p = repositorioPaciente.buscar(numCns);
        if (p != null && p.getEndereco() == null) {
            Paciente pacienteAlterado = pacienteView.alterarEndereco(p);
            try {
                repositorioPaciente.alterar(pacienteAlterado);
            } catch (PacienteNaoEncontradoException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Paciente não existe ou já possui endereco!");
        }
    }

    public void setarPacienteDaAnamnese(Anamnese a, Paciente p) {
        repositorioPaciente.setarPacienteDaAnamnese(a, p);
    }

    public Paciente buscar(long numCNS) {
        return repositorioPaciente.buscar(numCNS);
    }
}
