package br.com.techchallenge.fiap.techChallenge.dtos;

public record UserUpdateRequestDTO(
        String nome,
        String email,
        String endereco
) {
}
