package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.consulta.dtoDatosReservaConsulta;
import med.voll.api.infra.errores.ValidacionExeption;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
@Component
public class ValidadorFueraHorarioConsultas implements ValidadorDeConsultas {

    public void validar(dtoDatosReservaConsulta dtoDatosReservaConsulta){
        var fechaConsulta = dtoDatosReservaConsulta.fecha();
        var domingo = fechaConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY) ;
        var horarioAntesDeAperturaClinica = fechaConsulta.getHour() < 7 ;
        var horarioDespuesDeCierreClinica = fechaConsulta.getHour() > 18 ;
        if(domingo || horarioDespuesDeCierreClinica || horarioAntesDeAperturaClinica) {
            throw new ValidacionExeption("Horario seleccionado por fuera del horario en el que atiende la clienica") ;
        }
    }
}
