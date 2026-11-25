package entidades;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class OficinaEcologicaTest {

    @Test
    void testarCalcularPontuacao() {
        OficinaEcologica oficina = new OficinaEcologica(
                1,
                "Oficina de Reciclagem",
                "Aprendendo sobre reciclagem",
                LocalDateTime.now(),
                20,
                5,
                true
        );

        assertEquals(25, oficina.calcularPontuacao());
    }

    @Test
    void testarGetDuracaoHoras() {
        OficinaEcologica oficina = new OficinaEcologica(
                1,
                "Oficina de Reciclagem",
                "Aprendendo sobre reciclagem",
                LocalDateTime.now(),
                20,
                5,
                true
        );

        assertEquals(5, oficina.getDuracaoHoras());
    }

    @Test
    void testarTemKitMaterial() {
        OficinaEcologica oficina = new OficinaEcologica(
                1,
                "Oficina de Reciclagem",
                "Aprendendo sobre reciclagem",
                LocalDateTime.now(),
                20,
                5,
                true
        );

        assertTrue(oficina.temKitMaterial());
    }

    @Test
    void testarSemKitMaterial() {
        OficinaEcologica oficina = new OficinaEcologica(
                1,
                "Oficina de Reciclagem",
                "Aprendendo sobre reciclagem",
                LocalDateTime.now(),
                20,
                5,
                false
        );

        assertFalse(oficina.temKitMaterial());
    }

    @Test
    void testarGetDetalhesEspecificos() {
        OficinaEcologica oficina = new OficinaEcologica(
                1,
                "Oficina de Reciclagem",
                "Aprendendo sobre reciclagem",
                LocalDateTime.now(),
                20,
                5,
                true
        );

        assertEquals(
                "Tipo: Oficina Ecológica | Duração: 5h | Kit: Sim",
                oficina.getDetalhesEspecificos()
        );
    }
}
