package br.com.techchallenge.fiap.techchallenge.usecases.funcionamento;

import br.com.techchallenge.fiap.techchallenge.entities.Funcionamento;
import br.com.techchallenge.fiap.techchallenge.repositories.FuncionamentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Sql(scripts = {"/db_load.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/db_clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class GetFuncionamentoByRestauranteIdUseCaseTest {

    @Mock
    private FuncionamentoRepository funcionamentoRepository;

    @InjectMocks
    private GetFuncionamentoByRestauranteIdUseCase getFuncionamentoByRestauranteIdUseCase;

    @Test
    void deveRetornarListaDeFuncionamentos() {
        Long restauranteId = 1L;

        Funcionamento funcionamento1 = new Funcionamento(/* parâmetros válidos */);
        Funcionamento funcionamento2 = new Funcionamento(/* parâmetros válidos */);
        List<Funcionamento> funcionamentoList = Arrays.asList(funcionamento1, funcionamento2);

        when(funcionamentoRepository.findByRestaurante_IdRestaurante(restauranteId)).thenReturn(funcionamentoList);

        List<Funcionamento> result = getFuncionamentoByRestauranteIdUseCase.execute(restauranteId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(funcionamento1, result.get(0));
        assertEquals(funcionamento2, result.get(1));

        verify(funcionamentoRepository, times(1)).findByRestaurante_IdRestaurante(restauranteId);
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoExistirFuncionamento() {
        Long restauranteId = 1L;

        when(funcionamentoRepository.findByRestaurante_IdRestaurante(restauranteId)).thenReturn(List.of());

        List<Funcionamento> result = getFuncionamentoByRestauranteIdUseCase.execute(restauranteId);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(funcionamentoRepository, times(1)).findByRestaurante_IdRestaurante(restauranteId);
    }

    @Test
    void deveLancarExcecaoQuandoNaoEncontrarRestaurante() {
        Long restauranteId = 1L;

        when(funcionamentoRepository.findByRestaurante_IdRestaurante(restauranteId)).thenReturn(null);

        List<Funcionamento> result = getFuncionamentoByRestauranteIdUseCase.execute(restauranteId);

        assertNull(result);
        verify(funcionamentoRepository, times(1)).findByRestaurante_IdRestaurante(restauranteId);
    }

    @Test
    void deveCriarUseCaseComSucesso() {
        assertNotNull(
                GetFuncionamentoByRestauranteIdUseCase.create(funcionamentoRepository));
    }
}
