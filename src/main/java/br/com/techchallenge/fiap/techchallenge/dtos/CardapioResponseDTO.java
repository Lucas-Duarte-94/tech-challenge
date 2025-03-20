package br.com.techchallenge.fiap.techchallenge.dtos;

import br.com.techchallenge.fiap.techchallenge.entities.Cardapio;
import br.com.techchallenge.fiap.techchallenge.entities.ItemCardapio;

import java.util.List;

public record CardapioResponseDTO(
        Cardapio cardapio,
        List<ItemCardapio> itensCardapio
) {
}
