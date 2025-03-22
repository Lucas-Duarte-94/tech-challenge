package br.com.techchallenge.fiap.techchallenge.dtos;

import br.com.techchallenge.fiap.techchallenge.entities.Cardapio;
import br.com.techchallenge.fiap.techchallenge.entities.ItemCardapio;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public record CardapioResponseDTO(
        Cardapio cardapio,
        List<ItemCardapio> itensCardapio
) {
}
