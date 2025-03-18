package br.com.techchallenge.fiap.techchallenge.dtos;

import br.com.techchallenge.fiap.techchallenge.entities.Endereco;
import br.com.techchallenge.fiap.techchallenge.entities.TipoUsuario;

import java.time.OffsetDateTime;

public record UsuarioPublicDTO(
        Long id,
        String nomeCompleto,
        String email,
        String login,
        OffsetDateTime dataAtualizacao,
        Endereco endereco,
        TipoUsuario tipoUsuario
) {
}
