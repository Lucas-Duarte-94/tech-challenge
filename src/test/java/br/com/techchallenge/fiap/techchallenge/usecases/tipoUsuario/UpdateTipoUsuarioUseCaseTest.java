package br.com.techchallenge.fiap.techchallenge.usecases.tipoUsuario;

import br.com.techchallenge.fiap.techchallenge.entities.TipoUsuario;
import br.com.techchallenge.fiap.techchallenge.enums.TipoUsuarioEnum;
import br.com.techchallenge.fiap.techchallenge.errors.TipoUsuarioNotFoundException;
import br.com.techchallenge.fiap.techchallenge.repositories.TipoUsuarioRepository;
import br.com.techchallenge.fiap.techchallenge.usecases.tipousuario.CreateTipoUsuarioUseCase;
import br.com.techchallenge.fiap.techchallenge.usecases.tipousuario.GetTipoUsuarioUseCase;
import br.com.techchallenge.fiap.techchallenge.usecases.tipousuario.UpdateTipoUsuarioUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateTipoUsuarioUseCaseTest {
    @Mock
    private TipoUsuarioRepository tipoUsuarioRepository;

    @InjectMocks
    private UpdateTipoUsuarioUseCase updateTipoUsuarioUseCase;

    @Test
    void deveCriarUseCaseComSucesso() {
        assertNotNull(
                UpdateTipoUsuarioUseCase.create(tipoUsuarioRepository));
    }
    @Test
    void deveCriarInstanciaDeUpdateTipoUsuarioUseCase() {
        UpdateTipoUsuarioUseCase instance = UpdateTipoUsuarioUseCase.create(tipoUsuarioRepository);
        assertNotNull(instance);
        assertTrue(instance instanceof UpdateTipoUsuarioUseCase);
    }

    @Test
    void deveAtualizarTipoUsuarioComSucesso() {
        Long id = 1L;
        TipoUsuarioEnum descTipoUsuario =  TipoUsuarioEnum.CLIENTE;
        TipoUsuario tipoUsuario = new TipoUsuario(id, TipoUsuarioEnum.CLIENTE);
        TipoUsuario tipoUsuarioAtualizado = new TipoUsuario(id, descTipoUsuario);

        when(tipoUsuarioRepository.findById(id)).thenReturn(Optional.of(tipoUsuario));
        when(tipoUsuarioRepository.save(any(TipoUsuario.class))).thenReturn(tipoUsuarioAtualizado);

        TipoUsuario resultado = updateTipoUsuarioUseCase.execute(id, TipoUsuarioEnum.CLIENTE.getValue());

        assertNotNull(resultado);
        verify(tipoUsuarioRepository, times(1)).findById(id);
        verify(tipoUsuarioRepository, times(1)).save(any(TipoUsuario.class));
    }

    @Test
    void deveLancarExcecaoQuandoTipoUsuarioNaoEncontrado() {
        Long id = 1L;
        String descTipoUsuario = "ADMIN";

        when(tipoUsuarioRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(TipoUsuarioNotFoundException.class, () -> updateTipoUsuarioUseCase.execute(id, descTipoUsuario));

        verify(tipoUsuarioRepository, times(1)).findById(id);
        verify(tipoUsuarioRepository, never()).save(any(TipoUsuario.class));
    }
}
