package br.com.techchallenge.fiap.techchallenge.usecases.endereco;

import br.com.techchallenge.fiap.techchallenge.entities.Endereco;
import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;

public class GetEnderecoByRestauranteIdUseCase {
    private final RestauranteRepository restauranteRepository;

    private GetEnderecoByRestauranteIdUseCase(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public static GetEnderecoByRestauranteIdUseCase create(RestauranteRepository restauranteRepository) {
        return new GetEnderecoByRestauranteIdUseCase(restauranteRepository);
    }

    public Endereco execute(Long restauranteId) {
        return restauranteRepository.findEnderecoByRestauranteId(restauranteId);
    }
}
