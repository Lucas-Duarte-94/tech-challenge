package br.com.techchallenge.fiap.techchallenge.usecases.funcionamento;

import br.com.techchallenge.fiap.techchallenge.entities.Funcionamento;
import br.com.techchallenge.fiap.techchallenge.repositories.FuncionamentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Sql(scripts = {"/db_load.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/db_clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class GetFuncionamentoUseCaseTest {

    @Mock
    private FuncionamentoRepository funcionamentoRepository;

    @InjectMocks
    private GetFuncionamentoUseCase getFuncionamentoUseCase;

    @Test
    void deveRetornarFuncionamentoQuandoExistir() {
        Long funcionamentoId = 1L;
        Funcionamento funcionamento = new Funcionamento(/* parâmetros válidos */);

        when(funcionamentoRepository.findById(funcionamentoId)).thenReturn(Optional.of(funcionamento));

        Funcionamento result = getFuncionamentoUseCase.execute(funcionamentoId);

        assertNotNull(result);
        assertEquals(funcionamento, result);

        verify(funcionamentoRepository, times(1)).findById(funcionamentoId);
    }

    @Test
    void deveRetornarNullQuandoNaoExistirFuncionamento() {
        Long funcionamentoId = 1L;

        when(funcionamentoRepository.findById(funcionamentoId)).thenReturn(Optional.empty());

        Funcionamento result = getFuncionamentoUseCase.execute(funcionamentoId);

        assertNull(result);

        verify(funcionamentoRepository, times(1)).findById(funcionamentoId);
    }

    @Test
    void deveCriarUseCaseComSucesso() {
        assertNotNull(
                GetFuncionamentoUseCase.create(funcionamentoRepository));
    }
}
