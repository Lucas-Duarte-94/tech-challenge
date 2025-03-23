package br.com.techchallenge.fiap.techchallenge.usecases.itemcardapio;

import br.com.techchallenge.fiap.techchallenge.dtos.CreateItemCardapioDTO;
import br.com.techchallenge.fiap.techchallenge.entities.Cardapio;
import br.com.techchallenge.fiap.techchallenge.entities.ItemCardapio;
import br.com.techchallenge.fiap.techchallenge.errors.CardapioNotFoundException;
import br.com.techchallenge.fiap.techchallenge.mocks.InternalMock;
import br.com.techchallenge.fiap.techchallenge.mocks.InternalMockDto;
import br.com.techchallenge.fiap.techchallenge.repositories.CardapioRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.ItemCardapioRepository;
import br.com.techchallenge.fiap.techchallenge.usecases.funcionamento.GetFuncionamentoUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateItemCardapioUseCaseTest {

    @Mock
    private ItemCardapioRepository itemCardapioRepository;

    @Mock
    private CardapioRepository cardapioRepository;

    @InjectMocks
    private CreateItemCardapioUseCase createItemCardapioUseCase;

    @Test
    void deveCriarUseCaseComSucesso() {
        assertNotNull(
                CreateItemCardapioUseCase.create(itemCardapioRepository, cardapioRepository));
    }
    @Test
    void deveCriarItemCardapioComSucesso() {
        CreateItemCardapioDTO dto = InternalMockDto.getCreateItemCardapioDTO();
        Cardapio cardapio = InternalMock.getCardapio();
        ItemCardapio itemCardapioCriado = InternalMock.getItemCardapio();

        when(cardapioRepository.findById(dto.cardapioId())).thenReturn(Optional.of(cardapio));
        when(itemCardapioRepository.save(any(ItemCardapio.class))).thenReturn(itemCardapioCriado);

        ItemCardapio itemCardapio = createItemCardapioUseCase.execute(dto);

        assertNotNull(itemCardapio);
        assertEquals(itemCardapioCriado.getNome(), itemCardapio.getNome());
        assertEquals(itemCardapioCriado.getDescricao(), itemCardapio.getDescricao());
        assertEquals(itemCardapioCriado.getValor(), itemCardapio.getValor());

        verify(cardapioRepository, times(1)).findById(dto.cardapioId());
        verify(itemCardapioRepository, times(1)).save(any(ItemCardapio.class));
    }

    @Test
    void deveLancarExcecaoQuandoCardapioNaoEncontrado() {
        CreateItemCardapioDTO dto = InternalMockDto.getCreateItemCardapioDTO();

        when(cardapioRepository.findById(dto.cardapioId())).thenReturn(Optional.empty());

        assertThrows(CardapioNotFoundException.class, () -> createItemCardapioUseCase.execute(dto));

        verify(cardapioRepository, times(1)).findById(dto.cardapioId());
        verify(itemCardapioRepository, never()).save(any(ItemCardapio.class));
    }
}
