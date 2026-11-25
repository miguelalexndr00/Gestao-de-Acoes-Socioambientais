package sistema;

import org.junit.jupiter.api.Test;

import entidades.Voluntario;
import sistemas.SistemaVoluntario;
import excecoes.EmailDuplicadoException;

import static org.junit.jupiter.api.Assertions.*;

public class SistemaVoluntarioTest {

    @Test
    void testarCadastrarVoluntario() {
        SistemaVoluntario sistema = new SistemaVoluntario();

        boolean resultado = sistema.cadastrarVoluntario(
                "Miguel",
                "miguel@email.com",
                "123"
        );

        assertTrue(resultado);
    }

    @Test
    void testarBuscarVoluntario() {
        SistemaVoluntario sistema = new SistemaVoluntario();

        sistema.cadastrarVoluntario(
                "Miguel",
                "miguel@email.com",
                "123"
        );

        Voluntario voluntario = sistema.buscarVoluntario("miguel@email.com");

        assertEquals("Miguel", voluntario.getNome());
    }

    @Test
    void testarBuscarVoluntarioEmailMaiusculo() {
        SistemaVoluntario sistema = new SistemaVoluntario();

        sistema.cadastrarVoluntario(
                "Miguel",
                "miguel@email.com",
                "123"
        );

        Voluntario voluntario = sistema.buscarVoluntario("MIGUEL@EMAIL.COM");

        assertEquals("Miguel", voluntario.getNome());
    }

    @Test
    void testarEmailDuplicado() {
        SistemaVoluntario sistema = new SistemaVoluntario();

        sistema.cadastrarVoluntario(
                "Miguel",
                "miguel@email.com",
                "123"
        );

        assertThrows(EmailDuplicadoException.class, () -> {
            sistema.cadastrarVoluntario(
                    "Outro nome",
                    "MIGUEL@email.com",
                    "456"
            );
        });
    }

    @Test
    void testarExibirVoluntario() {
        SistemaVoluntario sistema = new SistemaVoluntario();

        sistema.cadastrarVoluntario(
                "Miguel",
                "miguel@email.com",
                "123"
        );

        String resultado = sistema.exibirVoluntario("miguel@email.com");

        assertEquals(
                "Nome: Miguel | Ações: 0 | Pontuação: 0",
                resultado
        );
    }

    @Test
    void testarBuscarVoluntarioInexistente() {
        SistemaVoluntario sistema = new SistemaVoluntario();

        assertThrows(IllegalArgumentException.class, () -> {
            sistema.buscarVoluntario("naoexiste@email.com");
        });
    }

    @Test
    void testarListarVoluntarios() {
        SistemaVoluntario sistema = new SistemaVoluntario();

        sistema.cadastrarVoluntario(
                "Miguel",
                "miguel@email.com",
                "123"
        );

        sistema.cadastrarVoluntario(
                "Ana",
                "ana@email.com",
                "456"
        );

        String[] resultado = sistema.listarVoluntarios();

        assertEquals(2, resultado.length);
    }

    @Test
    void testarListarVoluntariosVazio() {
        SistemaVoluntario sistema = new SistemaVoluntario();

        String[] resultado = sistema.listarVoluntarios();

        assertEquals(0, resultado.length);
    }
}
