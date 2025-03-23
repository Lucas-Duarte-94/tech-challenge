package br.com.techchallenge.fiap.techchallenge.usecases.itemcardapio;

import br.com.techchallenge.fiap.techchallenge.repositories.ItemCardapioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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
