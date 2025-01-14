package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.dtoDatosReservaConsulta;
import med.voll.api.infra.errores.ValidacionExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadprPacienteSinOtraConsultaEnElMismoDia implements ValidadorDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(dtoDatosReservaConsulta dtoDatosReservaConsulta){
        var primerHorario = dtoDatosReservaConsulta.fecha().withHour(7) ;
        var ultimoHorario = dtoDatosReservaConsulta.fecha().withHour(18);
        var pacienteTieneOtraConsultaEnElDia = consultaRepository.existsByPacienteIdAndFechaBetween(dtoDatosReservaConsulta.idPaciente() , primerHorario , ultimoHorario  );
        if(pacienteTieneOtraConsultaEnElDia){
            throw new ValidacionExeption("Paciente ya tiene una consulta reservada para ese día") ;
        }
    }
}
