package br.com.techchallenge.fiap.techchallenge.usecases.cardapio;

import br.com.techchallenge.fiap.techchallenge.repositories.CardapioRepository;

public class GetCardapioUseCase {
    private final CardapioRepository cardapioRepository;

    private GetCardapioUseCase(CardapioRepository cardapioRepository) {
        this.cardapioRepository = cardapioRepository;
    }

    public static GetCardapioUseCase create(CardapioRepository cardapioRepository) {
        return new GetCardapioUseCase(cardapioRepository);
    }
}
