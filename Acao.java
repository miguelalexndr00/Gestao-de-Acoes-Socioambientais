import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Acao {
    private final int id;
    private String titulo;
    private String descricao;
    private LocalDateTime data;
    private int maxParticipantes;
    private final List<Voluntario> inscritos = new ArrayList<>();

    public Acao(int id, String titulo, String descricao, LocalDateTime data, int maxParticipantes) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.maxParticipantes = maxParticipantes;
    }

    // MÉTODO ABSTRATO: cada filha calcula de um jeito. É o polimorfismo.
    public abstract int calcularPontuacao();

    // Cada filha completa com seus atributos específicos
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
            throw new InscricaoDuplicadaException(v.getEmail(), id);
        }
        if (estaLotada()) {
            throw new AcaoLotadaException(id);
        }
        inscritos.add(v);
        v.adicionarAcao(this); // mantém os dois lados sincronizados
    }

    public String exibirDetalhes() {
        String dataFormatada = data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id).append(" | Título: ").append(titulo);
        sb.append("\nDescrição: ").append(descricao);
        sb.append("\nData: ").append(dataFormatada);
        sb.append(" | Pontuação: ").append(calcularPontuacao());
        sb.append("\n").append(getDetalhesEspecificos());
        sb.append("\nInscritos: ");
        if (inscritos.isEmpty()) sb.append("nenhum");
        else {
            for (Voluntario v : inscritos) sb.append(v.getNome()).append("; ");
        }
        return sb.toString();
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