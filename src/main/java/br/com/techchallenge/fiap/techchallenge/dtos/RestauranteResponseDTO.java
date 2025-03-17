package br.com.techchallenge.fiap.techchallenge.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestauranteResponseDTO {
	private Long idFuncionamento;

	private DayOfWeek diaSemana;

	private LocalTime horaAbertura;

	private LocalTime horaFechamento;

	private RestauranteDTO restaurante;
}
