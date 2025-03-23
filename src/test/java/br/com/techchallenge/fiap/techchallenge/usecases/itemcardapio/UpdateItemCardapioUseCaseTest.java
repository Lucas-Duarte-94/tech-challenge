package br.com.techchallenge.fiap.techchallenge.usecases.itemcardapio;

import br.com.techchallenge.fiap.techchallenge.dtos.UpdateItemCardapioDTO;
import br.com.techchallenge.fiap.techchallenge.entities.ItemCardapio;
import br.com.techchallenge.fiap.techchallenge.errors.ItemCardapioNotFoundException;
import br.com.techchallenge.fiap.techchallenge.repositories.ItemCardapioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateItemCardapioUseCaseTest {

    @Mock
    private ItemCardapioRepository itemCardapioRepository;

    @InjectMocks
    private UpdateItemCardapioUseCase updateItemCardapioUseCase;

    @Test
    void deveCriarUseCaseComSucesso() {
        assertNotNull(
                UpdateItemCardapioUseCase.create(itemCardapioRepository));
    }
    @Test
    void deveAtualizarItemCardapioQuandoExistir() {
        Long itemCardapioId = 1L;
        UpdateItemCardapioDTO updateDTO = new UpdateItemCardapioDTO(
                "Novo Nome", "Nova Descrição", null, "Nova Foto", true
        );
        ItemCardapio existingItemCardapio = new ItemCardapio();
        existingItemCardapio.setIdItemCardapio(itemCardapioId);

        when(itemCardapioRepository.findById(itemCardapioId)).thenReturn(java.util.Optional.of(existingItemCardapio));
        when(itemCardapioRepository.save(any(ItemCardapio.class))).thenReturn(existingItemCardapio);

        ItemCardapio result = updateItemCardapioUseCase.execute(itemCardapioId, updateDTO);

        assertNotNull(result);
        assertEquals(updateDTO.nome(), result.getNome());
        assertEquals(updateDTO.descricao(), result.getDescricao());
        assertEquals(updateDTO.foto(), result.getFoto());
        assertEquals(updateDTO.somenteLocal(), result.getSomenteLocal());

        verify(itemCardapioRepository, times(1)).findById(itemCardapioId);
        verify(itemCardapioRepository, times(1)).save(any(ItemCardapio.class));
    }

    @Test
    void deveLancarExcecaoQuandoItemCardapioNaoExistir() {
        Long itemCardapioId = 1L;
        UpdateItemCardapioDTO updateDTO = new UpdateItemCardapioDTO(
                "Novo Nome", "Nova Descrição", null, "Nova Foto", true
        );

        when(itemCardapioRepository.findById(itemCardapioId)).thenReturn(java.util.Optional.empty());

        assertThrows(ItemCardapioNotFoundException.class, () -> updateItemCardapioUseCase.execute(itemCardapioId, updateDTO));

        verify(itemCardapioRepository, times(1)).findById(itemCardapioId);
        verify(itemCardapioRepository, never()).save(any(ItemCardapio.class));
    }
}
