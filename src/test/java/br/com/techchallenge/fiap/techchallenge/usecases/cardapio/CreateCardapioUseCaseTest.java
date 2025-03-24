package br.com.techchallenge.fiap.techchallenge.usecases.cardapio;

import br.com.techchallenge.fiap.techchallenge.entities.Cardapio;
import br.com.techchallenge.fiap.techchallenge.entities.Restaurante;
import br.com.techchallenge.fiap.techchallenge.errors.RestauranteNotFoundException;
import br.com.techchallenge.fiap.techchallenge.mocks.InternalMock;
import br.com.techchallenge.fiap.techchallenge.repositories.CardapioRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Sql(scripts = {"/db_load.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/db_clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CreateCardapioUseCaseTest {

    @Mock
    private CardapioRepository cardapioRepository;

    @Mock
    private RestauranteRepository restauranteRepository;

    @InjectMocks
    private CreateCardapioUseCase createCardapioUseCase;

    @Test
    void deveCriarInstanciaDeCreateCardapioUseCase() {
        CreateCardapioUseCase instance = CreateCardapioUseCase.create(cardapioRepository, restauranteRepository);
        assertNotNull(instance);
        assertTrue(instance instanceof CreateCardapioUseCase);
    }

    @Test
    void deveCriarCardapioComSucesso() {
        Restaurante restaurante = InternalMock.getRestaurante();
        Cardapio cardapioEsperado = new Cardapio(1L, restaurante);

        when(restauranteRepository.findById(1L)).thenReturn(Optional.of(restaurante));
        when(cardapioRepository.save(any(Cardapio.class))).thenReturn(cardapioEsperado);

        Cardapio cardapioCriado = createCardapioUseCase.execute(1L);

        assertNotNull(cardapioCriado);
        assertEquals(restaurante, cardapioCriado.getRestaurante());
        verify(restauranteRepository, times(1)).findById(1L);
        verify(cardapioRepository, times(1)).save(any(Cardapio.class));
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoEncontrado() {
        Long restauranteId = 1L;

        when(restauranteRepository.findById(restauranteId)).thenReturn(Optional.empty());

        assertThrows(RestauranteNotFoundException.class, () -> createCardapioUseCase.execute(restauranteId));

        verify(restauranteRepository, times(1)).findById(restauranteId);
        verify(cardapioRepository, never()).save(any(Cardapio.class));
    }
}
