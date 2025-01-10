package med.voll.api.domain.consulta;

import jakarta.validation.Valid;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.errores.ValidacionExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaDeConsultas {


    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ConsultaRepository consultaRepository;


    public void reservar(dtoDatosReservaConsulta dtoDatosReservaConsulta) throws ValidacionExeption {


        if (!pacienteRepository.existsById(dtoDatosReservaConsulta.idPaciente())) {
            throw new ValidacionExeption("No existe un paciente con el id informado");
        }

        if (dtoDatosReservaConsulta.idMedico() != null && !medicoRepository.existsById(dtoDatosReservaConsulta.idMedico())) {
            throw new ValidacionExeption("No existe un médico con el id informado");
        }

        var medico = elegirMedico(dtoDatosReservaConsulta);
        var paciente = pacienteRepository.findById(dtoDatosReservaConsulta.idPaciente()).get();

        var consulta = new Consulta(null, medico, paciente, dtoDatosReservaConsulta.fecha() , null);
        consultaRepository.save(consulta);
    }

    private Medico elegirMedico(dtoDatosReservaConsulta dtoDatosReservaConsulta) {
        if (dtoDatosReservaConsulta.idMedico() != null) {
            return medicoRepository.getReferenceById(dtoDatosReservaConsulta.idMedico());
        }
        if (dtoDatosReservaConsulta.especialidad() == null) {
            throw new ValidacionExeption("Es necesario elegir una especialidad cuando no se elige un médico");

        }

        return medicoRepository.elegirMedicoAleatorioDisponibleEnLaFecha(dtoDatosReservaConsulta.especialidad(), dtoDatosReservaConsulta.fecha());
    }

    public void cancelar(dtoDatosCancelamientoConsulta datos) {
        if (!consultaRepository.existsById(datos.idConsulta())) {
            throw new ValidacionExeption("Id de la consulta informado no existe!");
        }
        var consulta = consultaRepository.getReferenceById(datos.idConsulta());
        consulta.cancelar(datos.motivo());
    }

}


