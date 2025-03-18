package br.com.techchallenge.fiap.techchallenge.usecases.cardapio;

import br.com.techchallenge.fiap.techchallenge.repositories.CardapioRepository;

public class DeleteCardapioUseCase {
    private final CardapioRepository cardapioRepository;

    private DeleteCardapioUseCase(CardapioRepository cardapioRepository) {
        this.cardapioRepository = cardapioRepository;
    }

    public static DeleteCardapioUseCase create(CardapioRepository cardapioRepository) {
        return new DeleteCardapioUseCase(cardapioRepository);
    }
}
