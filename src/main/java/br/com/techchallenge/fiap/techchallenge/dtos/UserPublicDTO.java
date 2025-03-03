package br.com.techchallenge.fiap.techchallenge.dtos;

import java.util.Date;

public record UserPublicDTO(
        String id,
        String nome,
        String email,
        String login,
        Date ultimaAlteracao,
        String endereco,
        String type
) {
}
