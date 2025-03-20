package br.com.techchallenge.fiap.techchallenge.usecases.itemcardapio;

import br.com.techchallenge.fiap.techchallenge.repositories.ItemCardapioRepository;

public class DeleteItemCardapioUseCase {
    private final ItemCardapioRepository itemCardapioRepository;

    private DeleteItemCardapioUseCase(ItemCardapioRepository itemCardapioRepository) {
        this.itemCardapioRepository = itemCardapioRepository;
    }

    public static DeleteItemCardapioUseCase create(ItemCardapioRepository itemCardapioRepository) {
        return new DeleteItemCardapioUseCase(itemCardapioRepository);
    }

    public void execute(Long id) {
        itemCardapioRepository.deleteById(id);
    }
}
