package br.com.techchallenge.fiap.techchallenge.usecases.itemcardapio;

import br.com.techchallenge.fiap.techchallenge.entities.ItemCardapio;
import br.com.techchallenge.fiap.techchallenge.repositories.ItemCardapioRepository;
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
public class GetItemCardapioByIdUseCaseTest {

    @Mock
    private ItemCardapioRepository itemCardapioRepository;

    @InjectMocks
    private GetItemCardapioByIdUseCase getItemCardapioByIdUseCase;

    @Test
    void deveCriarUseCaseComSucesso() {
        assertNotNull(
                GetItemCardapioByIdUseCase.create(itemCardapioRepository));
    }
    @Test
    void deveRetornarItemCardapioQuandoExistir() {
        Long itemCardapioId = 1L;
        ItemCardapio itemCardapio = new ItemCardapio(/* parâmetros válidos */);

        when(itemCardapioRepository.findById(itemCardapioId)).thenReturn(Optional.of(itemCardapio));

        ItemCardapio result = getItemCardapioByIdUseCase.execute(itemCardapioId);

        assertNotNull(result);
        assertEquals(itemCardapio, result);

        verify(itemCardapioRepository, times(1)).findById(itemCardapioId);
    }

    @Test
    void deveRetornarNullQuandoNaoExistirItemCardapio() {
        Long itemCardapioId = 1L;

        when(itemCardapioRepository.findById(itemCardapioId)).thenReturn(Optional.empty());

        ItemCardapio result = getItemCardapioByIdUseCase.execute(itemCardapioId);

        assertNull(result);

        verify(itemCardapioRepository, times(1)).findById(itemCardapioId);
    }
}
