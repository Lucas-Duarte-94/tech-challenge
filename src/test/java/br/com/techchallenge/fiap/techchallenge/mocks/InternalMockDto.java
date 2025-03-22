package br.com.techchallenge.fiap.techchallenge.mocks;

import br.com.techchallenge.fiap.techchallenge.dtos.CreateEnderecoDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.CreateFuncionamentoDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.CreateItemCardapioDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.CreateRestauranteDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.UpdateEnderecoDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.UpdateFuncionamentoDTO;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Optional;

public class InternalMockDto {

    public static UpdateEnderecoDTO getUpdateEnderecoDto(){
        return  new UpdateEnderecoDTO(
                "Rua Exemplo",
                "123",
                "Apto 101",
                "Centro",
                "Cidade Exemplo",
                "Estado Exemplo",
                "12345-678",
                1L,
                Optional.of(2L)
        );
    }

    public static CreateFuncionamentoDTO getCreateFuncionamentoDTO(){
        DayOfWeek diaSemana = DayOfWeek.MONDAY;
        LocalTime horaAbertura = LocalTime.of(10, 0);
        LocalTime horaFechamento = LocalTime.of(22, 0);
        Long restauranteId = 1L;
        Long usuarioId = 1L;

        return new CreateFuncionamentoDTO(
                diaSemana, horaAbertura, horaFechamento, restauranteId, usuarioId);

    }

    public static UpdateFuncionamentoDTO getUpdateFuncionamentoDTO(){
        DayOfWeek diaSemana = DayOfWeek.MONDAY;
        LocalTime horaAbertura = LocalTime.of(10, 0);
        LocalTime horaFechamento = LocalTime.of(22, 0);
        Long restauranteId = 1L;
        Long usuarioId = 1L;

        return new UpdateFuncionamentoDTO(
                diaSemana, horaAbertura, horaFechamento, restauranteId, usuarioId);

    }

    public static CreateItemCardapioDTO getCreateItemCardapioDTO() {
        return new CreateItemCardapioDTO(
                "Pizza Margherita",
                "Pizza tradicional com molho de tomate, queijo e manjericão.",
                new BigDecimal("29.90"),
                "url_da_foto_da_pizza.jpg",
                true,
                1L
        );
    }

    public static CreateRestauranteDTO getCreateDTO() {
        return new CreateRestauranteDTO(
                "Restaurante Saboroso",
                "Italiana",
               getEnderecoDto(),
                1L
                );
    }

    public static CreateEnderecoDTO getEnderecoDto() {
        return new CreateEnderecoDTO(
                "Avenida Paulista",
                "1000",
                "Apto 101",
                "Bela Vista",
                "São Paulo",
                "SP",
                "01310-100",
                Optional.of(1L),
                Optional.empty()
        );
    }
}
