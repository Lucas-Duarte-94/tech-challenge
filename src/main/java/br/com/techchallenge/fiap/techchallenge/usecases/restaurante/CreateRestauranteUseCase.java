package br.com.techchallenge.fiap.techchallenge.usecases.restaurante;

import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;

public class CreateRestauranteUseCase {
    private final RestauranteRepository restauranteRepository;

    private CreateRestauranteUseCase(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public static CreateRestauranteUseCase create(RestauranteRepository restauranteRepository) {
        return new CreateRestauranteUseCase(restauranteRepository);
    }
}
