package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.consulta.dtoDatosReservaConsulta;
import med.voll.api.infra.errores.ValidacionExeption;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorConsultaConAnticipacion implements ValidadorDeConsultas {

    public void validar(dtoDatosReservaConsulta dtoDatosReservaConsulta){
        var fechaConsulta = dtoDatosReservaConsulta.fecha();
        var ahora = LocalDateTime.now();
        var diferenciaEnMinutos = Duration.between(ahora , fechaConsulta).toMinutes() ;
        if(diferenciaEnMinutos < 30){
            throw new ValidacionExeption("Horario seleccionado con menos de 30 minustos de anticipacion") ;
        }
    }
}
