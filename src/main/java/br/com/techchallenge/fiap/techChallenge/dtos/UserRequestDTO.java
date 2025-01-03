package br.com.techchallenge.fiap.techChallenge.dtos;

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
        @NotNull
        String type
) {
}
