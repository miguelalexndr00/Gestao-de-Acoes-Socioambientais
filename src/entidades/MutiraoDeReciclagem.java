package entidades;

import java.time.LocalDateTime;

public class MutiraoDeReciclagem extends Acao {
    private final int duracaoHoras;

    public MutiraoDeReciclagem(int id, String titulo, String descricao,
                               LocalDateTime data, int maxParticipantes, int duracaoHoras) {
        super(id, titulo, descricao, data, maxParticipantes);
        if (duracaoHoras <= 0) {
            throw new IllegalArgumentException("Duração deve ser maior que zero");
        }
        this.duracaoHoras = duracaoHoras;
    }

    public int getDuracaoHoras() { return duracaoHoras; }

    @Override
    public int calcularPontuacao() {
        return 4 * duracaoHoras;
    }

    @Override
    protected String getDetalhesEspecificos() {
        return "Tipo: Mutirão de Reciclagem | Duração: " + duracaoHoras + "h";
    }
}