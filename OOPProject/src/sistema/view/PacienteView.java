package sistema.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import sistema.models.Endereco;
import sistema.models.Paciente;
import sistema.models.PacienteComDeficiencia;
import sistema.models.Sexo;
import sistema.models.TipoDeficiencia;

public class PacienteView {

    private Scanner sc;

    public PacienteView() {
        sc = new Scanner(System.in);
    }

    public Paciente lerPaciente() {
        Paciente p = null;
        Endereco end = null;
        char opcaoDeficiencia;
        char opcaoEndereco;
        Sexo sexo;
        TipoDeficiencia tipoDeficiencia;
        Date data = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print("Digite o nome do paciente: ");
        String nome = sc.nextLine();
        System.out.print("Digite a data de nascimento do paciente(dia/mes/ano): ");
        String dataNasc = sc.next();
        sc.nextLine();
        try {
            data = sdf.parse(dataNasc);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.print("Digite o sexo do paciente: ");
        char charSexo = sc.next().toUpperCase().charAt(0);
        sc.nextLine();
        if (charSexo == 'M') {
            sexo = Sexo.MASCULINO;
        } else if (charSexo == 'F') {
            sexo = Sexo.FEMININO;
        } else {
            sexo = Sexo.INDEFINIDO;
        }
        System.out.print("Digite o nome da mãe do paciente: ");
        String nomeMae = sc.nextLine();
        System.out.print("Digite o telefone do paciente: ");
        String telefone = sc.next();
        sc.nextLine();
        do {
            System.out.print("Deseja cadastrar endereço? S/N: ");
            opcaoEndereco = sc.next().toUpperCase().charAt(0);
            sc.nextLine();
        } while (opcaoEndereco != 'S' && opcaoEndereco != 'N');
        if (opcaoEndereco == 'S') {
            System.out.println("Cidade: ");
            String cidade = sc.nextLine();
            System.out.println("Logradouro: ");
            String logradouro = sc.nextLine();
            System.out.println("UF: ");
            String uf = sc.next();
            sc.nextLine();
            System.out.println("Numero: ");
            int numero = sc.nextInt();
            sc.nextLine();
            end = new Endereco(cidade, logradouro, uf, numero);
        }
        do {
            System.out.print("Paciente possui Deficiência? S/N: ");
            opcaoDeficiencia = sc.next().toUpperCase().charAt(0);
            sc.nextLine();
        } while (opcaoDeficiencia != 'S' && opcaoDeficiencia != 'N');
        if (opcaoDeficiencia == 'S') {
            System.out.print("Digite o fator complicador: ");
            String fatorComplicador = sc.nextLine();
            System.out.print("Digite o tipo da deficiência: MOTORA/MENTAL/VISUAL: ");
            String tipoDeficienciaString = sc.next().toUpperCase();
            sc.nextLine();
            if (tipoDeficienciaString.equals("MOTORA")) {
                tipoDeficiencia = TipoDeficiencia.MOTORA;
            } else if (tipoDeficienciaString.equals("MENTAL")) {
                tipoDeficiencia = TipoDeficiencia.MENTAL;
            } else if (tipoDeficienciaString.equals("VISUAL")) {
                tipoDeficiencia = TipoDeficiencia.VISUAL;
            } else {
                tipoDeficiencia = TipoDeficiencia.INDEFINIDA;
            }
            p = PacienteComDeficiencia.getInstance(fatorComplicador, tipoDeficiencia, nome, nomeMae, data, sexo, end, telefone);
        } else if (opcaoDeficiencia == 'N') {
            p = Paciente.getInstance(nome, nomeMae, data, sexo, end, telefone);
        }
        return p;
    }

    public long lerNumCnsPaciente() {
        System.out.print("Digite o numCNS do paciente: ");
        long numCns = sc.nextLong();
        sc.nextLine();
        return numCns;
    }

    public Paciente alterar(Paciente p) {
        Paciente pAlterado = null;
        if (p != null) {
            try {
                pAlterado = p.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            int opcao;
            do {
                System.out.println("-------------------------------");
                System.out.println("0 - Sair do menu de alteração");
                System.out.println("1 - Alterar Telefone");
                System.out.println("2 - Alterar endereço ");
                System.out.println("3 - Alterar nome ");
                System.out.println("-------------------------------");
                opcao = sc.nextInt();
                sc.nextLine();
                switch (opcao) {
                    case 0:
                        break;
                    case 1:
                        System.out.println("Digite o novo telefone do paciente:");
                        pAlterado.setTelefone(sc.next());
                        sc.nextLine();
                        System.out.println("Telefone alterado com sucesso!");
                        break;
                    case 2:
                        if (pAlterado.getEndereco() != null) {
                            alterarEndereco(pAlterado);
                            break;
                        } else {
                            System.out.println("Paciente ainda não possui endereço cadastrado!");
                            break;
                        }
                    case 3:
                        System.out.print("Digite o novo nome do paciente:");
                        pAlterado.setNome(sc.nextLine());
                        System.out.println("Nome alterado com sucesso!");
                        break;
                }
            } while (opcao != 0);
        } else {
            System.out.println("Paciente não existe.");
        }
        return pAlterado;
    }

    public void mostrarPacientes(List<Paciente> pacientes) {
        TipoDeficiencia tipoDeficiencia = TipoDeficiencia.INDEFINIDA;
        String leftAlignFormat = "| %-7s | %-13s | %-13s | %-11s | %-17s |%n";
        System.out.format("+---------+---------------+---------------+-------------+-------------------+%n");
        System.out.format("| NUM_CNS | NOME          | NOME_MÃE      | TELEFONE    |   DEFICIENCIA     |%n");
        System.out.format("+---------+---------------+---------------+-------------+-------------------+%n");
        Iterator<Paciente> iter = pacientes.iterator();
        while (iter.hasNext()) {
            Paciente p = iter.next();
            if (p instanceof PacienteComDeficiencia) {
                PacienteComDeficiencia pDeficiente = (PacienteComDeficiencia) p;
                tipoDeficiencia = pDeficiente.getTipoDeficiencia();
            }
            int numCnsPacienteInt = Math.toIntExact(p.getNumCns());
            String numCnsPacienteString = String.valueOf(numCnsPacienteInt);
            System.out.format(leftAlignFormat, String.format("%1.7s", numCnsPacienteString),
                    String.format("%1.13s", p.getNome()), String.format("%1.13s", p.getNomeMae()),
                    String.format("%1.11s", p.getTelefone()), String.format("%1.17s", tipoDeficiencia));
            System.out.format("+---------+---------------+---------------+-------------+-------------------+%n");
        }
    }

    public Paciente alterarEndereco(Paciente p) {
        Paciente pacienteAlterado = null;
        if (p != null) {
            try {
                pacienteAlterado = p.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            System.out.print("Endereço do paciente:\n");
            System.out.print("Cidade: ");
            String cidade = sc.nextLine();
            System.out.print("Logradouro: ");
            String logradouro = sc.nextLine();
            System.out.print("UF: ");
            String uf = sc.nextLine();
            System.out.print("Numero: ");
            int numero = sc.nextInt();
            sc.nextLine();
            Endereco end = new Endereco(cidade, logradouro, uf, numero);
            pacienteAlterado.setEndereco(end);
            System.out.println("Endereco alterado com sucesso!");
        }
        return pacienteAlterado;
    }
}
