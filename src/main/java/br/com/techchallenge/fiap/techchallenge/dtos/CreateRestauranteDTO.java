package br.com.techchallenge.fiap.techchallenge.dtos;


public record CreateRestauranteDTO(
        String nomeRestaurante,
        String tipoCozinha,
        CreateEnderecoDTO endereco,
        Long usuarioId
) {
}
