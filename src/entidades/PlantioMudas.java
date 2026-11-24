package entidades;

import java.time.LocalDateTime;

// PlantioDeMudas.java
public class PlantioDeMudas extends Acao {
    private int qtdMudas;

    public PlantioDeMudas(int id, String titulo, String descricao,
                          LocalDateTime data, int maxParticipantes, int qtdMudas) {
        super(id, titulo, descricao, data, maxParticipantes);
        this.qtdMudas = qtdMudas;
    }

    public int getQtdMudas() { return qtdMudas; }

    @Override
    public int calcularPontuacao() {
        return 5 + 2 * qtdMudas; // 5 base + 2 por muda
    }

    @Override
    protected String getDetalhesEspecificos() {
        return "Tipo: Plantio de Mudas | Mudas: " + qtdMudas;
    }
}