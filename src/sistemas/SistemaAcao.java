package sistemas;

import entidades.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedHashMap;
import java.util.Map;

public class SistemaAcao {

    private static final DateTimeFormatter FORMATO_BR =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private final Map<Integer, Acao> acoes = new LinkedHashMap<>();
    private int proximoId = 1;

    public int cadastrarPlantio(String titulo, String descricao, String data,
                                int maxParticipantes, int qtdMudas) {
        return registrar(new PlantioDeMudas(proximoId, titulo, descricao,
                converterData(data), maxParticipantes, qtdMudas));
    }

    public int cadastrarMutirao(String titulo, String descricao, String data,
                                int maxParticipantes, int duracaoHoras) {
        return registrar(new MutiraoDeReciclagem(proximoId, titulo, descricao,
                converterData(data), maxParticipantes, duracaoHoras));
    }

    public int cadastrarOficina(String titulo, String descricao, String data,
                                int maxParticipantes, int duracaoHoras, boolean kitMaterial) {
        return registrar(new OficinaEcologica(proximoId, titulo, descricao,
                converterData(data), maxParticipantes, duracaoHoras, kitMaterial));
    }

    public boolean inscreverVoluntario(Voluntario voluntario, int idAcao) {
        buscarAcao(idAcao).inscrever(voluntario); 
        return true;
    }

    public String exibirDetalhesAcao(int idAcao) {
        return buscarAcao(idAcao).exibirDetalhes();
    }

    public Acao buscarAcao(int id) {
        Acao a = acoes.get(id);
        if (a == null) {
            throw new IllegalArgumentException("Ação não encontrada: " + id);
        }
        return a;
    }

    private int registrar(Acao acao) {
        acoes.put(acao.getId(), acao);
        proximoId++;
        return acao.getId();
    }

    private LocalDateTime converterData(String data) {
        if (data == null || data.isBlank()) {
            throw new IllegalArgumentException("Data é obrigatória");
        }
        String d = data.trim();
        try {
            return LocalDateTime.parse(d);
        } catch (DateTimeParseException e) {
            try {
                return LocalDateTime.parse(d, FORMATO_BR);
            } catch (DateTimeParseException e2) {
                throw new IllegalArgumentException("Data inválida: " + data
                        + " (use 2026-12-05T09:00 ou 05/12/2026 09:00)");
            }
        }
    }
}
