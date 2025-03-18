package br.com.techchallenge.fiap.techchallenge.usecases.restaurante;

import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;

public class UpdateRestauranteUseCase {
    private final RestauranteRepository restauranteRepository;

    private UpdateRestauranteUseCase(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public static UpdateRestauranteUseCase create(RestauranteRepository restauranteRepository) {
        return new UpdateRestauranteUseCase(restauranteRepository);
    }
}
