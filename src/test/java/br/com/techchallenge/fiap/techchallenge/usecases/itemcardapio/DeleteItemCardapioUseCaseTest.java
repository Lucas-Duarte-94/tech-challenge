package br.com.techchallenge.fiap.techchallenge.usecases.itemcardapio;

import br.com.techchallenge.fiap.techchallenge.repositories.ItemCardapioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Sql(scripts = {"/db_load.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/db_clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class DeleteItemCardapioUseCaseTest {

    @Mock
    private ItemCardapioRepository itemCardapioRepository;

    @InjectMocks
    private DeleteItemCardapioUseCase deleteItemCardapioUseCase;

    @Test
    void deveCriarUseCaseComSucesso() {
        assertNotNull(
                DeleteItemCardapioUseCase.create(itemCardapioRepository));
    }
    @Test
    void deveExcluirItemCardapioComSucesso() {
        Long itemCardapioId = 1L;

        // Executa o caso de uso
        deleteItemCardapioUseCase.execute(itemCardapioId);

        // Verifica se o método deleteById foi chamado com o ID correto
        verify(itemCardapioRepository, times(1)).deleteById(itemCardapioId);
    }
}
