package br.com.techchallenge.fiap.techchallenge.dtos;

import br.com.techchallenge.fiap.techchallenge.enums.UserEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(
        @NotNull
        String nome,
        @NotNull
        String email,
        @NotNull
        String login,
        @NotNull
        String senha,
        String endereco,
        @Schema(description = "'client' ou 'restaurant_owner'")
        @NotNull
        UserEnum type
) {
}
