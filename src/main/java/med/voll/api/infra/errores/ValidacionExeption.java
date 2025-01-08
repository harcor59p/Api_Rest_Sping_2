package med.voll.api.infra.errores;

public class ValidacionExeption extends Throwable {
    public ValidacionExeption(String mensaje) {
        super(mensaje);
    }
}
