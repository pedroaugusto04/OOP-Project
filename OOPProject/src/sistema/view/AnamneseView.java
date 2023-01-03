package sistema.view;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import sistema.models.Anamnese;
import sistema.models.Paciente;

public class AnamneseView {

    private Scanner sc;

    public AnamneseView() {
        sc = new Scanner(System.in);
    }

    public long lerNumCnsPaciente() {
        System.out.print("Digite o numCNS do paciente: ");
        long numCns = sc.nextLong();
        sc.nextLine();
        return numCns;
    }

    public Anamnese lerAnamnese(Paciente p) {
        System.out.print("Digite o motivo da Anamnese: ");
        String motivo = sc.nextLine();
        System.out.print("Historico: ");
        String historico = sc.nextLine();
        System.out.print("Queixa: ");
        String queixa = sc.nextLine();
        Anamnese a = Anamnese.getInstance(p, motivo, historico, queixa);
        return a;
    }

    public String getNomePaciente() {
        System.out.print("Digite o nome do paciente que terá a Anamnese alterada: ");
        String nome = sc.nextLine();
        return nome;
    }

    public Anamnese modificarAnamnese(List<Anamnese> anamnesesMesmoNome) {
        int j = 0;
        int opcaoId;
        boolean encontrouId = false;
        if (anamnesesMesmoNome != null) {
            System.out.println("Anamneses encontradas:");
            String leftAlignFormat = "| %-7s | %-13s | %-13s | %-21s |%n";

            System.out.format("+---------+---------------+---------------+-----------------------+%n");
            System.out.format("|    ID   |    PACIENTE   |      MÃE      |         MOTIVO        |%n");
            System.out.format("+---------+---------------+---------------+-----------------------+%n");
            Iterator<Anamnese> iter = anamnesesMesmoNome.iterator();
            while (iter.hasNext()) {
                Anamnese a = iter.next();
                String idAnamneseString = String.valueOf(a.getId());
                System.out.format(leftAlignFormat, String.format("%1.7s", idAnamneseString), String.format("%1.13s", a.getPaciente().getNome()),
                        String.format("%1.13s", a.getPaciente().getNomeMae()), String.format("%1.21s", a.getMotivo()));
                System.out.format("+---------+---------------+---------------+-----------------------+%n");

            }
            System.out.println("Digite o ID da Anamnese que deseja alterar: ");
            opcaoId = sc.nextInt();
            sc.nextLine();
            iter = anamnesesMesmoNome.iterator();
            while (iter.hasNext()) {
                Anamnese a = iter.next();
                if (a.getId() == opcaoId) {
                    encontrouId = true;
                    break;
                }
            }
            if (!encontrouId) {
                System.out.println("ID não encontrado!");
                return null;
            }
            System.out.println("Digite o novo motivo:");
            anamnesesMesmoNome.get(j).setMotivo(sc.nextLine());
            System.out.println("Motivo alterado com sucesso!");
            System.out.println("Digite o novo historico:");
            anamnesesMesmoNome.get(j).setHistorico(sc.nextLine());
            System.out.println("Historico alterado com sucesso!");
            System.out.println("Digite a nova queixa:");
            anamnesesMesmoNome.get(j).setQueixa(sc.nextLine());
            System.out.println("Queixa alterada com sucesso!");
            return anamnesesMesmoNome.get(j);
        } else {
            return null;
        }
    }

    public long LerIdAnamnese() {
        System.out.print("Digite o ID da Anamnese que deseja excluir: ");
        long id = sc.nextLong();
        sc.nextLine();
        return id;
    }

    public static void listar(List<Anamnese> anamneses) {
        String leftAlignFormat = "| %-2s | %-17s | %-100s |%n";
        System.out.format("+----+-------------------+------------------------------------------------------------------------------------------------------+%n");
        System.out.format("| ID | PACIENTE          | MOTIVO                                                                                               |%n");
        System.out.format("+----+-------------------+------------------------------------------------------------------------------------------------------+%n");
        Iterator<Anamnese> iter1 = anamneses.iterator();
        while (iter1.hasNext()) {
            Anamnese a = iter1.next();
            String idAnamneseString = String.valueOf(a.getId());
            System.out.format(leftAlignFormat, String.format("%1.2s", idAnamneseString), String.format("%1.17s", a.getPaciente().getNome()), String.format("%1.100s", a.getMotivo()));
            System.out.format("+----+-------------------+------------------------------------------------------------------------------------------------------+%n");
        }
    }
}
