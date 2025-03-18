package br.com.techchallenge.fiap.techchallenge.usecases.cardapio;

import br.com.techchallenge.fiap.techchallenge.repositories.CardapioRepository;

public class UpdateCardapioUseCase {
    private final CardapioRepository cardapioRepository;

    private UpdateCardapioUseCase(CardapioRepository cardapioRepository) {
        this.cardapioRepository = cardapioRepository;
    }

    public static UpdateCardapioUseCase create(CardapioRepository cardapioRepository) {
        return new UpdateCardapioUseCase(cardapioRepository);
    }
}
