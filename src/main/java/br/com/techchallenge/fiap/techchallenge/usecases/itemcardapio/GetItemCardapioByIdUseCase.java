package br.com.techchallenge.fiap.techchallenge.usecases.itemcardapio;

import br.com.techchallenge.fiap.techchallenge.entities.ItemCardapio;
import br.com.techchallenge.fiap.techchallenge.repositories.ItemCardapioRepository;

public class GetItemCardapioByIdUseCase {
    private final ItemCardapioRepository itemCardapioRepository;

    private GetItemCardapioByIdUseCase(ItemCardapioRepository itemCardapioRepository) {
        this.itemCardapioRepository = itemCardapioRepository;
    }

    public static GetItemCardapioByIdUseCase create(ItemCardapioRepository itemCardapioRepository) {
        return new GetItemCardapioByIdUseCase(itemCardapioRepository);
    }

    public ItemCardapio execute(Long id) {
        return itemCardapioRepository.findById(id).orElse(null);
    }
}
