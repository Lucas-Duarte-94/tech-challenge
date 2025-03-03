package br.com.techchallenge.fiap.techchallenge.dtos;

public record UserUpdateRequestDTO(
        String nome,
        String email,
        String endereco
) {
}
