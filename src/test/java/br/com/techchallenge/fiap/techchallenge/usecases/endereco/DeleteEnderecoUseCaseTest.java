package br.com.techchallenge.fiap.techchallenge.usecases.endereco;

import br.com.techchallenge.fiap.techchallenge.errors.MissingIdForEnderecoException;
import br.com.techchallenge.fiap.techchallenge.repositories.EnderecoRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteEnderecoUseCaseTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private RestauranteRepository restauranteRepository;

    @InjectMocks
    private DeleteEnderecoUseCase deleteEnderecoUseCase;

    @Test
    void deveCriarInstanciaDeDeleteEnderecoUseCase() {
        DeleteEnderecoUseCase instance = DeleteEnderecoUseCase.create(enderecoRepository, usuarioRepository, restauranteRepository);
        assertNotNull(instance);
        assertTrue(instance instanceof DeleteEnderecoUseCase);
    }

    @Test
    void deveExcluirEnderecoEAtualizarRestauranteQuandoRestauranteIdForInformado() {
        Long enderecoId = 1L;
        Long restauranteId = 2L;

        // Mocking
        doNothing().when(restauranteRepository).updateEnderecoToNull(restauranteId);
        doNothing().when(enderecoRepository).deleteById(enderecoId);

        deleteEnderecoUseCase.execute(enderecoId, Optional.of(restauranteId), Optional.empty());

        verify(restauranteRepository, times(1)).updateEnderecoToNull(restauranteId);
        verify(enderecoRepository, times(1)).deleteById(enderecoId);
    }

    @Test
    void deveExcluirEnderecoEAtualizarUsuarioQuandoUsuarioIdForInformado() {
        Long enderecoId = 1L;
        Long usuarioId = 2L;

        // Mocking
        doNothing().when(usuarioRepository).updateEnderecoToNull(usuarioId);
        doNothing().when(enderecoRepository).deleteById(enderecoId);

        deleteEnderecoUseCase.execute(enderecoId, Optional.empty(), Optional.of(usuarioId));

        verify(usuarioRepository, times(1)).updateEnderecoToNull(usuarioId);
        verify(enderecoRepository, times(1)).deleteById(enderecoId);
    }

    @Test
    void deveLancarExcecaoQuandoNenhumIdForInformado() {
        Long enderecoId = 1L;

        // Mocking
        assertThrows(MissingIdForEnderecoException.class, () -> deleteEnderecoUseCase.execute(enderecoId, Optional.empty(), Optional.empty()));

        verify(enderecoRepository, never()).deleteById(enderecoId);
    }

    @Test
    void deveLancarExcecaoQuandoEnderecoNaoExistir() {
        Long enderecoId = 1L;
        Long restauranteId = 2L;

        doThrow(IllegalArgumentException.class).when(restauranteRepository).updateEnderecoToNull(restauranteId);

        assertThrows(IllegalArgumentException.class, () -> deleteEnderecoUseCase.execute(enderecoId, Optional.of(restauranteId), Optional.empty()));

        verify(enderecoRepository, never()).deleteById(enderecoId);
    }
}
