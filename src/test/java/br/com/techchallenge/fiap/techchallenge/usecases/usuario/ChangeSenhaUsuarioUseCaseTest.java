package br.com.techchallenge.fiap.techchallenge.usecases.usuario;

import br.com.techchallenge.fiap.techchallenge.dtos.UserChangePasswordRequestDTO;
import br.com.techchallenge.fiap.techchallenge.entities.Usuario;
import br.com.techchallenge.fiap.techchallenge.errors.PasswordDoesNotMatchException;
import br.com.techchallenge.fiap.techchallenge.errors.UserNotFoundException;
import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.jdbc.Sql;

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
@Sql(scripts = {"/db_load.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/db_clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ChangeSenhaUsuarioUseCaseTest {
    @InjectMocks
    private ChangeSenhaUsuarioUseCase changeSenhaUsuarioUseCase;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    void deveCriarUseCaseComSucesso() {
        assertNotNull(
                ChangeSenhaUsuarioUseCase.create(usuarioRepository));
    }
    @Test
    void deveCriarInstanciaDeChangeSenhaUsuarioUseCase() {
        // Executa o método estático create() para criar uma instância do caso de uso
        ChangeSenhaUsuarioUseCase instance = ChangeSenhaUsuarioUseCase.create(usuarioRepository);

        // Verifica se a instância não é nula
        assertNotNull(instance);

        // Verifica se a instância criada é do tipo correto (ChangeSenhaUsuarioUseCase)
        assertTrue(instance instanceof ChangeSenhaUsuarioUseCase);
    }

    @Test
    void deveAlterarSenhaComSucesso() {
        // Criação do DTO com dados de entrada
        UserChangePasswordRequestDTO dto = new UserChangePasswordRequestDTO("login1", "senhaAntiga", "novaSenha123");

        // Criação de um usuário fictício
        Usuario usuarioExistente = new Usuario(1L, "Usuário 1", null, "email1@email.com", "login1", "senhaAntiga", null, null);

        // Mockando a busca do usuário pelo login
        when(usuarioRepository.findByLogin(dto.login())).thenReturn(Optional.of(usuarioExistente));

        // Mockando o comportamento de salvar o usuário com a nova senha
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Executa o método de alteração de senha
        changeSenhaUsuarioUseCase.execute(dto);

        // Verifica se a senha foi alterada corretamente
        assertEquals("novaSenha123", usuarioExistente.getSenha());

        // Verifica se o método findByLogin foi chamado corretamente
        verify(usuarioRepository, times(1)).findByLogin(dto.login());
        // Verifica se o método save foi chamado para salvar o usuário com a nova senha
        verify(usuarioRepository, times(1)).save(usuarioExistente);
    }

    @Test
    void deveLancarExcecaoQuandoSenhaNaoCorrespondente() {
        // Criação do DTO com dados de entrada
        UserChangePasswordRequestDTO dto = new UserChangePasswordRequestDTO("login1", "senhaErrada", "novaSenha123");

        // Criação de um usuário fictício com a senha correta
        Usuario usuarioExistente = new Usuario(1L, "Usuário 1", null, "email1@email.com", "login1", "senhaAntiga", null, null);

        // Mockando a busca do usuário pelo login
        when(usuarioRepository.findByLogin(dto.login())).thenReturn(Optional.of(usuarioExistente));

        // Executa o método de alteração de senha e verifica se a exceção PasswordDoesNotMatchException é lançada
        assertThrows(PasswordDoesNotMatchException.class, () -> changeSenhaUsuarioUseCase.execute(dto));

        // Verifica se o método findByLogin foi chamado corretamente
        verify(usuarioRepository, times(1)).findByLogin(dto.login());
        // Verifica se o método save NÃO foi chamado, pois a senha não corresponde
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        // Criação do DTO com dados de entrada
        UserChangePasswordRequestDTO dto = new UserChangePasswordRequestDTO("login1", "senhaAntiga", "novaSenha123");

        // Mockando que o usuário não foi encontrado
        when(usuarioRepository.findByLogin(dto.login())).thenReturn(Optional.empty());

        // Executa o método de alteração de senha e verifica se a exceção UserNotFoundException é lançada
        assertThrows(UserNotFoundException.class, () -> changeSenhaUsuarioUseCase.execute(dto));

        // Verifica se o método findByLogin foi chamado corretamente
        verify(usuarioRepository, times(1)).findByLogin(dto.login());
        // Verifica se o método save NÃO foi chamado, pois o usuário não foi encontrado
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

}
