package med.voll.api.infra.errores;

public class ValidacionExeption extends RuntimeException {
    public ValidacionExeption(String mensaje) {
        super(mensaje);
    }
}
