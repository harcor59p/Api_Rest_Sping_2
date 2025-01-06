package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

public record dtoDatosDetalleConsulta(Long id , Long idMedico , Long idPaciente, LocalDateTime fecha) {
}
