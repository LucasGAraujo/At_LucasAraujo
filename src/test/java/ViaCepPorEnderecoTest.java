import org.example.Endereco;
import org.example.ViaCepException;
import org.example.ViaCepPorEndereco;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ViaCepPorEnderecoTest {
    private final ViaCepPorEndereco viaCep = new ViaCepPorEndereco();

    @Test
    void testEnderecoValido() {
        Endereco[] enderecos = viaCep.getEnderecoFromAddress("SP", "Sao Paulo", "Avenida Paulista");
        assertNotNull(enderecos);
        assertTrue(enderecos.length > 0, "Deve retornar pelo menos um endereço");
        assertEquals("SP", enderecos[0].getUf());
        assertEquals("Sao Paulo", enderecos[0].getLocalidade());
        assertTrue(enderecos[0].getLogradouro().contains("Paulista"));
    }

    @Test
    void testEnderecoInexistente() {
        ViaCepException exception = assertThrows(ViaCepException.class,
                () -> viaCep.getEnderecoFromAddress("SP", "CidadeFalsa", "RuaInexistente"));
        assertTrue(exception.getMessage().contains("Endereço não encontrado"));
    }

    @Test
    void testParametrosInvalidos() {
        ViaCepException exception = assertThrows(ViaCepException.class,
                () -> viaCep.getEnderecoFromAddress("", "Sao Paulo", "Avenida Paulista"));
        assertTrue(exception.getMessage().contains("Parâmetros inválidos"));
    }
}