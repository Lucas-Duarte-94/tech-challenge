package br.com.techchallenge.fiap.techchallenge.usecases.cardapio;

import br.com.techchallenge.fiap.techchallenge.repositories.CardapioRepository;

public class CreateCardapioUseCase {
    private final CardapioRepository cardapioRepository;

    private CreateCardapioUseCase(CardapioRepository cardapioRepository) {
        this.cardapioRepository = cardapioRepository;
    }

    public static CreateCardapioUseCase create(CardapioRepository cardapioRepository) {
        return new CreateCardapioUseCase(cardapioRepository);
    }
}
