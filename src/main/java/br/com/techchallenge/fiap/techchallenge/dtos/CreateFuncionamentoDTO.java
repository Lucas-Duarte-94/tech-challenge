package br.com.techchallenge.fiap.techchallenge.dtos;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record CreateFuncionamentoDTO(
        DayOfWeek diaSemana,
        LocalTime horaAbertura,
        LocalTime horaFechamento,
        Long restauranteId
) {
}
