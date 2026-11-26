package entidades;

import excecoes.AcaoLotadaException;
import excecoes.InscricaoDuplicadaException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AcaoTest {

    private static final LocalDateTime DATA = LocalDateTime.of(2026, 12, 5, 9, 0);

    private Acao criar(int capacidade) {
        return new MutiraoDeReciclagem(1, "Mutirão", "Coleta", DATA, capacidade, 2);
    }

    @Test
    void deveInscreverVoluntarioEAtualizarSuasAcoes() {
        Acao acao = criar(5);
        Voluntario v = new Voluntario("Ana", "ana@email.com", "1");

        acao.inscrever(v);

        assertTrue(acao.jaInscrito(v));
        assertEquals(1, acao.getInscritos().size());
        assertEquals(1, v.getQuantidadeAcoes());
        assertEquals(8, v.getPontuacaoAcumulada());
    }

    @Test
    void deveRejeitarInscricaoDuplicada() {
        Acao acao = criar(5);
        Voluntario v = new Voluntario("Ana", "ana@email.com", "1");
        acao.inscrever(v);

        assertThrows(InscricaoDuplicadaException.class, () -> acao.inscrever(v));
        assertEquals(1, acao.getInscritos().size());
    }

    @Test
    void deveRejeitarMesmoVoluntarioComEmailEmMaiusculo() {
        Acao acao = criar(5);
        acao.inscrever(new Voluntario("Ana", "ana@email.com", "1"));

        assertThrows(InscricaoDuplicadaException.class, () ->
                acao.inscrever(new Voluntario("Ana", "ANA@EMAIL.COM", "1")));
    }

    @Test
    void deveRejeitarInscricaoQuandoLotada() {
        Acao acao = criar(1);
        Voluntario a = new Voluntario("Ana", "ana@email.com", "1");
        Voluntario b = new Voluntario("Bia", "bia@email.com", "2");
        acao.inscrever(a);

        assertTrue(acao.estaLotada());
        assertThrows(AcaoLotadaException.class, () -> acao.inscrever(b));
        assertEquals(0, b.getQuantidadeAcoes());
    }

    @Test
    void naoDeveExporListaInternaDeInscritos() {
        Acao acao = criar(5);
        acao.getInscritos().add(new Voluntario("Ana", "ana@email.com", "1"));

        assertEquals(0, acao.getInscritos().size());
    }

    @Test
    void deveExibirDetalhes() {
        Acao acao = criar(5);
        assertTrue(acao.exibirDetalhes().contains("Inscritos: nenhum"));

        acao.inscrever(new Voluntario("Ana", "ana@email.com", "1"));
        String detalhes = acao.exibirDetalhes();

        assertTrue(detalhes.contains("Data: 05/12/2026 09:00"));
        assertTrue(detalhes.contains("Pontuação: 8"));
        assertTrue(detalhes.contains("Inscritos: Ana"));
    }

    @Test
    void deveValidarCamposObrigatorios() {
        assertThrows(IllegalArgumentException.class, () ->
                new MutiraoDeReciclagem(1, " ", "d", DATA, 5, 2));
        assertThrows(IllegalArgumentException.class, () ->
                new MutiraoDeReciclagem(1, "T", "d", null, 5, 2));
        assertThrows(IllegalArgumentException.class, () ->
                new MutiraoDeReciclagem(1, "T", "d", DATA, 0, 2));
    }
}
