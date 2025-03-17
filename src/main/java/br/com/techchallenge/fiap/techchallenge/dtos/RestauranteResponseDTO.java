package br.com.techchallenge.fiap.techchallenge.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestauranteResponseDTO {
	private String nomeRestaurante;

	private String tipoCozinha;

	private List<FuncionamentoDTO> funcionamentoRestaurante;

//	private UsuarioResponseDTO usuario;
}
