package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.consulta.dtoDatosReservaConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.infra.errores.ValidacionExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoActivo implements ValidadorDeConsultas {

    @Autowired
    private MedicoRepository medicoRepository ;

    public void validar(dtoDatosReservaConsulta dtoDatosReservaConsulta) {

        // eleccion del medico opcional
        if(dtoDatosReservaConsulta.idMedico() == null){
            return;
        }
        var medicoEsActivo = medicoRepository.findActivoById(dtoDatosReservaConsulta.idMedico());
        if(!medicoEsActivo){
            throw new ValidacionExeption("Consulta no se puede reservar con un medico inactivo") ;
        }
    }
}
