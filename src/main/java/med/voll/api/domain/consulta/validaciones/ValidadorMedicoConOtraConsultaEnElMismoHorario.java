package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.dtoDatosReservaConsulta;
import med.voll.api.infra.errores.ValidacionExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoConOtraConsultaEnElMismoHorario {

    @Autowired
    private ConsultaRepository consultaRepository ;

    public void validar(dtoDatosReservaConsulta dtoDatosReservaConsulta){
        var medicoTieneOtraConsultaEnElMismoHorario = consultaRepository.existsByMedicoIdAndFecha(dtoDatosReservaConsulta.idMedico() , dtoDatosReservaConsulta.fecha()) ;
        if(medicoTieneOtraConsultaEnElMismoHorario){
            throw new ValidacionExeption("Medico ya tiene otra consulta en esa misma fecha y hora") ;
        }
    }
}
