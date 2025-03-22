package br.com.techchallenge.fiap.techchallenge.usecases.itemcardapio;

import br.com.techchallenge.fiap.techchallenge.entities.ItemCardapio;
import br.com.techchallenge.fiap.techchallenge.repositories.ItemCardapioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetItemCardapioByIdUseCaseTest {

    @Mock
    private ItemCardapioRepository itemCardapioRepository;

    @InjectMocks
    private GetItemCardapioByIdUseCase getItemCardapioByIdUseCase;

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
