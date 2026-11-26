package sistemas;

import entidades.Voluntario;
import excecoes.AcaoLotadaException;
import excecoes.InscricaoDuplicadaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SistemaAcaoTest {

    @Test
    void deveGerarIdsSequenciais() {
        SistemaAcao sistema = new SistemaAcao();

        assertEquals(1, sistema.cadastrarPlantio("P", "d", "2026-12-05T09:00", 10, 3));
        assertEquals(2, sistema.cadastrarMutirao("M", "d", "2026-12-05T09:00", 10, 2));
        assertEquals(3, sistema.cadastrarOficina("O", "d", "2026-12-05T09:00", 10, 2, true));
    }

    @Test
    void cadastroInvalidoNaoDeveConsumirId() {
        SistemaAcao sistema = new SistemaAcao();

        assertThrows(IllegalArgumentException.class, () ->
                sistema.cadastrarPlantio("P", "d", "data errada", 10, 3));
        assertThrows(IllegalArgumentException.class, () ->
                sistema.cadastrarMutirao("", "d", "2026-12-05T09:00", 10, 2));

        assertEquals(1, sistema.cadastrarPlantio("P", "d", "2026-12-05T09:00", 10, 3));
    }

    @Test
    void deveAceitarOsDoisFormatosDeData() {
        SistemaAcao sistema = new SistemaAcao();

        int id1 = sistema.cadastrarPlantio("P", "d", "2026-12-05T09:00", 10, 3);
        int id2 = sistema.cadastrarPlantio("P", "d", "05/12/2026 09:00", 10, 3);

        assertEquals(sistema.buscarAcao(id1).getData(), sistema.buscarAcao(id2).getData());
    }

    @Test
    void deveRejeitarDataNulaOuVazia() {
        SistemaAcao sistema = new SistemaAcao();

        assertThrows(IllegalArgumentException.class, () ->
                sistema.cadastrarPlantio("P", "d", null, 10, 3));
        assertThrows(IllegalArgumentException.class, () ->
                sistema.cadastrarPlantio("P", "d", "  ", 10, 3));
    }

    @Test
    void deveLancarExcecaoParaAcaoInexistente() {
        SistemaAcao sistema = new SistemaAcao();

        assertThrows(IllegalArgumentException.class, () -> sistema.buscarAcao(99));
        assertThrows(IllegalArgumentException.class, () -> sistema.exibirDetalhesAcao(99));
    }

    @Test
    void deveInscreverEPropagarExcecoes() {
        SistemaAcao sistema = new SistemaAcao();
        int id = sistema.cadastrarPlantio("P", "d", "2026-12-05T09:00", 1, 3);
        Voluntario ana = new Voluntario("Ana", "ana@email.com", "1");
        Voluntario bia = new Voluntario("Bia", "bia@email.com", "2");

        assertTrue(sistema.inscreverVoluntario(ana, id));
        assertThrows(InscricaoDuplicadaException.class, () -> sistema.inscreverVoluntario(ana, id));
        assertThrows(AcaoLotadaException.class, () -> sistema.inscreverVoluntario(bia, id));
    }
}
