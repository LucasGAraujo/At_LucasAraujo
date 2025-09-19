import org.example.Endereco;
import org.example.ViaCep;
import org.example.ViaCepException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ViaCepTest {

    private final ViaCep viaCep = new ViaCep();

    @Test
    void testCepComLetras() {
        String cep = "ABCDE123";
        ViaCepException exception = assertThrows(ViaCepException.class,
                () -> viaCep.getEnderecoFromCep(cep));
        assertEquals("CEP inválido: " + cep, exception.getMessage());
    }

    @Test
    void testCepVazio() {
        String cep = "";
        ViaCepException exception = assertThrows(ViaCepException.class,
                () -> viaCep.getEnderecoFromCep(cep));
        assertEquals("CEP inválido: " + cep, exception.getMessage());
    }

    @Test
    void testCepTamanhoErrado() {
        String cep = "123";
        ViaCepException exception = assertThrows(ViaCepException.class,
                () -> viaCep.getEnderecoFromCep(cep));
        assertEquals("CEP inválido: " + cep, exception.getMessage());
    }

    @Test
    void testCepInexistente() {
        String cep = "0000002121200";
        ViaCepException exception = assertThrows(ViaCepException.class,
                () -> viaCep.getEnderecoFromCep(cep));
        assertEquals("CEP inválido: " + cep, exception.getMessage());
    }

    @Test
    void testCepValido() {
        String cep = "01001000";
        Endereco endereco = viaCep.getEnderecoFromCep(cep);
        assertNotNull(endereco, "CEP válido não deve retornar null");
        assertEquals("01001-000", endereco.getCep());
        assertEquals("Praça da Sé", endereco.getLogradouro());
    }
}
