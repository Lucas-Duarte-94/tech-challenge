package br.com.techchallenge.fiap.techchallenge.usecases.itemcardapio;

import br.com.techchallenge.fiap.techchallenge.dtos.CreateItemCardapioDTO;
import br.com.techchallenge.fiap.techchallenge.entities.Cardapio;
import br.com.techchallenge.fiap.techchallenge.entities.ItemCardapio;
import br.com.techchallenge.fiap.techchallenge.errors.CardapioNotFoundException;
import br.com.techchallenge.fiap.techchallenge.repositories.CardapioRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.ItemCardapioRepository;

public class CreateItemCardapioUseCase {
    private final ItemCardapioRepository itemCardapioRepository;
    private final CardapioRepository cardapioRepository;

    private CreateItemCardapioUseCase(ItemCardapioRepository itemCardapioRepository, CardapioRepository cardapioRepository) {
        this.itemCardapioRepository = itemCardapioRepository;
        this.cardapioRepository = cardapioRepository;
    }

    public static CreateItemCardapioUseCase create(ItemCardapioRepository itemCardapioRepository, CardapioRepository cardapioRepository) {
        return new CreateItemCardapioUseCase(itemCardapioRepository, cardapioRepository);
    }

    public ItemCardapio execute(CreateItemCardapioDTO itemCardapio) {
        Cardapio existingCardapio = cardapioRepository.findById(itemCardapio.cardapioId()).orElseThrow(CardapioNotFoundException::new);

        ItemCardapio newIC = ItemCardapio.builder()
                .valor(itemCardapio.valor())
                .descricao(itemCardapio.descricao())
                .nome(itemCardapio.nome())
                .foto(itemCardapio.foto())
                .somenteLocal(itemCardapio.somenteLocal())
                .cardapio(existingCardapio)
                .build();

        return itemCardapioRepository.save(newIC);
    }
}
