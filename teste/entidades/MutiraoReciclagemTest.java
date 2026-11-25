package entidades;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MutiraoReciclagemTest {

    @Test
    void testarCalculoPontuacao() {
        MutiraoReciclagem mutirao = new MutiraoReciclagem(
                1,
                "Mutirão Comunitário",
                "Coleta de materiais recicláveis",
                LocalDateTime.now(),
                20,
                5
        );

        assertEquals(20, mutirao.calcularPontuacao());
    }
}
