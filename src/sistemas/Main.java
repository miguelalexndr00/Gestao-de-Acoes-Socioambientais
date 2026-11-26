package sistemas;

import java.util.Scanner;

public class Main {

    private static final Scanner in = new Scanner(System.in);
    private static final Impacta impacta = new Impacta();

    public static void main(String[] args) {
        int opcao;
        do {
            menu();
            opcao = lerInt("Opção: ");
            try {
                executar(opcao);
            } catch (RuntimeException e) {
                System.out.println(">> Erro: " + e.getMessage());
            }
        } while (opcao != 0);
        System.out.println("Até mais!");
    }

    private static void menu() {
        System.out.println("\n===== IMPACTA - Ações Socioambientais =====");
        System.out.println("1 - Cadastrar voluntário");
        System.out.println("2 - Listar voluntários (ranking por pontuação)");
        System.out.println("3 - Exibir voluntário");
        System.out.println("4 - Cadastrar plantio de mudas");
        System.out.println("5 - Cadastrar mutirão de reciclagem");
        System.out.println("6 - Cadastrar oficina ecológica");
        System.out.println("7 - Inscrever voluntário em ação");
        System.out.println("8 - Exibir detalhes de ação");
        System.out.println("9 - Carregar dados de exemplo");
        System.out.println("0 - Sair");
    }

    private static void executar(int opcao) {
        switch (opcao) {
            case 1 -> {
                impacta.cadastrarVoluntario(lerTexto("Nome: "), lerTexto("E-mail: "), lerTexto("Matrícula: "));
                System.out.println("Voluntário cadastrado!");
            }
            case 2 -> {
                String[] lista = impacta.listarVoluntarios();
                if (lista.length == 0) System.out.println("Nenhum voluntário cadastrado.");
                for (int i = 0; i < lista.length; i++) System.out.println((i + 1) + "º " + lista[i]);
            }
            case 3 -> System.out.println(impacta.exibirVoluntario(lerTexto("E-mail: ")));
            case 4 -> {
                int id = impacta.cadastrarPlantio(lerTexto("Título: "), lerTexto("Descrição: "),
                        lerTexto("Data (05/12/2026 09:00): "), lerInt("Máx. participantes: "),
                        lerInt("Qtd. de mudas: "));
                System.out.println("Plantio cadastrado! ID: " + id);
            }
            case 5 -> {
                int id = impacta.cadastrarMutirao(lerTexto("Título: "), lerTexto("Descrição: "),
                        lerTexto("Data (05/12/2026 09:00): "), lerInt("Máx. participantes: "),
                        lerInt("Duração (horas): "));
                System.out.println("Mutirão cadastrado! ID: " + id);
            }
            case 6 -> {
                int id = impacta.cadastrarOficina(lerTexto("Título: "), lerTexto("Descrição: "),
                        lerTexto("Data (05/12/2026 09:00): "), lerInt("Máx. participantes: "),
                        lerInt("Duração (horas): "), lerTexto("Tem kit de material? (s/n): ").equalsIgnoreCase("s"));
                System.out.println("Oficina cadastrada! ID: " + id);
            }
            case 7 -> {
                impacta.inscreverVoluntario(lerTexto("E-mail do voluntário: "), lerInt("ID da ação: "));
                System.out.println("Inscrição realizada!");
            }
            case 8 -> System.out.println(impacta.exibirDetalhesAcao(lerInt("ID da ação: ")));
            case 9 -> carregarExemplo();
            case 0 -> { }
            default -> System.out.println("Opção inválida.");
        }
    }

    private static void carregarExemplo() {
        impacta.cadastrarVoluntario("Miguel", "miguel@email.com", "123");
        impacta.cadastrarVoluntario("Ana", "ana@email.com", "456");
        impacta.cadastrarVoluntario("Bruno", "bruno@email.com", "789");
        int plantio = impacta.cadastrarPlantio("Plantio Comunitário", "Plantio de árvores nativas",
                "05/12/2026 09:00", 2, 20);
        int mutirao = impacta.cadastrarMutirao("Mutirão Comunitário", "Coleta de recicláveis",
                "06/12/2026 08:00", 20, 5);
        int oficina = impacta.cadastrarOficina("Oficina de Reciclagem", "Aprendendo a reciclar",
                "07/12/2026 14:00", 15, 3, true);
        impacta.inscreverVoluntario("miguel@email.com", plantio);
        impacta.inscreverVoluntario("ana@email.com", plantio);
        impacta.inscreverVoluntario("miguel@email.com", mutirao);
        impacta.inscreverVoluntario("ana@email.com", oficina);
        System.out.println("Dados de exemplo carregados (ações " + plantio + ", " + mutirao + " e " + oficina
                + "). O plantio já está lotado (2/2): tente inscrever o Bruno nele.");
    }

    private static String lerTexto(String rotulo) {
        System.out.print(rotulo);
        return in.nextLine();
    }

    private static int lerInt(String rotulo) {
        while (true) {
            System.out.print(rotulo);
            try {
                return Integer.parseInt(in.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro.");
            }
        }
    }
}
