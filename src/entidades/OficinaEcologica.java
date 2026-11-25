package entidades;

import java.time.LocalDateTime;

public class OficinaEcologica extends Acao {
    private final int duracaoHoras;
    private final boolean kitMaterial;

    public OficinaEcologica(int id, String titulo, String descricao,
                            LocalDateTime data, int maxParticipantes,
                            int duracaoHoras, boolean kitMaterial) {
        super(id, titulo, descricao, data, maxParticipantes);
        if (duracaoHoras <= 0) {
            throw new IllegalArgumentException("Duração deve ser maior que zero");
        }
        this.duracaoHoras = duracaoHoras;
        this.kitMaterial = kitMaterial;
    }

    public int getDuracaoHoras() { return duracaoHoras; }
    public boolean temKitMaterial() { return kitMaterial; }

    @Override
    public int calcularPontuacao() {
        int pontos = 3 * duracaoHoras;
        if (kitMaterial) pontos += 10;
        return pontos;
    }

    @Override
    protected String getDetalhesEspecificos() {
        return "Tipo: Oficina Ecológica | Duração: " + duracaoHoras + "h | Kit: "
                + (kitMaterial ? "Sim" : "Não");
    }
}