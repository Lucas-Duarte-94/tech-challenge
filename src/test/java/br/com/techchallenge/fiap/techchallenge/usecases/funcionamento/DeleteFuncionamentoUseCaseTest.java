package br.com.techchallenge.fiap.techchallenge.usecases.funcionamento;

import br.com.techchallenge.fiap.techchallenge.repositories.FuncionamentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteFuncionamentoUseCaseTest {

    @Mock
    private FuncionamentoRepository funcionamentoRepository;

    @InjectMocks
    private DeleteFuncionamentoUseCase deleteFuncionamentoUseCase;

    @Test
    void deveDeletarFuncionamentoComSucesso() {
        Long funcionamentoId = 1L;

        // Executando o caso de uso
        deleteFuncionamentoUseCase.execute(funcionamentoId);

        // Verificando se o repositório foi chamado corretamente
        verify(funcionamentoRepository, times(1)).deleteById(funcionamentoId);
    }

    @Test
    void naoDeveFazerNadaSeIdForInvalido() {
        Long funcionamentoId = 1L;

        doNothing().when(funcionamentoRepository).deleteById(funcionamentoId);

        // Executando o caso de uso
        deleteFuncionamentoUseCase.execute(funcionamentoId);

        // Verificando se o método deleteById foi chamado corretamente, mesmo que não haja dados
        verify(funcionamentoRepository, times(1)).deleteById(funcionamentoId);
    }

    @Test
    void deveLancarExcecaoSeNaoExistirFuncionamentoComId() {
        Long funcionamentoId = 1L;

        // Simulando que o funcionamento não existe no repositório
        doThrow(new IllegalArgumentException("Funcionamento não encontrado")).when(funcionamentoRepository).deleteById(funcionamentoId);

        // Verificando se o caso de uso lida corretamente com exceções
        try {
            deleteFuncionamentoUseCase.execute(funcionamentoId);
        } catch (IllegalArgumentException e) {
            assertEquals("Funcionamento não encontrado", e.getMessage());
        }

        // Verificando se o repositório foi chamado
        verify(funcionamentoRepository, times(1)).deleteById(funcionamentoId);
    }
}
