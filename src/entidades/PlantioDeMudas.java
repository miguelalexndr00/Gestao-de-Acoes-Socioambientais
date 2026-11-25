package entidades;

import java.time.LocalDateTime;

public class PlantioDeMudas extends Acao {
    private final int qtdMudas;

    public PlantioDeMudas(int id, String titulo, String descricao,
                          LocalDateTime data, int maxParticipantes, int qtdMudas) {
        super(id, titulo, descricao, data, maxParticipantes);
        if (qtdMudas < 0) {
            throw new IllegalArgumentException("Quantidade de mudas não pode ser negativa");
        }
        this.qtdMudas = qtdMudas;
    }

    public int getQtdMudas() { return qtdMudas; }

    @Override
    public int calcularPontuacao() {
        return 5 + 2 * qtdMudas;
    }

    @Override
    protected String getDetalhesEspecificos() {
        return "Tipo: Plantio de Mudas | Mudas: " + qtdMudas;
    }
}