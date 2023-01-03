package sistema.date;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import sistema.date.exceptions.InserirPacienteException;
import sistema.date.exceptions.NomeIgualPacienteException;
import sistema.date.exceptions.PacienteNaoEncontradoException;
import sistema.models.Anamnese;
import sistema.models.Paciente;

public class RepositorioPaciente implements IRepositorioPaciente {

    private List<Paciente> pacientes;
    private long numCns;
    private static RepositorioPaciente instance;

    private RepositorioPaciente() {
        pacientes = new ArrayList<Paciente>(20);
        numCns = 1;
    }

    public static RepositorioPaciente getInstance() {
        if (instance == null) {
            instance = new RepositorioPaciente();
        }
        return instance;
    }

    @Override
    public void setarPacienteDaAnamnese(Anamnese a, Paciente p) {
        if (a != null && p != null) {
            Iterator<Paciente> iter = pacientes.iterator();
            while (iter.hasNext()) {
                Paciente pOriginal = iter.next();
                if (pOriginal.equals(p)) {
                    a.setPaciente(pOriginal);
                }
            }
        }
    }

    @Override
    public void add(Paciente p) throws IllegalArgumentException, InserirPacienteException, NomeIgualPacienteException {
        if (p == null) {
            throw new IllegalArgumentException("Paciente não existe");
        } else {
            boolean podeAdicionar = true;
            Iterator<Paciente> iter = pacientes.iterator();
            while (iter.hasNext()) {
                Paciente pOriginal = iter.next();
                if (pOriginal.getNome().equals(p.getNome()) && pOriginal.getNomeMae().equals(p.getNomeMae())) {
                    podeAdicionar = false;
                    throw new NomeIgualPacienteException("Já existe um paciente com o mesmo nome e nome da mãe.");
                }
            }
            if (podeAdicionar) {
                p.setNumCns(numCns);
                numCns++;
                if (!pacientes.add(p)) {
                    throw new InserirPacienteException("Falha ao armazenar");
                } else {
                    System.out.println("Paciente cadastrado com sucesso!");
                }
            }
        }
    }

    @Override
    public Paciente buscar(long numCns) {
        Iterator<Paciente> iter = pacientes.iterator();
        while (iter.hasNext()) {
            Paciente p = iter.next();
            if (p.getNumCns() == numCns) {
                try {
                    return p.clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public boolean alterar(Paciente p) {
        if (p != null) {
            for (int i = 0; i < pacientes.size(); i++) {
                if (pacientes.get(i).equals(p)) {
                    pacientes.set(i, p);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<Paciente> getPacientes() {
        List<Paciente> copiaPacientes = new ArrayList<Paciente>(pacientes.size());
        for (Paciente p : pacientes) {
            try {
                copiaPacientes.add(p.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return copiaPacientes;
    }

    @Override
    public void excluir(Paciente p) throws PacienteNaoEncontradoException {
        boolean excluiu = false;
        Iterator<Paciente> iter = pacientes.iterator();
        while (iter.hasNext()) {
            if (iter.next().equals(p)) {
                iter.remove();
                excluiu = true;
                System.out.println("Paciente deletado com sucesso.");
            }
        }
        if (!excluiu) {
            throw new PacienteNaoEncontradoException("Paciente não encontrado");
        }
    }
}
