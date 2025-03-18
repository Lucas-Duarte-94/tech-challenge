package br.com.techchallenge.fiap.techchallenge.usecases.restaurante;

import br.com.techchallenge.fiap.techchallenge.entities.Restaurante;
import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;

public class GetRestauranteByIdUseCase {
    private final RestauranteRepository restauranteRepository;

    private GetRestauranteByIdUseCase(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public static GetRestauranteByIdUseCase create(RestauranteRepository restauranteRepository) {
        return new GetRestauranteByIdUseCase(restauranteRepository);
    }

    public Restaurante execute(Long id) {
        return restauranteRepository.findById(id).orElseThrow();
    }
}
