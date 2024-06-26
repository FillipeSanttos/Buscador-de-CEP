package br.com.buscadordecep.main;

import br.com.buscadordecep.modulos.ValidacaoDeDadosAPI;

import java.io.IOException;

public class mainprincipal {
    public static void main(String[] args) throws IOException, InterruptedException {

        ValidacaoDeDadosAPI novaPesquisa = new ValidacaoDeDadosAPI();

        System.out.println("Olá! Este é o buscador de CEP!");
        novaPesquisa.metodoDeCalcular();
        System.out.println("Obrigado por utilizar o buscador de CEP.");
    }
}
