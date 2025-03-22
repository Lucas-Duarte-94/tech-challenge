package br.com.techchallenge.fiap.techchallenge.usecases.itemcardapio;

import br.com.techchallenge.fiap.techchallenge.dtos.UpdateItemCardapioDTO;
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

    public ItemCardapio execute(Long id, UpdateItemCardapioDTO itemCardapio) {
        var existingItemCardapio = itemCardapioRepository.findById(id).orElseThrow(ItemCardapioNotFoundException::new);

        existingItemCardapio.setValor(itemCardapio.valor());
        existingItemCardapio.setNome(itemCardapio.nome());
        existingItemCardapio.setFoto(itemCardapio.foto());
        existingItemCardapio.setSomenteLocal(itemCardapio.somenteLocal());
        existingItemCardapio.setDescricao(itemCardapio.descricao());

        return itemCardapioRepository.save(existingItemCardapio);
    }
}
