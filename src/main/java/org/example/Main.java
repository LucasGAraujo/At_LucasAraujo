package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ViaCep viaCep = new ViaCep();

        String cep = "01001-000"; // CEP de exemplo

        try {
            Endereco endereco = viaCep.getEnderecoFromCep(cep);

            if (endereco != null) {
                System.out.println("CEP: " + endereco.getCep());
                System.out.println("Logradouro: " + endereco.getLogradouro());
                System.out.println("Complemento: " + endereco.getComplemento());
                System.out.println("Bairro: " + endereco.getBairro());
                System.out.println("Cidade: " + endereco.getLocalidade());
                System.out.println("UF: " + endereco.getUf());
                System.out.println("Estado: " + endereco.getEstado());
                System.out.println("Região: " + endereco.getRegiao());
                System.out.println("IBGE: " + endereco.getIbge());
                System.out.println("GIA: " + endereco.getGia());
                System.out.println("DDD: " + endereco.getDdd());
                System.out.println("SIAFI: " + endereco.getSiafi());
            } else {
                System.out.println("CEP inexistente: " + cep);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Erro ao acessar ViaCEP: " + e.getMessage());
        }
    }
}