package sistemas;

import entidades.Voluntario;
import excecoes.EmailDuplicadoException;

import java.util.*;

public class SistemaVoluntario {

    private final Map<String, Voluntario> voluntarios = new HashMap<>();

    public boolean cadastrarVoluntario(String nome, String email, String matricula) {
        String chave = email.toLowerCase();
        if (voluntarios.containsKey(chave)) {
            throw new EmailDuplicadoException("E-mail já cadastrado: " + email);
        }
        voluntarios.put(chave, new Voluntario(nome, email, matricula));
        return true;
    }

    public String exibirVoluntario(String email) {
        return buscarVoluntario(email).exibirDetalhes();
    }

    public String[] listarVoluntarios() {
        List<Voluntario> lista = new ArrayList<>(voluntarios.values());
        lista.sort(Comparator
                .comparingInt(Voluntario::getPontuacaoAcumulada).reversed()
                .thenComparing(Voluntario::getNome, String.CASE_INSENSITIVE_ORDER));

        String[] resultado = new String[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            resultado[i] = lista.get(i).exibirDetalhes();
        }
        return resultado;
    }

    public Voluntario buscarVoluntario(String email) {
        Voluntario v = voluntarios.get(email.toLowerCase());
        if (v == null) {
            throw new IllegalArgumentException("Voluntário não encontrado: " + email);
        }
        return v;
    }
}