package br.com.techchallenge.fiap.techchallenge.dtos;

import br.com.techchallenge.fiap.techchallenge.entities.Endereco;

public record UserUpdateRequestDTO(
        String nomeCompleto,
        String email
) {
}
