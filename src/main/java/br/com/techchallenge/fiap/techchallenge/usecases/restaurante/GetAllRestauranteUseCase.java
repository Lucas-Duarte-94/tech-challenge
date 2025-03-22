package br.com.techchallenge.fiap.techchallenge.usecases.restaurante;

import br.com.techchallenge.fiap.techchallenge.entities.Restaurante;
import br.com.techchallenge.fiap.techchallenge.mappers.UsuarioMapper;
import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class GetAllRestauranteUseCase {
    private final RestauranteRepository restauranteRepository;

    private GetAllRestauranteUseCase(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public static GetAllRestauranteUseCase create(RestauranteRepository restauranteRepository) {
        return new GetAllRestauranteUseCase(restauranteRepository);
    }

    public List<Restaurante> execute(int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        return restauranteRepository.findAll(pageable).toList();
    }
}
