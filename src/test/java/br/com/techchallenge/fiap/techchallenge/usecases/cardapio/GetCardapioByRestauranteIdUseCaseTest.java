package br.com.techchallenge.fiap.techchallenge.usecases.cardapio;

import br.com.techchallenge.fiap.techchallenge.dtos.CardapioResponseDTO;
import br.com.techchallenge.fiap.techchallenge.entities.Cardapio;
import br.com.techchallenge.fiap.techchallenge.entities.ItemCardapio;
import br.com.techchallenge.fiap.techchallenge.entities.Restaurante;
import br.com.techchallenge.fiap.techchallenge.errors.CardapioNotFoundException;
import br.com.techchallenge.fiap.techchallenge.mocks.InternalMock;
import br.com.techchallenge.fiap.techchallenge.repositories.CardapioRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.ItemCardapioRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetCardapioByRestauranteIdUseCaseTest {

    @Mock
    private CardapioRepository cardapioRepository;

    @Mock
    private RestauranteRepository restauranteRepository;

    @Mock
    private ItemCardapioRepository itemCardapioRepository;

    @InjectMocks
    private GetCardapioByRestauranteIdUseCase getCardapioByRestauranteIdUseCase;

    @Test
    void deveCriarInstanciaDeGetCardapioByRestauranteIdUseCase() {
        GetCardapioByRestauranteIdUseCase instance = GetCardapioByRestauranteIdUseCase.create(
                cardapioRepository, restauranteRepository, itemCardapioRepository);
        assertNotNull(instance);
        assertTrue(instance instanceof GetCardapioByRestauranteIdUseCase);
    }

    @Test
    void deveRetornarCardapioComItensQuandoRestauranteExistir() {
        Long restauranteId = 1L;
        Cardapio cardapio = InternalMock.getCardapio();
        ItemCardapio item1 = InternalMock.getItemCardapio();

        when(cardapioRepository.findByRestaurante_Id(restauranteId)).thenReturn(Optional.of(cardapio));
        when(itemCardapioRepository.findAllByCardapio_IdCardapio(cardapio.getIdCardapio()))
                .thenReturn(List.of(item1));

        CardapioResponseDTO responseDTO = getCardapioByRestauranteIdUseCase.execute(restauranteId);

        assertNotNull(responseDTO);
        assertEquals(cardapio, responseDTO.cardapio());
        assertEquals(1, responseDTO.itensCardapio().size());
        assertEquals("Descrição do Item", responseDTO.itensCardapio().get(0).getDescricao());
        verify(cardapioRepository, times(1)).findByRestaurante_Id(restauranteId);
        verify(itemCardapioRepository, times(1)).findAllByCardapio_IdCardapio(cardapio.getIdCardapio());
    }

    @Test
    void deveLancarExcecaoQuandoCardapioNaoExistirParaRestaurante() {
        Long restauranteId = 1L;

        when(cardapioRepository.findByRestaurante_Id(restauranteId)).thenReturn(Optional.empty());

        assertThrows(CardapioNotFoundException.class, () -> getCardapioByRestauranteIdUseCase.execute(restauranteId));

        verify(cardapioRepository, times(1)).findByRestaurante_Id(restauranteId);
        verify(itemCardapioRepository, never()).findAllByCardapio_IdCardapio(any());
    }
}
