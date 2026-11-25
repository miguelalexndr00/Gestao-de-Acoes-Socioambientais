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
        int id = proximoId++;
        acoes.put(id, new PlantioDeMudas(id, titulo, descricao,
                converterData(data), maxParticipantes, qtdMudas));
        return id;
    }

    public int cadastrarMutirao(String titulo, String descricao, String data,
                                int maxParticipantes, int duracaoHoras) {
        int id = proximoId++;
        acoes.put(id, new MutiraoDeReciclagem(id, titulo, descricao,
                converterData(data), maxParticipantes, duracaoHoras));
        return id;
    }

    public int cadastrarOficina(String titulo, String descricao, String data,
                                int maxParticipantes, int duracaoHoras, boolean kitMaterial) {
        int id = proximoId++;
        acoes.put(id, new OficinaEcologica(id, titulo, descricao,
                converterData(data), maxParticipantes, duracaoHoras, kitMaterial));
        return id;
    }

    public boolean inscreverVoluntario(Voluntario voluntario, int idAcao) {
        buscarAcao(idAcao).inscrever(voluntario); // lança as exceções de lotada/duplicada
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

    private LocalDateTime converterData(String data) {
        try {
            return LocalDateTime.parse(data);
        } catch (DateTimeParseException e) {
            try {
                return LocalDateTime.parse(data, FORMATO_BR);
            } catch (DateTimeParseException e2) {
                throw new IllegalArgumentException("Data inválida: " + data
                        + " (use 2026-12-05T09:00 ou 05/12/2026 09:00)");
            }
        }
    }
}