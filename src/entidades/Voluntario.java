package entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Voluntario {
    private final String nome;
    private final String email;
    private final String matricula;
    private final List<Acao> acoesParticipando = new ArrayList<>();

    public Voluntario(String nome, String email, String matricula) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("E-mail é obrigatório");
        }
        if (matricula == null || matricula.isBlank()) {
            throw new IllegalArgumentException("Matrícula é obrigatória");
        }
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

    public int getPontuacaoAcumulada() {
        int total = 0;
        for (Acao acao : acoesParticipando) {
            total += acao.calcularPontuacao();
        }
        return total;
    }

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