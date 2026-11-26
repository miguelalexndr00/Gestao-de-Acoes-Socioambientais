package sistemas;

import excecoes.AcaoLotadaException;
import excecoes.EmailDuplicadoException;
import excecoes.InscricaoDuplicadaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ImpactaTest {

    private Impacta impacta;

    @BeforeEach
    void setUp() {
        impacta = new Impacta();
        impacta.cadastrarVoluntario("Ana", "ana@email.com", "1");
        impacta.cadastrarVoluntario("Bia", "bia@email.com", "2");
        impacta.cadastrarVoluntario("Caio", "caio@email.com", "3");
    }

    @Test
    void deveInscreverESomarPontuacao() {
        int plantio = impacta.cadastrarPlantio("Plantio", "d", "05/12/2026 09:00", 10, 3); // 11
        int mutirao = impacta.cadastrarMutirao("Mutirão", "d", "06/12/2026 09:00", 10, 2);  // 8

        assertTrue(impacta.inscreverVoluntario("ana@email.com", plantio));
        assertTrue(impacta.inscreverVoluntario("ANA@email.com", mutirao));

        assertEquals("Nome: Ana | Ações: 2 | Pontuação: 19", impacta.exibirVoluntario("ana@email.com"));
    }

    @Test
    void deveListarOrdenadoPorPontuacaoDepoisPorNome() {
        int oficina = impacta.cadastrarOficina("Oficina", "d", "05/12/2026 09:00", 10, 2, true); // 16
        impacta.inscreverVoluntario("caio@email.com", oficina);

        String[] lista = impacta.listarVoluntarios();

        assertEquals(3, lista.length);
        assertTrue(lista[0].startsWith("Nome: Caio"));
        assertTrue(lista[1].startsWith("Nome: Ana"));
        assertTrue(lista[2].startsWith("Nome: Bia"));
    }

    @Test
    void devePropagarExcecoesDeNegocio() {
        int id = impacta.cadastrarPlantio("Plantio", "d", "05/12/2026 09:00", 1, 3);
        impacta.inscreverVoluntario("ana@email.com", id);

        assertThrows(InscricaoDuplicadaException.class, () -> impacta.inscreverVoluntario("ana@email.com", id));
        assertThrows(AcaoLotadaException.class, () -> impacta.inscreverVoluntario("bia@email.com", id));
        assertThrows(EmailDuplicadoException.class, () -> impacta.cadastrarVoluntario("X", "ANA@email.com", "9"));
    }

    @Test
    void deveFalharComVoluntarioOuAcaoInexistente() {
        int id = impacta.cadastrarPlantio("Plantio", "d", "05/12/2026 09:00", 5, 3);

        assertThrows(IllegalArgumentException.class, () -> impacta.inscreverVoluntario("nao@email.com", id));
        assertThrows(IllegalArgumentException.class, () -> impacta.inscreverVoluntario("ana@email.com", 99));
        assertThrows(IllegalArgumentException.class, () -> impacta.exibirDetalhesAcao(99));
    }

    @Test
    void deveExibirDetalhesDaAcaoComInscritos() {
        int id = impacta.cadastrarOficina("Oficina", "Reciclagem", "05/12/2026 09:00", 5, 2, false);
        impacta.inscreverVoluntario("bia@email.com", id);

        String detalhes = impacta.exibirDetalhesAcao(id);

        assertTrue(detalhes.contains("Tipo: Oficina Ecológica | Duração: 2h | Kit: Não"));
        assertTrue(detalhes.contains("Pontuação: 6"));
        assertTrue(detalhes.contains("Inscritos: Bia"));
    }
}
