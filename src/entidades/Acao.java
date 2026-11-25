package entidades;

import excecoes.AcaoLotadaException;
import excecoes.InscricaoDuplicadaException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Acao {
    private final int id;
    private final String titulo;
    private final String descricao;
    private final LocalDateTime data;
    private final int maxParticipantes;
    private final List<Voluntario> inscritos = new ArrayList<>();

    public Acao(int id, String titulo, String descricao, LocalDateTime data, int maxParticipantes) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título é obrigatório");
        }
        if (data == null) {
            throw new IllegalArgumentException("Data é obrigatória");
        }
        if (maxParticipantes <= 0) {
            throw new IllegalArgumentException("Capacidade máxima deve ser maior que zero");
        }
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.maxParticipantes = maxParticipantes;
    }

    public abstract int calcularPontuacao();

    protected abstract String getDetalhesEspecificos();

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public LocalDateTime getData() { return data; }
    public int getMaxParticipantes() { return maxParticipantes; }
    public List<Voluntario> getInscritos() { return new ArrayList<>(inscritos); }

    public boolean estaLotada() {
        return inscritos.size() >= maxParticipantes;
    }

    public boolean jaInscrito(Voluntario v) {
        return inscritos.contains(v);
    }

    public void inscrever(Voluntario v) {
        if (jaInscrito(v)) {
            throw new InscricaoDuplicadaException(
                    "Voluntário " + v.getEmail() + " já inscrito na ação " + id);
        }
        if (estaLotada()) {
            throw new AcaoLotadaException("Ação " + id + " está lotada");
        }
        inscritos.add(v);
        v.adicionarAcao(this);
    }

    public String exibirDetalhes() {
        String dataFormatada = data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        List<String> nomes = new ArrayList<>();
        for (Voluntario v : inscritos) nomes.add(v.getNome());

        return "ID: " + id + " | Título: " + titulo
                + "\nDescrição: " + descricao
                + "\nData: " + dataFormatada + " | Pontuação: " + calcularPontuacao()
                + "\n" + getDetalhesEspecificos()
                + "\nInscritos: " + (nomes.isEmpty() ? "nenhum" : String.join("; ", nomes));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Acao)) return false;
        return id == ((Acao) o).id;
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}