package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ViaCepPorEndereco {

    private static final String URL_BASE = "https://viacep.com.br/ws/";

    public Endereco[] getEnderecoFromAddress(String uf, String cidade, String logradouro) {
        try {
            if (uf == null || uf.isEmpty() || cidade == null || cidade.isEmpty() || logradouro == null || logradouro.isEmpty()) {
                throw new ViaCepException("Parâmetros inválidos para consulta por endereço");
            }

            String url = URL_BASE + uf + "/" + cidade.replace(" ",
                    "%20") + "/" + logradouro.replace(" ", "%20") + "/json/";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String body = response.body();

            if (body.contains("\"erro\": true") || body.equals("[]")) {
                throw new ViaCepException("Endereço não encontrado: " + uf + "/" + cidade + "/" + logradouro);
            }

            String[] entries = body.split("\\},\\{");
            Endereco[] enderecos = new Endereco[entries.length];

            for (int i = 0; i < entries.length; i++) {
                Endereco e = new Endereco();
                e.setCep(extractValue(entries[i], "cep"));
                e.setLogradouro(extractValue(entries[i], "logradouro"));
                e.setBairro(extractValue(entries[i], "bairro"));
                e.setLocalidade(cidade);
                e.setUf(uf);
                enderecos[i] = e;
            }

            return enderecos;

        } catch (IOException | InterruptedException | java.net.URISyntaxException e) {
            throw new ViaCepException("Erro ao acessar API do ViaCEP", e);
        }
    }

    private String extractValue(String json, String key) {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("\"" + key + "\"\\s*:\\s*\"(.*?)\"");
        java.util.regex.Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
