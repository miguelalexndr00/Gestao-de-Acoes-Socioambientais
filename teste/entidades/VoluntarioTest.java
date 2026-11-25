package entidades;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class VoluntarioTest {

    private static final LocalDateTime DATA = LocalDateTime.of(2026, 12, 5, 9, 0);

    private Voluntario voluntario;

    @BeforeEach
    void setUp() {
        voluntario = new Voluntario("Miguel", "miguel@email.com", "123");
    }

    @Test
    void deveRetornarDadosCadastrados() {
        assertEquals("Miguel", voluntario.getNome());
        assertEquals("miguel@email.com", voluntario.getEmail());
        assertEquals("123", voluntario.getMatricula());
    }

    @Test
    void deveIniciarSemAcoesESemPontos() {
        assertEquals(0, voluntario.getQuantidadeAcoes());
        assertEquals(0, voluntario.getPontuacaoAcumulada());
        assertEquals("Nome: Miguel | Ações: 0 | Pontuação: 0", voluntario.exibirDetalhes());
    }

    @Test
    void deveSomarPontuacaoDasAcoesInscritas() {
        Acao plantio = new PlantioDeMudas(1, "Plantio", "desc", DATA, 10, 3);      // 11
        Acao mutirao = new MutiraoDeReciclagem(2, "Mutirão", "desc", DATA, 10, 2); // 8
        Acao oficina = new OficinaEcologica(3, "Oficina", "desc", DATA, 10, 2, true); // 16

        plantio.inscrever(voluntario);
        mutirao.inscrever(voluntario);
        oficina.inscrever(voluntario);

        assertEquals(3, voluntario.getQuantidadeAcoes());
        assertEquals(35, voluntario.getPontuacaoAcumulada());
        assertEquals("Nome: Miguel | Ações: 3 | Pontuação: 35", voluntario.exibirDetalhes());
    }

    @Test
    void naoDeveContarAMesmaAcaoDuasVezes() {
        Acao mutirao = new MutiraoDeReciclagem(1, "Mutirão", "desc", DATA, 10, 2);

        mutirao.inscrever(voluntario);
        voluntario.adicionarAcao(mutirao); // package-private, acessível no mesmo pacote

        assertEquals(1, voluntario.getQuantidadeAcoes());
        assertEquals(8, voluntario.getPontuacaoAcumulada());
    }

    @Test
    void emailIgualIgnorandoMaiusculasDeveSerIgual() {
        Voluntario outro = new Voluntario("Outro nome", "MIGUEL@email.com", "456");

        assertEquals(voluntario, outro);
        assertEquals(voluntario.hashCode(), outro.hashCode());
    }

    @Test
    void emailDiferenteDeveSerDiferente() {
        Voluntario outro = new Voluntario("Miguel", "outro@email.com", "123");

        assertNotEquals(voluntario, outro);
    }

    @Test
    void deveRejeitarDadosObrigatoriosVazios() {
        assertThrows(IllegalArgumentException.class, () -> new Voluntario("", "a@email.com", "1"));
        assertThrows(IllegalArgumentException.class, () -> new Voluntario("Ana", " ", "1"));
        assertThrows(IllegalArgumentException.class, () -> new Voluntario("Ana", "a@email.com", null));
    }
}
