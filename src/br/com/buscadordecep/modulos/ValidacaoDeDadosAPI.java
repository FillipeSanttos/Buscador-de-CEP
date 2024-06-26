package br.com.buscadordecep.modulos;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ValidacaoDeDadosAPI {

    String numeroDoCEP = "";
    String validacaoDeCEP = "";
    Scanner acompanharEscrita = new Scanner(System.in);

    public void metodoDeCalcular() throws IOException, InterruptedException {
        while (!numeroDoCEP.equalsIgnoreCase("^\\d{8}$")) {
            System.out.println("Digite o CEP (apenas números) para ver e gravar as informações:");
            numeroDoCEP = acompanharEscrita.nextLine();

            //Validação de CEP: ViaCEP API
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://viacep.com.br/ws/" + numeroDoCEP + "/json/")).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            validacaoDeCEP = response.body();

            //Validar número do CEP = 8 e confirmação de CEP válido na API
            if (numeroDoCEP.matches("^\\d{8}$") && (!validacaoDeCEP.contains("true"))) {
                break;
            } else {
                System.out.println("CEP incorreto ou inexistente!");
            }
        }

        //Gravar informações em arquivo JSON
        FileWriter escreverArquivoDeTexto = new FileWriter("cep"+numeroDoCEP+".json");
        escreverArquivoDeTexto.write(validacaoDeCEP);
        escreverArquivoDeTexto.close();

        //Mostrar informações
        System.out.println("Segue as informações do CEP:");
        System.out.println(validacaoDeCEP);
        System.out.println("As informações também foram gravados no arquivo: cep"+numeroDoCEP+".json' ");
    }
}
