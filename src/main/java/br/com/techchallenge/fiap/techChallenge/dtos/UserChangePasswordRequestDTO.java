package br.com.techchallenge.fiap.techChallenge.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record UserChangePasswordRequestDTO(
        @NotNull
        String login,
        @Schema(description = "Senha atual")
        @NotNull
        String senha,
        @Schema(description = "Nova senha")
        @NotNull
        String novaSenha
) {
}
