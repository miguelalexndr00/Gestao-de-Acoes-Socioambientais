public class InscricaoDuplicadaException extends RuntimeException {
    public InscricaoDuplicadaException(String email, int idAcao) {
        super("Voluntário " + email + " já está inscrito na ação " + idAcao);
    }
}