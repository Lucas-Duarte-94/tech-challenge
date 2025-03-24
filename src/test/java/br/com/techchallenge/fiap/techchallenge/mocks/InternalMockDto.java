package br.com.techchallenge.fiap.techchallenge.mocks;

import br.com.techchallenge.fiap.techchallenge.dtos.CardapioResponseDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.CreateEnderecoDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.CreateFuncionamentoDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.CreateItemCardapioDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.CreateRestauranteDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.CreateUsuarioDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.DeleteRestauranteDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.UpdateEnderecoDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.UpdateFuncionamentoDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.UpdateItemCardapioDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.UpdateRestauranteRequestDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.UserChangePasswordRequestDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.UserLoginRequestDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.UserUpdateRequestDTO;
import br.com.techchallenge.fiap.techchallenge.enums.TipoUsuarioEnum;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
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
                3L
        );
    }

    public static CreateRestauranteDTO getCreateRestauranteDTO() {
        return new CreateRestauranteDTO(
                "Restaurante Saboroso",
                "Italiana",
               getEnderecoDto(),
                2L
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

    public static CardapioResponseDTO getCardapioResponseDto() {
        return new CardapioResponseDTO(InternalMock.getCardapio(), List.of(InternalMock.getItemCardapio()));
    }

    public static CreateRestauranteDTO getCreateRestauranteDto() {
        return new CreateRestauranteDTO("nomeRestaurante", "cozinha", getEnderecoDto(), 1L);
    }

    public static UpdateRestauranteRequestDTO getUpdateRestauranteRequestDTO() {
        return new UpdateRestauranteRequestDTO("nomeRestaurante", "cozinha", 2L);
    }

    public static UpdateItemCardapioDTO getUpdateItemCardapioDTO() {
        return new UpdateItemCardapioDTO(
                "Item Atualizado",
                "Descrição Atualizada",
                BigDecimal.valueOf(29.99),
                "url_foto_atualizada",
                false
        );
    }

    public static DeleteRestauranteDTO getDeleteRestauranteDto() {
        return new DeleteRestauranteDTO(2L);
    }

    public static CreateUsuarioDTO getCreateUsuarioDTO() {
        return new CreateUsuarioDTO(
                "João Silva",
                "joao.silva@email.com",
                "joao_pedro",
                "senhaSegura1234",
                getCreateEnderecoDTO(),
                TipoUsuarioEnum.CLIENTE
        );
    }

    public static UserLoginRequestDTO getUserLoginRequestDTO() {
        return new UserLoginRequestDTO(
                "ana_souza",
                "senha456"
        );
    }

    public static UserUpdateRequestDTO getUserUpdateRequestDTO() {
        return new UserUpdateRequestDTO(
                "João Silva Atualizado",
                "joao.atualizado@email.com"
        );
    }

    public static UserChangePasswordRequestDTO getUserChangePasswordRequestDTO() {
        return new UserChangePasswordRequestDTO(
                "joao_pedro_5",
                "senha789",
                "novaSenhaForte456"
        );
    }

    public static CreateEnderecoDTO getCreateEnderecoDTO() {
        return new CreateEnderecoDTO(
                "Rua Exemplo",
                "123",
                "Apto 101",
                "Bairro Teste",
                "Cidade Teste",
                "Estado Teste",
                "12345-678",
                Optional.of(2L),
                Optional.empty()
        );
    }

    public static UpdateEnderecoDTO getUpdateEnderecoDTO() {
        return new UpdateEnderecoDTO(
                "Rua Atualizada",
                "456",
                "Casa Fundos",
                "Bairro Atualizado",
                "Cidade Atualizada",
                "Estado Atualizado",
                "98765-432",
                2L,
                Optional.empty()
        );
    }
}
