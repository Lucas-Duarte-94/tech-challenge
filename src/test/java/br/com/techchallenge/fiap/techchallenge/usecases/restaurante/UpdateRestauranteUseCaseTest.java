package br.com.techchallenge.fiap.techchallenge.usecases.restaurante;

import br.com.techchallenge.fiap.techchallenge.dtos.UpdateRestauranteRequestDTO;
import br.com.techchallenge.fiap.techchallenge.entities.Restaurante;
import br.com.techchallenge.fiap.techchallenge.errors.RestauranteNotFoundException;
import br.com.techchallenge.fiap.techchallenge.errors.UserDoesNotHavePermissionException;
import br.com.techchallenge.fiap.techchallenge.mocks.InternalMock;
import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateRestauranteUseCaseTest {

    @Mock
    private RestauranteRepository restauranteRepository;

    @InjectMocks
    private UpdateRestauranteUseCase updateRestauranteUseCase;

    @Test
    void deveCriarUseCaseComSucesso() {
        assertNotNull(
                UpdateRestauranteUseCase.create(restauranteRepository));
    }
    @Test
    void deveAtualizarRestauranteQuandoUsuarioForValido() {
        Long idRestaurante = 1L;
        Long usuarioId = 1L;
        UpdateRestauranteRequestDTO requestDTO = new UpdateRestauranteRequestDTO("Restaurante Atualizado", "Mexicana", usuarioId);

        Restaurante restauranteMock = InternalMock.getRestaurante();
        restauranteMock.getUsuario().setIdUsuario(usuarioId);

        when(restauranteRepository.findById(idRestaurante)).thenReturn(java.util.Optional.of(restauranteMock));
        when(restauranteRepository.save(any(Restaurante.class))).thenReturn(restauranteMock);

        Restaurante result = updateRestauranteUseCase.execute(idRestaurante, requestDTO);

        assertNotNull(result);
        assertEquals(requestDTO.nomeRestaurante(), result.getNomeRestaurante());
        assertEquals(requestDTO.tipoCozinha(), result.getTipoCozinha());
        verify(restauranteRepository, times(1)).findById(idRestaurante);
        verify(restauranteRepository, times(1)).save(any(Restaurante.class));
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoForEncontrado() {
        Long idRestaurante = 1L;
        Long usuarioId = 1L;
        UpdateRestauranteRequestDTO requestDTO = new UpdateRestauranteRequestDTO("Restaurante Atualizado", "Mexicana", usuarioId);

        when(restauranteRepository.findById(idRestaurante)).thenReturn(java.util.Optional.empty());

        assertThrows(RestauranteNotFoundException.class, () -> updateRestauranteUseCase.execute(idRestaurante, requestDTO));

        verify(restauranteRepository, times(1)).findById(idRestaurante);
        verify(restauranteRepository, never()).save(any(Restaurante.class));
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoForDonoDoRestaurante() {
        Long idRestaurante = 1L;
        Long usuarioId = 2L;
        UpdateRestauranteRequestDTO requestDTO = new UpdateRestauranteRequestDTO("Restaurante Atualizado", "Mexicana", usuarioId);

        Restaurante restauranteMock = InternalMock.getRestaurante();
        restauranteMock.getUsuario().setIdUsuario(1L); // Usuario diferente

        when(restauranteRepository.findById(idRestaurante)).thenReturn(java.util.Optional.of(restauranteMock));

        assertThrows(UserDoesNotHavePermissionException.class, () -> updateRestauranteUseCase.execute(idRestaurante, requestDTO));

        verify(restauranteRepository, times(1)).findById(idRestaurante);
        verify(restauranteRepository, never()).save(any(Restaurante.class));
    }
}
