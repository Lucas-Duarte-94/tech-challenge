package br.com.techchallenge.fiap.techchallenge.usecases.restaurante;

import br.com.techchallenge.fiap.techchallenge.entities.Restaurante;
import br.com.techchallenge.fiap.techchallenge.mocks.InternalMock;
import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetRestauranteByIdUseCaseTest {

    @Mock
    private RestauranteRepository restauranteRepository;

    @InjectMocks
    private GetRestauranteByIdUseCase getRestauranteByIdUseCase;

    @Test
    void deveCriarUseCaseComSucesso() {
        assertNotNull(
                GetRestauranteByIdUseCase.create(restauranteRepository));
    }
    @Test
    void deveRetornarRestauranteQuandoExistir() {
        Long idRestaurante = 1L;
        Restaurante restauranteMock = InternalMock.getRestaurante();

        when(restauranteRepository.findById(idRestaurante)).thenReturn(Optional.of(restauranteMock));

        Restaurante result = getRestauranteByIdUseCase.execute(idRestaurante);

        assertNotNull(result);
        assertEquals(restauranteMock.getNomeRestaurante(), result.getNomeRestaurante());
        verify(restauranteRepository, times(1)).findById(idRestaurante);
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoExistir() {
        Long idRestaurante = 1L;

        when(restauranteRepository.findById(idRestaurante)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> getRestauranteByIdUseCase.execute(idRestaurante));
        verify(restauranteRepository, times(1)).findById(idRestaurante);
    }
}
