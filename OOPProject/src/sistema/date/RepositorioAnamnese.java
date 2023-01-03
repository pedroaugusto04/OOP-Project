package sistema.date;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import sistema.date.exceptions.AnamneseNaoEncontradaException;
import sistema.date.exceptions.InserirAnamneseException;
import sistema.models.Anamnese;
import sistema.models.Paciente;

public class RepositorioAnamnese implements IRepositorioAnamnese {

    private List<Anamnese> anamneses;
    private long id;
    private static RepositorioAnamnese instance;

    private RepositorioAnamnese() {
        anamneses = new ArrayList<Anamnese>(20);
        id = 1;
    }

    public static RepositorioAnamnese getInstance() {
        if (instance == null) {
            instance = new RepositorioAnamnese();
        }
        return instance;
    }

    @Override
    public void add(Anamnese a)
            throws IllegalArgumentException, InserirAnamneseException {
        if (a == null) {
            throw new IllegalArgumentException("Anamnese não existe");
        } else {
            a.setId(id);
            id++;
            if (!anamneses.add(a)) {
                throw new InserirAnamneseException("Falha ao armazenar");
            } else {
                System.out.println("Anamnese cadastrada com sucesso!");
            }
        }
    }

    @Override
    public List<Anamnese> buscarAnamnesesMesmoNome(String nome) {
        boolean temAnamnese = false;
        List<Anamnese> anamnesesMesmoNome = new ArrayList(20);
        Iterator<Anamnese> iter = anamneses.iterator();
        while (iter.hasNext()) {
            Anamnese a = iter.next();
            if (a.getPaciente().getNome().equals(nome)) {
                temAnamnese = true;
                try {
                    anamnesesMesmoNome.add(a.clone());
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }
        if (temAnamnese) {
            return anamnesesMesmoNome;
        } else {
            return null;
        }
    }

    @Override
    public void alterar(Anamnese anamneseAlterada) throws AnamneseNaoEncontradaException {
        boolean alterou = false;
        if (anamneseAlterada != null) {
            for (int i = 0; i < anamneses.size(); i++) {
                if (anamneses.get(i).equals(anamneseAlterada)) {
                    anamneses.set(i, anamneseAlterada);
                    alterou = true;
                }
            }
            if (!alterou) {
                throw new AnamneseNaoEncontradaException("Anamnese não encontrada.");
            }
        }
    }

    @Override
    public Anamnese buscar(long id) {
        Iterator<Anamnese> iter = anamneses.iterator();
        while (iter.hasNext()) {
            Anamnese a = iter.next();
            if (a.getId() == id) {
                try {
                    return a.clone();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public void excluir(Anamnese a) throws AnamneseNaoEncontradaException {
        boolean excluiu = false;
        if (a != null) {
            Iterator<Anamnese> iter = anamneses.iterator();
            while (iter.hasNext()) {
                if (iter.next().equals(a)) {
                    iter.remove();
                    excluiu = true;
                    System.out.println("Anamnese deletada com sucesso!");
                }
            }
        }
        if (!excluiu) {
            throw new AnamneseNaoEncontradaException("Anamnese não encontrada");
        }
    }

    @Override
    public List<Anamnese> getAnamneses() {
        List<Anamnese> copiaAnamneses = new ArrayList(anamneses.size());
        Iterator<Anamnese> iter = anamneses.iterator();
        while (iter.hasNext()) {
            try {
                copiaAnamneses.add(iter.next().clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return copiaAnamneses;
    }

    @Override
    public boolean verificaExcluir(Paciente p) {
        Iterator<Anamnese> iter = anamneses.iterator();
        while (iter.hasNext()) {
            if (iter.next().getPaciente().equals(p)) {
                return false;
            }
        }
        return true;
    }
}
