package entidades;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MutiraoDeReciclagemTest {

    private static final LocalDateTime DATA = LocalDateTime.of(2026, 12, 5, 9, 0);

    @Test
    void deveCalcular4PontosPorHora() {
        MutiraoDeReciclagem mutirao = new MutiraoDeReciclagem(
                1, "Mutirão Comunitário", "Coleta de materiais recicláveis", DATA, 20, 5);

        assertEquals(20, mutirao.calcularPontuacao());
    }

    @Test
    void pontuacaoDeveEscalarComADuracao() {
        MutiraoDeReciclagem mutirao = new MutiraoDeReciclagem(
                2, "Mutirão Curto", "Duas horas", DATA, 10, 2);

        assertEquals(8, mutirao.calcularPontuacao());
    }

    @Test
    void deveRejeitarDuracaoInvalida() {
        assertThrows(IllegalArgumentException.class, () ->
                new MutiraoDeReciclagem(3, "Inválido", "Sem duração", DATA, 10, 0));
    }
}
