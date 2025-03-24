package br.com.techchallenge.fiap.techchallenge.usecases.restaurante;

import br.com.techchallenge.fiap.techchallenge.errors.RestauranteNotFoundException;
import br.com.techchallenge.fiap.techchallenge.errors.UserDoesNotHavePermissionException;
import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
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

        log.info("O ID Q ENTROU LOGO ANTES DA CALL: {}", id);

        try {

            restauranteRepository.deleteById(id);
        }catch (Exception e){
            log.error("TRiste mas real, deu erro: {}", e.getMessage());
        }
    }
}
