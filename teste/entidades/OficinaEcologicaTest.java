package entidades;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OficinaEcologicaTest {

    private static final LocalDateTime DATA = LocalDateTime.of(2026, 12, 5, 9, 0);

    private OficinaEcologica criar(int duracaoHoras, boolean kit) {
        return new OficinaEcologica(1, "Oficina de Reciclagem",
                "Aprendendo sobre reciclagem", DATA, 20, duracaoHoras, kit);
    }

    @Test
    void deveSomarBonusQuandoHaKit() {
        assertEquals(25, criar(5, true).calcularPontuacao());
    }

    @Test
    void naoDeveSomarBonusSemKit() {
        assertEquals(15, criar(5, false).calcularPontuacao());
    }

    @Test
    void deveRetornarDuracao() {
        assertEquals(5, criar(5, true).getDuracaoHoras());
    }

    @Test
    void deveInformarSeTemKit() {
        assertTrue(criar(5, true).temKitMaterial());
        assertFalse(criar(5, false).temKitMaterial());
    }

    @Test
    void deveMontarDetalhesEspecificos() {
        assertEquals("Tipo: Oficina Ecológica | Duração: 5h | Kit: Sim",
                criar(5, true).getDetalhesEspecificos());
        assertEquals("Tipo: Oficina Ecológica | Duração: 5h | Kit: Não",
                criar(5, false).getDetalhesEspecificos());
    }

    @Test
    void deveRejeitarDuracaoInvalida() {
        assertThrows(IllegalArgumentException.class, () -> criar(0, true));
    }
}
