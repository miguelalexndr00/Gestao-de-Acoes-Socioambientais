package entidades;

// MutiraoDeReciclagem.java
import java.time.LocalDateTime;

public class MutiraoDeReciclagem extends Acao {
    private int duracaoHoras;

    public MutiraoDeReciclagem(int id, String titulo, String descricao,
                               LocalDateTime data, int maxParticipantes, int duracaoHoras) {
        super(id, titulo, descricao, data, maxParticipantes);
        this.duracaoHoras = duracaoHoras;
    }

    public int getDuracaoHoras() { return duracaoHoras; }

    @Override
    public int calcularPontuacao() {
        return 4 * duracaoHoras; // 4 pontos por hora
    }

    @Override
    protected String getDetalhesEspecificos() {
        return "Tipo: Mutirão de Reciclagem | Duração: " + duracaoHoras + "h";
    }
}