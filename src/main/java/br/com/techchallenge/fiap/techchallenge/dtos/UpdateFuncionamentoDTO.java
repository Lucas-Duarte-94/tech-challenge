package br.com.techchallenge.fiap.techchallenge.dtos;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record UpdateFuncionamentoDTO(
        DayOfWeek diaSemana,
        LocalTime horaAbertura,
        LocalTime horaFechamento,
        Long restauranteId,
        Long usuarioId
) {
}
