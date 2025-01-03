package br.com.techchallenge.fiap.techChallenge.dtos;

import jakarta.validation.constraints.NotNull;

public record UserChangePasswordRequestDTO(
        @NotNull
        String login,
        @NotNull
        String senha,
        @NotNull
        String novaSenha
) {
}
