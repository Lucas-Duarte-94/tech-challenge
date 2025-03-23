package br.com.techchallenge.fiap.techchallenge.usecases.restaurante;

import br.com.techchallenge.fiap.techchallenge.errors.RestauranteNotFoundException;
import br.com.techchallenge.fiap.techchallenge.errors.UserDoesNotHavePermissionException;
import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;
import br.com.techchallenge.fiap.techchallenge.entities.Restaurante;
import br.com.techchallenge.fiap.techchallenge.mocks.InternalMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DeleteRestauranteUseCaseTest {

    @Mock
    private RestauranteRepository restauranteRepository;

    @InjectMocks
    private DeleteRestauranteUseCase deleteRestauranteUseCase;

    @Test
    void deveCriarUseCaseComSucesso() {
        assertNotNull(
                DeleteRestauranteUseCase.create(restauranteRepository));
    }
    @Test
    void deveDeletarRestauranteComSucesso() {
        Long restauranteId = 1L;
        Long usuarioId = 1L;

        Restaurante restaurante = InternalMock.getRestaurante();

        when(restauranteRepository.findById(restauranteId)).thenReturn(Optional.of(restaurante));

        deleteRestauranteUseCase.execute(restauranteId, usuarioId);

        verify(restauranteRepository, times(1)).findById(restauranteId);
        verify(restauranteRepository, times(1)).deleteById(restauranteId);
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoEncontrado() {
        Long restauranteId = 1L;
        Long usuarioId = 1L;

        when(restauranteRepository.findById(restauranteId)).thenReturn(Optional.empty());

        assertThrows(RestauranteNotFoundException.class, () -> deleteRestauranteUseCase.execute(restauranteId, usuarioId));

        verify(restauranteRepository, times(1)).findById(restauranteId);
        verify(restauranteRepository, never()).deleteById(restauranteId);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoTemPermissao() {
        Long restauranteId = 1L;
        Long usuarioId = 2L; // Usuario com id diferente do restaurante

        Restaurante restaurante = InternalMock.getRestaurante();
        restaurante.getUsuario().setIdUsuario(1L);

        when(restauranteRepository.findById(restauranteId)).thenReturn(Optional.of(restaurante));

        assertThrows(UserDoesNotHavePermissionException.class, () -> deleteRestauranteUseCase.execute(restauranteId, usuarioId));

        verify(restauranteRepository, times(1)).findById(restauranteId);
        verify(restauranteRepository, never()).deleteById(restauranteId);
    }
}
