package br.com.techchallenge.fiap.techchallenge.usecases.usuario;

import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;
import br.com.techchallenge.fiap.techchallenge.usecases.usuario.DeleteUsuarioUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@Sql(scripts = {"/db_load.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/db_clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class DeleteUsuarioUseCaseTest {
    @InjectMocks
    private DeleteUsuarioUseCase deleteUsuarioUseCase;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    void deveCriarUseCaseComSucesso() {
        assertNotNull(
                DeleteUsuarioUseCase.create(usuarioRepository));
    }
    @Test
    void deveDeletarUsuarioComSucesso() {
        Long usuarioId = 1L;

        deleteUsuarioUseCase.execute(usuarioId);

        verify(usuarioRepository, times(1)).deleteById(usuarioId);
    }

    @Test
    void deveCriarInstanciaDeDeleteUsuarioUseCase() {
        // Executa o método estático create()
        DeleteUsuarioUseCase instance = DeleteUsuarioUseCase.create(usuarioRepository);

        // Verifica se a instância não é nula
        assertNotNull(instance);

        // Verifica se a instância criada é realmente do tipo DeleteUsuarioUseCase
        assertTrue(instance instanceof DeleteUsuarioUseCase);
    }

}
