package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.errores.ValidacionExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaDeConsultas {


    @Autowired
    private MedicoRepository medicoRepository ;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ConsultaRepository consultaRepository;


    public void reservar(dtoDatosReservaConsulta dtoDatosReservaConsulta) throws ValidacionExeption {


        if(!pacienteRepository.existsById(dtoDatosReservaConsulta.idPaciente())){
            throw new ValidacionExeption("No existe un paciente con el id informado") ;
        }

        if(dtoDatosReservaConsulta.idMedico() != null && !medicoRepository.existsById(dtoDatosReservaConsulta.idMedico())){
            throw new ValidacionExeption("No existe un médico con el id informado") ;
        }

        var medico = elegirMedico(dtoDatosReservaConsulta);
        var paciente = pacienteRepository.findById(dtoDatosReservaConsulta.idPaciente()).get() ;

        var consulta = new Consulta(null , medico , paciente , dtoDatosReservaConsulta.fecha()) ;
        consultaRepository.save(consulta) ;
    }

    private Medico elegirMedico(dtoDatosReservaConsulta dtoDatosReservaConsulta) {
    }

}
