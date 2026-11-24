public class AcaoLotadaException extends RuntimeException {
    public AcaoLotadaException(int idAcao) {
        super("Ação " + idAcao + " já atingiu a capacidade máxima.");
    }
}