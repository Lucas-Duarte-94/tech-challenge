package br.com.techchallenge.fiap.techchallenge.usecases.tipoUsuario;

import br.com.techchallenge.fiap.techchallenge.entities.TipoUsuario;
import br.com.techchallenge.fiap.techchallenge.enums.TipoUsuarioEnum;
import br.com.techchallenge.fiap.techchallenge.errors.TipoUsuarioNotFoundException;
import br.com.techchallenge.fiap.techchallenge.repositories.TipoUsuarioRepository;
import br.com.techchallenge.fiap.techchallenge.usecases.tipousuario.CreateTipoUsuarioUseCase;
import br.com.techchallenge.fiap.techchallenge.usecases.tipousuario.GetTipoUsuarioUseCase;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetTipoUsuarioUseCaseTest {
    @Mock
    private TipoUsuarioRepository tipoUsuarioRepository;

    @InjectMocks
    private GetTipoUsuarioUseCase getTipoUsuarioUseCase;

    @Test
    void deveCriarInstanciaDeGetTipoUsuarioUseCase() {
        GetTipoUsuarioUseCase instance = GetTipoUsuarioUseCase.create(tipoUsuarioRepository);
        assertNotNull(instance);
        assertTrue(instance instanceof GetTipoUsuarioUseCase);
    }

    @Test
    void deveRetornarTipoUsuarioComSucesso() {
        Long id = 1L;
        TipoUsuario tipoUsuario = new TipoUsuario(id, TipoUsuarioEnum.CLIENTE);
        when(tipoUsuarioRepository.findById(id)).thenReturn(Optional.of(tipoUsuario));

        TipoUsuario resultado = getTipoUsuarioUseCase.execute(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getIdTipoUsuario());
        verify(tipoUsuarioRepository, times(1)).findById(id);
    }

    @Test
    void deveLancarExcecaoQuandoTipoUsuarioNaoEncontrado() {
        Long id = 1L;
        when(tipoUsuarioRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(TipoUsuarioNotFoundException.class, () -> getTipoUsuarioUseCase.execute(id));

        verify(tipoUsuarioRepository, times(1)).findById(id);
    }
}
