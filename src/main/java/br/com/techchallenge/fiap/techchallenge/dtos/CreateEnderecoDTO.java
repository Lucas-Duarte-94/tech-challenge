package br.com.techchallenge.fiap.techchallenge.dtos;

import java.util.Optional;

public record CreateEnderecoDTO(
        String descricaoLogradouro,
        String numero,
        String descricaoComplemento,
        String descricaoBairro,
        String descricaoCidade,
        String descricaoEstado,
        String numeroCep,
        Optional<Long> usuarioId,
        Optional<Long> restauranteId

) {
}
