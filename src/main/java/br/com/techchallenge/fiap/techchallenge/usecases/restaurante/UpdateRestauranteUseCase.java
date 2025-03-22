package br.com.techchallenge.fiap.techchallenge.usecases.restaurante;

import br.com.techchallenge.fiap.techchallenge.dtos.UpdateRestauranteRequestDTO;
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

    public Restaurante execute(Long id, UpdateRestauranteRequestDTO request) {
        var restaurante = restauranteRepository.findById(id).orElseThrow(RestauranteNotFoundException::new);

        restaurante.setNomeRestaurante(request.nomeRestaurante());
        restaurante.setTipoCozinha(request.tipoCozinha());

        return restauranteRepository.save(restaurante);
    }
}
