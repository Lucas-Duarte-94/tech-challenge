package br.com.techchallenge.fiap.techchallenge.usecases.itemcardapio;

import br.com.techchallenge.fiap.techchallenge.entities.ItemCardapio;
import br.com.techchallenge.fiap.techchallenge.errors.ItemCardapioNotFoundException;
import br.com.techchallenge.fiap.techchallenge.repositories.ItemCardapioRepository;

public class UpdateItemCardapioUseCase {
    private final ItemCardapioRepository itemCardapioRepository;

    private UpdateItemCardapioUseCase(ItemCardapioRepository itemCardapioRepository) {
        this.itemCardapioRepository = itemCardapioRepository;
    }

    public static UpdateItemCardapioUseCase create(ItemCardapioRepository itemCardapioRepository) {
        return new UpdateItemCardapioUseCase(itemCardapioRepository);
    }

    public ItemCardapio execute(ItemCardapio itemCardapio) {
        itemCardapioRepository.findById(itemCardapio.getIdItemCardapio()).orElseThrow(ItemCardapioNotFoundException::new);

        return itemCardapioRepository.save(itemCardapio);
    }
}
