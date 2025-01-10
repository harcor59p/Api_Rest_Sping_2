package med.voll.api.domain.consulta;

import jakarta.validation.constraints.NotNull;

public record dtoDatosCancelamientoConsulta(
        @NotNull
        Long idConsulta,
        @NotNull
        MotivoCancelamiento motivo) {
}
