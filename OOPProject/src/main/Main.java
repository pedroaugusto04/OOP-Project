package main;

import sistema.date.IRepositorioAnamnese;
import sistema.date.IRepositorioPaciente;
import sistema.date.IRepositorioUsuario;
import sistema.date.RepositorioAnamnese;
import sistema.date.RepositorioPaciente;
import sistema.date.RepositorioUsuario;

public class Main {

    public static void main(String[] args) {
        // Pedro Augusto Almeida Duarte
        IRepositorioUsuario repositorioUsuario = RepositorioUsuario.getInstance();
        IRepositorioPaciente repositorioPaciente = RepositorioPaciente.getInstance();
        IRepositorioAnamnese repositorioAnamnese = RepositorioAnamnese.getInstance();
        Sistema sistema = Sistema.getInstance(repositorioUsuario, repositorioPaciente, repositorioAnamnese);
        sistema.init();
        sistema.iniciarMenuPrincipal();
    }
}
