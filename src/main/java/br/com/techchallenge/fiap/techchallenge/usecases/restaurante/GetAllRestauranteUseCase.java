package br.com.techchallenge.fiap.techchallenge.usecases.restaurante;

import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;

public class GetAllRestauranteUseCase {
    private final RestauranteRepository restauranteRepository;

    private GetAllRestauranteUseCase(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public static GetAllRestauranteUseCase create(RestauranteRepository restauranteRepository) {
        return new GetAllRestauranteUseCase(restauranteRepository);
    }
}
