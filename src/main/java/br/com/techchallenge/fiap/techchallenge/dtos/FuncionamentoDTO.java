package br.com.techchallenge.fiap.techchallenge.dtos;

import br.com.techchallenge.fiap.techchallenge.entities.Restaurante;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.OffsetDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FuncionamentoDTO {
	private Long idFuncionamento;

	private DayOfWeek diaSemana;

	private LocalTime horaAbertura;

	private LocalTime horaFechamento;

	private RestauranteDTO restaurante;
}
