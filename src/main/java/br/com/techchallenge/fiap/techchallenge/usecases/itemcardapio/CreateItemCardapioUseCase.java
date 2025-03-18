package br.com.techchallenge.fiap.techchallenge.usecases.itemcardapio;

import br.com.techchallenge.fiap.techchallenge.repositories.ItemCardapioRepository;

public class CreateItemCardapioUseCase {
    private final ItemCardapioRepository itemCardapioRepository;

    private CreateItemCardapioUseCase(ItemCardapioRepository itemCardapioRepository) {
        this.itemCardapioRepository = itemCardapioRepository;
    }

    public static CreateItemCardapioUseCase create(ItemCardapioRepository itemCardapioRepository) {
        return new CreateItemCardapioUseCase(itemCardapioRepository);
    }
}
