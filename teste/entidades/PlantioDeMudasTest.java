package entidades;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlantioDeMudasTest {

    private static final LocalDateTime DATA = LocalDateTime.of(2026, 12, 5, 9, 0);

    private PlantioDeMudas criar(int qtdMudas) {
        return new PlantioDeMudas(1, "Plantio Comunitário", "Plantio de árvores",
                DATA, 20, qtdMudas);
    }

    @Test
    void deveSomar5DeBaseMais2PorMuda() {
        assertEquals(45, criar(20).calcularPontuacao());
    }

    @Test
    void deveManterApenasPontuacaoBaseSemMudas() {
        assertEquals(5, criar(0).calcularPontuacao());
    }

    @Test
    void pontuacaoDeveEscalarComAQuantidadeDeMudas() {
        assertEquals(11, criar(3).calcularPontuacao());
    }

    @Test
    void deveMontarDetalhesEspecificos() {
        assertEquals("Tipo: Plantio de Mudas | Mudas: 20",
                criar(20).getDetalhesEspecificos());
    }

    @Test
    void deveRejeitarQuantidadeNegativa() {
        assertThrows(IllegalArgumentException.class, () -> criar(-1));
    }
}
