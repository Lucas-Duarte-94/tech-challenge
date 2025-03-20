package br.com.techchallenge.fiap.techchallenge.usecases.cardapio;

import br.com.techchallenge.fiap.techchallenge.entities.Cardapio;
import br.com.techchallenge.fiap.techchallenge.errors.RestauranteNotFoundException;
import br.com.techchallenge.fiap.techchallenge.repositories.CardapioRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;

public class CreateCardapioUseCase {
    private final CardapioRepository cardapioRepository;
    private final RestauranteRepository restauranteRepository;

    private CreateCardapioUseCase(CardapioRepository cardapioRepository, RestauranteRepository restauranteRepository) {
        this.cardapioRepository = cardapioRepository;
        this.restauranteRepository = restauranteRepository;
    }

    public static CreateCardapioUseCase create(CardapioRepository cardapioRepository, RestauranteRepository restauranteRepository) {
        return new CreateCardapioUseCase(cardapioRepository, restauranteRepository);
    }

    public Cardapio execute(Long restauranteId) {
        var restaurante = restauranteRepository.findById(restauranteId).orElseThrow(RestauranteNotFoundException::new);

        Cardapio novoCardapio = Cardapio.builder().restaurante(restaurante).build();

        return cardapioRepository.save(novoCardapio);
    }
}
