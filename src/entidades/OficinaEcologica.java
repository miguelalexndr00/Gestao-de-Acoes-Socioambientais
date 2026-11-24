package entidades;

import java.time.LocalDateTime;

// OficinaEcologica.java
public class OficinaEcologica extends Acao {
    private int duracaoHoras;
    private boolean kitMaterial;

    public OficinaEcologica(int id, String titulo, String descricao,
                            LocalDateTime data, int maxParticipantes,
                            int duracaoHoras, boolean kitMaterial) {
        super(id, titulo, descricao, data, maxParticipantes);
        this.duracaoHoras = duracaoHoras;
        this.kitMaterial = kitMaterial;
    }

    public int getDuracaoHoras() { return duracaoHoras; }
    public boolean temKitMaterial() { return kitMaterial; }

    @Override
    public int calcularPontuacao() {
        int pontos = 3 * duracaoHoras; // 3 por hora
        if (kitMaterial) pontos += 10; // bônus do kit
        return pontos;
    }

    @Override
    protected String getDetalhesEspecificos() {
        return "Tipo: Oficina Ecológica | Duração: " + duracaoHoras + "h | Kit: " + (kitMaterial ? "Sim" : "Não");
    }
}