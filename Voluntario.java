import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Voluntario {
    private String nome;
    private final String email; // chave de identificação, não muda
    private String matricula;
    private final List<Acao> acoesParticipando = new ArrayList<>();

    public Voluntario(String nome, String email, String matricula) {
        this.nome = nome;
        this.email = email;
        this.matricula = matricula;
    }

    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getMatricula() { return matricula; }

    public int getQuantidadeAcoes() {
        return acoesParticipando.size();
    }

    // Pontuação acumulada = soma da pontuação de cada ação que participa
    public int getPontuacaoAcumulada() {
        int total = 0;
        for (Acao acao : acoesParticipando) {
            total += acao.calcularPontuacao(); // aqui usa o polimorfismo
        }
        return total;
    }

    // Chamado pela Acao quando a inscrição é aceita
    void adicionarAcao(Acao acao) {
        if (!acoesParticipando.contains(acao)) {
            acoesParticipando.add(acao);
        }
    }

    public String exibirDetalhes() {
        return "Nome: " + nome +
                " | Ações: " + getQuantidadeAcoes() +
                " | Pontuação: " + getPontuacaoAcumulada();
    }

    // Dois voluntários são iguais se tiverem o mesmo e-mail
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Voluntario)) return false;
        Voluntario outro = (Voluntario) o;
        return email.equalsIgnoreCase(outro.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email.toLowerCase());
    }
}