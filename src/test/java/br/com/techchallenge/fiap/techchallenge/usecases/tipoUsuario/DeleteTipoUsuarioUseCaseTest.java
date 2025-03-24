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
import org.springframework.test.context.jdbc.Sql;

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
@Sql(scripts = {"/db_load.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/db_clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class DeleteTipoUsuarioUseCaseTest {
    @Mock
    private TipoUsuarioRepository tipoUsuarioRepository;

    @InjectMocks
    private DeleteTipoUsuarioUseCase deleteTipoUsuarioUseCase;

    @Test
    void deveCriarUseCaseComSucesso() {
        assertNotNull(
                DeleteTipoUsuarioUseCase.create(tipoUsuarioRepository));
    }
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
