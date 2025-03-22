package br.com.techchallenge.fiap.techchallenge.usecases.restaurante;

import br.com.techchallenge.fiap.techchallenge.errors.RestauranteNotFoundException;
import br.com.techchallenge.fiap.techchallenge.errors.UserDoesNotHavePermissionException;
import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;

import java.util.Objects;

public class DeleteRestauranteUseCase {
    private final RestauranteRepository restauranteRepository;

    private DeleteRestauranteUseCase(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public static DeleteRestauranteUseCase create(RestauranteRepository restauranteRepository) {
        return new DeleteRestauranteUseCase(restauranteRepository);
    }

    public void execute(Long id, Long usuarioId) {
        var restaurante = restauranteRepository.findById(id).orElseThrow(RestauranteNotFoundException::new);

        if(!Objects.equals(restaurante.getUsuario().getIdUsuario(), usuarioId)) {
            throw new UserDoesNotHavePermissionException();
        }
        restauranteRepository.deleteById(id);
    }
}
