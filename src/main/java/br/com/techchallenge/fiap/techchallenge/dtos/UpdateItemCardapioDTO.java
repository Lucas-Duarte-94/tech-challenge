package br.com.techchallenge.fiap.techchallenge.dtos;

import java.math.BigDecimal;

public record UpdateItemCardapioDTO(
        String nome,
        String descricao,
        BigDecimal valor,
        String foto,
        Boolean somenteLocal
) {
}
