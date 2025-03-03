package br.com.techchallenge.fiap.techchallenge.dtos;

import jakarta.validation.constraints.NotNull;

public record UserLoginRequestDTO(
        @NotNull
        String login,
        @NotNull
        String senha
) {
}
