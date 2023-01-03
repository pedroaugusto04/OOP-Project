package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import sistema.controls.ControleAnamnese;
import sistema.controls.ControlePaciente;
import sistema.controls.ControleUsuario;
import sistema.date.IRepositorioAnamnese;
import sistema.date.IRepositorioPaciente;
import sistema.date.IRepositorioUsuario;
import sistema.models.Anamnese;
import sistema.models.Endereco;
import sistema.models.Paciente;
import sistema.models.Sexo;
import sistema.models.Tipo;
import sistema.models.Usuario;
import sistema.view.MenuView;

public class Sistema {

    private ControleUsuario controleUsuario;
    private ControlePaciente controlePaciente;
    private ControleAnamnese controleAnamnese;
    private MenuView menuView;
    private static Sistema instance;

    public static Sistema getInstance(IRepositorioUsuario repositorioUsuario,
            IRepositorioPaciente repositorioPaciente, IRepositorioAnamnese repositorioAnamnese) {
        if (instance == null) {
            instance = new Sistema(repositorioUsuario, repositorioPaciente, repositorioAnamnese);
        }
        return instance;
    }

    public static Sistema getInstance() {
        if (instance != null) {
            return instance;
        } else {
            throw new NullPointerException(
                    "Sistema não foi inicializado corretamente");
        }
    }

    private Sistema(IRepositorioUsuario repositorioUsuario,
            IRepositorioPaciente repositorioPaciente, IRepositorioAnamnese repositorioAnamnese) {
        controleUsuario = ControleUsuario.getInstance(repositorioUsuario);
        controlePaciente = ControlePaciente.getInstance(repositorioPaciente);
        controleAnamnese = ControleAnamnese.getInstance(repositorioAnamnese);
    }

    public void iniciarMenuPrincipal() {
        menuView = new MenuView();
        Usuario uLogin;
        int opcaoPrincipal = 0;
        do {
            opcaoPrincipal = menuView.menuPrincipal();
            switch (opcaoPrincipal) {
                case 0:
                    System.out.println("Programa encerrado");
                    System.exit(0);
                case 1:
                    uLogin = controleUsuario.logar();
                    iniciarMenuPosLogin(uLogin);
                    break;
                case 2:
                    iniciarMenuUsuario();
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (opcaoPrincipal != 0);
    }

    public void iniciarMenuPosLogin(Usuario uLogin) {
        if (uLogin.getTipo() == Tipo.ATENDENTE) {
            int opcaoAtendente = 0;
            do {
                opcaoAtendente = menuView.menuAtendente();
                switch (opcaoAtendente) {
                    case 0:
                        iniciarMenuPrincipal();
                    case 1:
                        controlePaciente.add();
                        break;
                    case 2:
                        controlePaciente.excluir();
                        break;
                    case 3:
                        controlePaciente.alterar();
                        break;
                    case 4:
                        controlePaciente.listar();
                        break;
                    case 5:
                        controlePaciente.adicionarEndereco();
                        break;
                    case 6:
                        uLogin = controleUsuario.logout(uLogin);
                        iniciarMenuPosLogin(uLogin);
                        break;
                    default:
                        System.out.println("Opção inválida");
                }
            } while (opcaoAtendente != 0);
        } else if (uLogin.getTipo() == Tipo.MEDICO) {
            int opcaoMedico = 0;
            do {
                opcaoMedico = menuView.menuMedico();
                switch (opcaoMedico) {
                    case 0:
                        iniciarMenuPrincipal();
                        break;
                    case 1:
                        controleAnamnese.add();
                        break;
                    case 2:
                        controleAnamnese.excluir();
                        break;
                    case 3:
                        controleAnamnese.alterar();
                        break;
                    case 4:
                        controleAnamnese.listar();
                        break;
                    case 5:
                        uLogin = controleUsuario.logout(uLogin);
                        iniciarMenuPosLogin(uLogin);
                        break;
                    default:
                        System.out.println("Opção inválida");
                }
            } while (opcaoMedico != 0);
        } else {
            System.out.println("Tipo indefinido, não é possível acessar um menu.");
        }
    }

    public void iniciarMenuUsuario() {
        int opcaoUsuario = 0;
        do {
            opcaoUsuario = menuView.menuUsuario();
            switch (opcaoUsuario) {
                case 0:
                    iniciarMenuPrincipal();
                case 1:
                    controleUsuario.add();
                    break;
                case 2:
                    controleUsuario.excluir();
                    break;
                case 3:
                    controleUsuario.alterar();
                    break;
                case 4:
                    controleUsuario.listar();
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        } while (opcaoUsuario != 0);
    }

    public void init() {
        String dataNasc;
        Date data = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Endereco end = new Endereco("cidade1", "logradouro1", "uf1", 1);
        Endereco end2 = new Endereco("cidade2", "logradouro2", "uf2", 2);
        Endereco end3 = new Endereco("cidade3", "logradouro3", "uf3", 3);
        dataNasc = "01/02/2000";
        try {
            data = sdf.parse(dataNasc);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Paciente p = Paciente.getInstance("nome1", "mae1", data, Sexo.MASCULINO, end, "123");
        dataNasc = "03/04/2001";
        try {
            data = sdf.parse(dataNasc);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Paciente p2 = Paciente.getInstance("nome2", "mae2", data, Sexo.FEMININO, end, "456");
        dataNasc = "05/06/2002";
        try {
            data = sdf.parse(dataNasc);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Paciente p3 = Paciente.getInstance("nome3", "mae3", data, Sexo.INDEFINIDO, end, "789");
        Anamnese a = Anamnese.getInstance(p, "motivo1", "historico1", "queixa1");
        Anamnese a2 = Anamnese.getInstance(p2, "motivo2", "historico2", "queixa2");
        Usuario u1 = Usuario.getInstance("nomeMedico", "lm", "sm", Tipo.MEDICO);
        Usuario u2 = Usuario.getInstance("nomeAtendente", "la", "sa", Tipo.ATENDENTE);
        controlePaciente.add(p);
        controlePaciente.add(p2);
        controlePaciente.add(p3);
        controleAnamnese.add(a);
        controleAnamnese.add(a2);
        controleUsuario.add(u1);
        controleUsuario.add(u2);
    }
}
