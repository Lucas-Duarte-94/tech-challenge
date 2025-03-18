package br.com.techchallenge.fiap.techchallenge.dtos;

public record CreateEnderecoDTO(
        String descricaoLogradouro,
        String numero,
        String descricaoComplemento,
        String descricaoBairro,
        String descricaoCidade,
        String descricaoEstado,
        String numeroCep

) {
}
