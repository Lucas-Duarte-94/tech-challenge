package br.com.techchallenge.fiap.techchallenge.dtos;

import java.util.Optional;

public record UpdateEnderecoDTO(
        String descricaoLogradouro,
        String numero,
        String descricaoComplemento,
        String descricaoBairro,
        String descricaoCidade,
        String descricaoEstado,
        String numeroCep,
        Long usuarioId,
        Optional<Long> restauranteId
) {
}
