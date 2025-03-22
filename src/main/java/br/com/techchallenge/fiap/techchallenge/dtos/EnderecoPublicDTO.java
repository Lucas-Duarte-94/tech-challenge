package br.com.techchallenge.fiap.techchallenge.dtos;

import br.com.techchallenge.fiap.techchallenge.entities.Restaurante;

import java.util.Optional;

public record EnderecoPublicDTO(
        String descricaoLogradouro,
        String numero,
        String descricaoComplemento,
        String descricaoBairro,
        String descricaoCidade,
        String descricaoEstado,
        String numeroCep,
        Optional<UsuarioPublicDTO> usuario,
        Optional<Restaurante> restaurante
) {
}
