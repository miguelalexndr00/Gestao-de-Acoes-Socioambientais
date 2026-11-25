package sistemas;

public class Impacta {

    private final SistemaVoluntario sistemaVoluntario = new SistemaVoluntario();
    private final SistemaAcao sistemaAcao = new SistemaAcao();


    public boolean cadastrarVoluntario(String nome, String email, String matricula) {
        return sistemaVoluntario.cadastrarVoluntario(nome, email, matricula);
    }

    public String exibirVoluntario(String email) {
        return sistemaVoluntario.exibirVoluntario(email);
    }

    public String[] listarVoluntarios() {
        return sistemaVoluntario.listarVoluntarios();
    }


    public int cadastrarPlantio(String titulo, String descricao, String data,
                                int maxParticipantes, int qtdMudas) {
        return sistemaAcao.cadastrarPlantio(titulo, descricao, data, maxParticipantes, qtdMudas);
    }

    public int cadastrarMutirao(String titulo, String descricao, String data,
                                int maxParticipantes, int duracaoHoras) {
        return sistemaAcao.cadastrarMutirao(titulo, descricao, data, maxParticipantes, duracaoHoras);
    }

    public int cadastrarOficina(String titulo, String descricao, String data,
                                int maxParticipantes, int duracaoHoras, boolean kitMaterial) {
        return sistemaAcao.cadastrarOficina(titulo, descricao, data, maxParticipantes,
                duracaoHoras, kitMaterial);
    }

    public boolean inscreverVoluntario(String emailVoluntario, int idAcao) {
        return sistemaAcao.inscreverVoluntario(
                sistemaVoluntario.buscarVoluntario(emailVoluntario), idAcao);
    }

    public String exibirDetalhesAcao(int idAcao) {
        return sistemaAcao.exibirDetalhesAcao(idAcao);
    }
}