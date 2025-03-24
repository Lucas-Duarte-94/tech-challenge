package br.com.techchallenge.fiap.techchallenge.dtos;

import br.com.techchallenge.fiap.techchallenge.enums.TipoUsuarioEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record CreateUsuarioDTO (
    @NotNull
    String nomeCompleto,
    @NotNull
    String email,
    @NotNull
    String login,
    @NotNull
    String senha,
    @NotNull
    CreateEnderecoDTO endereco,
    @Schema(description = "'cliente', 'dono_restaurante' ou 'admin'")
    @NotNull
    TipoUsuarioEnum tipoUsuario
) {
}
