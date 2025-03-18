package br.com.techchallenge.fiap.techchallenge.usecases.restaurante;

import br.com.techchallenge.fiap.techchallenge.entities.Restaurante;
import br.com.techchallenge.fiap.techchallenge.errors.RestauranteNotFoundException;
import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;

public class UpdateRestauranteUseCase {
    private final RestauranteRepository restauranteRepository;

    private UpdateRestauranteUseCase(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public static UpdateRestauranteUseCase create(RestauranteRepository restauranteRepository) {
        return new UpdateRestauranteUseCase(restauranteRepository);
    }

    public Restaurante execute(Restaurante restaurante) {
        restauranteRepository.findById(restaurante.getIdRestaurante()).orElseThrow(RestauranteNotFoundException::new);

        return restauranteRepository.save(restaurante);
    }
}
