package br.com.techchallenge.fiap.techchallenge.usecases.restaurante;

import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;

public class DeleteRestauranteUseCase {
    private final RestauranteRepository restauranteRepository;

    private DeleteRestauranteUseCase(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public static DeleteRestauranteUseCase create(RestauranteRepository restauranteRepository) {
        return new DeleteRestauranteUseCase(restauranteRepository);
    }

    public void execute(Long id) {
        restauranteRepository.deleteById(id);
    }
}
