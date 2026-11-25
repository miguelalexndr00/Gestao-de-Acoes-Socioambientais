package entidades;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlantioMudasTest {

    @Test
    void testarCalculoPontuacao() {
        PlantioDeMudas plantio = new PlantioDeMudas(
                1,
                "Plantio Comunitário",
                "Plantio de árvores",
                LocalDateTime.now(),
                20,
                20
        );

        assertEquals(45, plantio.calcularPontuacao());
    }
}
