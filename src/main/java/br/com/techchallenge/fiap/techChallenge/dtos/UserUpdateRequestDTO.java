package br.com.techchallenge.fiap.techChallenge.dtos;

import jakarta.validation.constraints.NotNull;

public record UserUpdateRequestDTO(
        @NotNull
        String login,
        String nome,
        String email,
        String endereco
) {
}
