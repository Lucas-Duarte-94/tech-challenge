package br.com.techchallenge.fiap.techChallenge.dtos;

import jakarta.validation.constraints.NotNull;

public record UserLoginRequestDTO(
        @NotNull
        String login,
        @NotNull
        String senha
) {
}
