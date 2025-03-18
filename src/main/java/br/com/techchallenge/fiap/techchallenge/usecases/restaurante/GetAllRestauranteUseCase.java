package br.com.techchallenge.fiap.techchallenge.usecases.restaurante;

import br.com.techchallenge.fiap.techchallenge.entities.Restaurante;
import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;

import java.util.List;

public class GetAllRestauranteUseCase {
    private final RestauranteRepository restauranteRepository;

    private GetAllRestauranteUseCase(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public static GetAllRestauranteUseCase create(RestauranteRepository restauranteRepository) {
        return new GetAllRestauranteUseCase(restauranteRepository);
    }

    public List<Restaurante> execute() {
        return restauranteRepository.findAll();
    }
}
