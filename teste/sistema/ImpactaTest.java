package sistema;

import excecoes.AcaoLotadaException;
import excecoes.EmailDuplicadoException;
import excecoes.InscricaoDuplicadaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sistemas.Impacta;

import static org.junit.jupiter.api.Assertions.*;

public class ImpactaTest {

    private static final String DATA = "2026-12-05T09:00";

    private Impacta impacta;

    @BeforeEach
    void setUp() {
        impacta = new Impacta();
    }

    // ---------- Voluntários ----------

    @Test
    void deveCadastrarVoluntario() {
        assertTrue(impacta.cadastrarVoluntario("Ana", "ana@email.com", "1"));
    }

    @Test
    void deveLancarExcecaoParaEmailDuplicado() {
        impacta.cadastrarVoluntario("Ana", "ana@email.com", "1");

        assertThrows(EmailDuplicadoException.class, () ->
                impacta.cadastrarVoluntario("Outra Ana", "ANA@email.com", "2"));
    }

    @Test
    void deveExibirVoluntario() {
        impacta.cadastrarVoluntario("Ana", "ana@email.com", "1");

        assertEquals("Nome: Ana | Ações: 0 | Pontuação: 0",
                impacta.exibirVoluntario("ana@email.com"));
    }

    // ---------- Ações ----------

    @Test
    void deveGerarIdsSequenciaisParaAcoes() {
        int id1 = impacta.cadastrarPlantio("Plantio", "desc", DATA, 10, 3);
        int id2 = impacta.cadastrarMutirao("Mutirão", "desc", DATA, 10, 2);
        int id3 = impacta.cadastrarOficina("Oficina", "desc", DATA, 10, 2, true);

        assertEquals(1, id1);
        assertEquals(2, id2);
        assertEquals(3, id3);
    }

    @Test
    void deveAceitarDataNoFormatoBrasileiro() {
        int id = impacta.cadastrarMutirao("Mutirão", "desc", "05/12/2026 09:00", 10, 2);

        assertTrue(impacta.exibirDetalhesAcao(id).contains("05/12/2026 09:00"));
    }

    @Test
    void deveRejeitarDataInvalida() {
        assertThrows(IllegalArgumentException.class, () ->
                impacta.cadastrarMutirao("Mutirão", "desc", "data errada", 10, 2));
    }

    @Test
    void deveExibirDetalhesDoPlantio() {
        int id = impacta.cadastrarPlantio("Plantio Central", "Árvores", DATA, 10, 3);

        String detalhes = impacta.exibirDetalhesAcao(id);

        assertTrue(detalhes.contains("Plantio Central"));
        assertTrue(detalhes.contains("Pontuação: 11"));
        assertTrue(detalhes.contains("Tipo: Plantio de Mudas | Mudas: 3"));
        assertTrue(detalhes.contains("Inscritos: nenhum"));
    }

    @Test
    void deveLancarExcecaoParaAcaoInexistente() {
        assertThrows(IllegalArgumentException.class, () -> impacta.exibirDetalhesAcao(99));
    }

    // ---------- Inscrição ----------

    @Test
    void deveInscreverVoluntarioEMostrarNosDetalhes() {
        impacta.cadastrarVoluntario("Ana", "ana@email.com", "1");
        int id = impacta.cadastrarMutirao("Mutirão", "desc", DATA, 10, 2);

        assertTrue(impacta.inscreverVoluntario("ana@email.com", id));

        assertTrue(impacta.exibirDetalhesAcao(id).contains("Inscritos: Ana"));
        assertEquals("Nome: Ana | Ações: 1 | Pontuação: 8",
                impacta.exibirVoluntario("ana@email.com"));
    }

    @Test
    void deveLancarExcecaoParaDuplaInscricao() {
        impacta.cadastrarVoluntario("Ana", "ana@email.com", "1");
        int id = impacta.cadastrarMutirao("Mutirão", "desc", DATA, 10, 2);
        impacta.inscreverVoluntario("ana@email.com", id);

        assertThrows(InscricaoDuplicadaException.class, () ->
                impacta.inscreverVoluntario("ana@email.com", id));
    }

    @Test
    void deveLancarExcecaoParaAcaoLotada() {
        impacta.cadastrarVoluntario("Ana", "ana@email.com", "1");
        impacta.cadastrarVoluntario("Bia", "bia@email.com", "2");
        int id = impacta.cadastrarMutirao("Mutirão", "desc", DATA, 1, 2); // só 1 vaga

        impacta.inscreverVoluntario("ana@email.com", id);

        assertThrows(AcaoLotadaException.class, () ->
                impacta.inscreverVoluntario("bia@email.com", id));
    }

    @Test
    void deveLancarExcecaoParaVoluntarioInexistenteNaInscricao() {
        int id = impacta.cadastrarMutirao("Mutirão", "desc", DATA, 10, 2);

        assertThrows(IllegalArgumentException.class, () ->
                impacta.inscreverVoluntario("naoexiste@email.com", id));
    }

    @Test
    void deveLancarExcecaoParaInscricaoEmAcaoInexistente() {
        impacta.cadastrarVoluntario("Ana", "ana@email.com", "1");

        assertThrows(IllegalArgumentException.class, () ->
                impacta.inscreverVoluntario("ana@email.com", 99));
    }

    // ---------- Pontuação polimórfica e ranking ----------

    @Test
    void deveSomarPontuacaoDosTresTiposDeAcao() {
        impacta.cadastrarVoluntario("Ana", "ana@email.com", "1");
        int plantio = impacta.cadastrarPlantio("Plantio", "desc", DATA, 10, 3);       // 11
        int mutirao = impacta.cadastrarMutirao("Mutirão", "desc", DATA, 10, 2);       // 8
        int oficina = impacta.cadastrarOficina("Oficina", "desc", DATA, 10, 2, true); // 16

        impacta.inscreverVoluntario("ana@email.com", plantio);
        impacta.inscreverVoluntario("ana@email.com", mutirao);
        impacta.inscreverVoluntario("ana@email.com", oficina);

        assertEquals("Nome: Ana | Ações: 3 | Pontuação: 35",
                impacta.exibirVoluntario("ana@email.com"));
    }

    @Test
    void deveListarRankingPorPontuacaoComDesempatePorNome() {
        impacta.cadastrarVoluntario("Zeca", "zeca@email.com", "1");
        impacta.cadastrarVoluntario("Carlos", "carlos@email.com", "2");
        impacta.cadastrarVoluntario("Bia", "bia@email.com", "3");
        impacta.cadastrarVoluntario("bruno", "bruno@email.com", "4");
        impacta.cadastrarVoluntario("Ana", "ana@email.com", "5");

        int plantio = impacta.cadastrarPlantio("Plantio", "desc", DATA, 10, 3);  // 11
        int mutirao = impacta.cadastrarMutirao("Mutirão", "desc", DATA, 10, 2);  // 8

        impacta.inscreverVoluntario("bia@email.com", plantio);
        impacta.inscreverVoluntario("carlos@email.com", mutirao);
        impacta.inscreverVoluntario("bruno@email.com", mutirao);
        impacta.inscreverVoluntario("ana@email.com", mutirao);

        String[] ranking = impacta.listarVoluntarios();

        assertEquals(5, ranking.length);
        assertEquals("Nome: Bia | Ações: 1 | Pontuação: 11", ranking[0]);
        assertEquals("Nome: Ana | Ações: 1 | Pontuação: 8", ranking[1]);
        assertEquals("Nome: bruno | Ações: 1 | Pontuação: 8", ranking[2]); // antes de Carlos: ignora maiúsculas
        assertEquals("Nome: Carlos | Ações: 1 | Pontuação: 8", ranking[3]);
        assertEquals("Nome: Zeca | Ações: 0 | Pontuação: 0", ranking[4]);
    }
}
