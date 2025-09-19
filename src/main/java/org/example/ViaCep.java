package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ViaCep {

    private static final String URL_BASE = "https://viacep.com.br/ws/";

    public Endereco getEnderecoFromCep(String cep) {
        try {
            if (cep == null || !cep.matches("\\d{8}")) {
                throw new ViaCepException("CEP inválido: " + cep);
            }

            String url = URL_BASE + cep + "/json/";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String body = response.body();

            if (body.contains("\"erro\": true")) {
                throw new ViaCepException("CEP inexistente: " + cep);
            }

            Endereco endereco = new Endereco();
            endereco.setCep(extractValue(body, "cep"));
            endereco.setLogradouro(extractValue(body, "logradouro"));
            endereco.setComplemento(extractValue(body, "complemento"));
            endereco.setBairro(extractValue(body, "bairro"));
            endereco.setLocalidade(extractValue(body, "localidade"));
            endereco.setEstado(extractValue(body,"estado"));
            endereco.setRegiao(extractValue(body,"regiao"));
            endereco.setUf(extractValue(body, "uf"));
            endereco.setIbge(extractValue(body, "ibge"));
            endereco.setGia(extractValue(body, "gia"));
            endereco.setDdd(extractValue(body, "ddd"));
            endereco.setSiafi(extractValue(body, "siafi"));

            return endereco;

        } catch (IOException | InterruptedException | java.net.URISyntaxException e) {
            throw new ViaCepException("Erro ao acessar API do ViaCEP", e);
        }
    }

    private String extractValue(String json, String key) {
        Pattern pattern = Pattern.compile("\"" + key + "\"\\s*:\\s*\"(.*?)\"");
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
