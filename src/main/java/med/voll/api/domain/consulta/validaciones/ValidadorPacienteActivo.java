package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.consulta.dtoDatosReservaConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.errores.ValidacionExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class ValidadorPacienteActivo implements ValidadorDeConsultas {

    @Autowired
    private PacienteRepository pacienteRepository ;

    public void validar(dtoDatosReservaConsulta dtoDatosReservaConsulta){
        var pacienteEstaActivo = pacienteRepository.findActivoById(dtoDatosReservaConsulta.idPaciente());
        if(!pacienteEstaActivo){
            throw  new ValidacionExeption("Consulta no puede ser reservadsa con paciente inactivo") ;
        }
    }
}
