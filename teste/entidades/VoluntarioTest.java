package entidades;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VoluntarioTest {

    @Test
    void testarGetNome() {
        Voluntario voluntario = new Voluntario(
                "Miguel",
                "miguel@email.com",
                "123"
        );

        assertEquals("Miguel", voluntario.getNome());
    }

    @Test
    void testarGetEmail() {
        Voluntario voluntario = new Voluntario(
                "Miguel",
                "miguel@email.com",
                "123"
        );

        assertEquals("miguel@email.com", voluntario.getEmail());
    }

    @Test
    void testarGetMatricula() {
        Voluntario voluntario = new Voluntario(
                "Miguel",
                "miguel@email.com",
                "123"
        );

        assertEquals("123", voluntario.getMatricula());
    }

    @Test
    void testarQuantidadeAcoes() {
        Voluntario voluntario = new Voluntario(
                "Miguel",
                "miguel@email.com",
                "123"
        );

        assertEquals(0, voluntario.getQuantidadeAcoes());
    }

    @Test
    void testarPontuacaoAcumulada() {
        Voluntario voluntario = new Voluntario(
                "Miguel",
                "miguel@email.com",
                "123"
        );

        assertEquals(0, voluntario.getPontuacaoAcumulada());
    }

    @Test
    void testarExibirDetalhes() {
        Voluntario voluntario = new Voluntario(
                "Miguel",
                "miguel@email.com",
                "123"
        );

        assertEquals(
                "Nome: Miguel | Ações: 0 | Pontuação: 0",
                voluntario.exibirDetalhes()
        );
    }

    @Test
    void testarEquals() {
        Voluntario voluntario1 = new Voluntario(
                "Miguel",
                "miguel@email.com",
                "123"
        );

        Voluntario voluntario2 = new Voluntario(
                "Outro nome",
                "MIGUEL@email.com",
                "456"
        );

        assertEquals(voluntario1, voluntario2);
    }

    @Test
    void testarHashCode() {
        Voluntario voluntario1 = new Voluntario(
                "Miguel",
                "miguel@email.com",
                "123"
        );

        Voluntario voluntario2 = new Voluntario(
                "Outro nome",
                "MIGUEL@email.com",
                "456"
        );

        assertEquals(voluntario1.hashCode(), voluntario2.hashCode());
    }
}
