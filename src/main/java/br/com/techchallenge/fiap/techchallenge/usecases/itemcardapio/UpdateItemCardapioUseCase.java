package br.com.techchallenge.fiap.techchallenge.usecases.itemcardapio;

import br.com.techchallenge.fiap.techchallenge.repositories.ItemCardapioRepository;

public class UpdateItemCardapioUseCase {
    private final ItemCardapioRepository itemCardapioRepository;

    private UpdateItemCardapioUseCase(ItemCardapioRepository itemCardapioRepository) {
        this.itemCardapioRepository = itemCardapioRepository;
    }

    public static UpdateItemCardapioUseCase create(ItemCardapioRepository itemCardapioRepository) {
        return new UpdateItemCardapioUseCase(itemCardapioRepository);
    }
}
