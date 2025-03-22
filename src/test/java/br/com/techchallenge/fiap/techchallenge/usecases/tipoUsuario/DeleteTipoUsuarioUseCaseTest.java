package br.com.techchallenge.fiap.techchallenge.usecases.tipoUsuario;

import br.com.techchallenge.fiap.techchallenge.entities.TipoUsuario;
import br.com.techchallenge.fiap.techchallenge.enums.TipoUsuarioEnum;
import br.com.techchallenge.fiap.techchallenge.repositories.TipoUsuarioRepository;
import br.com.techchallenge.fiap.techchallenge.usecases.tipousuario.CreateTipoUsuarioUseCase;
import br.com.techchallenge.fiap.techchallenge.usecases.tipousuario.DeleteTipoUsuarioUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteTipoUsuarioUseCaseTest {
    @Mock
    private TipoUsuarioRepository tipoUsuarioRepository;

    @InjectMocks
    private DeleteTipoUsuarioUseCase deleteTipoUsuarioUseCase;

    @Test
    void deveCriarInstanciaDeDeleteTipoUsuarioUseCase() {
        DeleteTipoUsuarioUseCase instance = DeleteTipoUsuarioUseCase.create(tipoUsuarioRepository);
        assertNotNull(instance);
        assertTrue(instance instanceof DeleteTipoUsuarioUseCase);
    }

    @Test
    void deveDeletarTipoUsuarioComSucesso() {
        Long idTipoUsuario = 1L;
        doNothing().when(tipoUsuarioRepository).deleteById(idTipoUsuario);

        deleteTipoUsuarioUseCase.execute(idTipoUsuario);

        verify(tipoUsuarioRepository, times(1)).deleteById(idTipoUsuario);
    }
}
