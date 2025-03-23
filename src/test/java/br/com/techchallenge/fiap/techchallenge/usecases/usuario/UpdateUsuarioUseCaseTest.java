package br.com.techchallenge.fiap.techchallenge.usecases.usuario;

import br.com.techchallenge.fiap.techchallenge.dtos.UserUpdateRequestDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.UsuarioPublicDTO;
import br.com.techchallenge.fiap.techchallenge.entities.Endereco;
import br.com.techchallenge.fiap.techchallenge.entities.Usuario;
import br.com.techchallenge.fiap.techchallenge.errors.UserNotFoundException;
import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;
import br.com.techchallenge.fiap.techchallenge.usecases.usuario.GetUsuarioByIdUseCase;
import br.com.techchallenge.fiap.techchallenge.usecases.usuario.UpdateUsuarioUseCase;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateUsuarioUseCaseTest {
    @InjectMocks
    private UpdateUsuarioUseCase updateUsuarioUseCase;

    @Mock
    private UsuarioRepository usuarioRepository;

    private Endereco endereco;

    @Test
    void deveCriarUseCaseComSucesso() {
        assertNotNull(
                UpdateUsuarioUseCase.create(usuarioRepository));
    }
    @Test
    void deveCriarInstanciaDeUpdateUsuarioUseCase() {
        // Executa o método estático create() para criar uma instância do caso de uso
        UpdateUsuarioUseCase instance = UpdateUsuarioUseCase.create(usuarioRepository);

        // Verifica se a instância não é nula
        assertNotNull(instance);

        // Verifica se a instância criada é do tipo correto (UpdateUsuarioUseCase)
        assertTrue(instance instanceof UpdateUsuarioUseCase);
    }

    @Test
    void deveAtualizarUsuarioComSucesso() {
        // Criação do usuário de exemplo
        Long userId = 1L;
        UserUpdateRequestDTO userDTO = new UserUpdateRequestDTO("Novo Nome", "newemail@example.com");

        endereco = Endereco.builder()
                .descricaoLogradouro("Rua A")
                .descricaoBairro("Centro")
                .descricaoCidade("São Paulo")
                .descricaoComplemento("Apto 101")
                .descricaoEstado("SP")
                .numero("123")
                .numeroCEP("01010-000")
                .build();

        Usuario usuarioExistente = new Usuario(userId, "Usuário Antigo", endereco, "oldemail@example.com", "login1", "senha1", null, null);

        // Mockando a busca do usuário pelo id
        when(usuarioRepository.findById(userId)).thenReturn(Optional.of(usuarioExistente));

        // Mockando o comportamento de salvar o usuário atualizado
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Executa o método de atualização
        UsuarioPublicDTO resultado = updateUsuarioUseCase.execute(userId, userDTO);

        // Verificações:
        // Verifica se o resultado não é nulo
        assertNotNull(resultado);
        // Verifica se o email do usuário foi atualizado corretamente
        assertEquals("newemail@example.com", resultado.email());
        // Verifica se o nome completo do usuário foi atualizado corretamente
        assertEquals("Novo Nome", resultado.nomeCompleto());

        // Verifica se o método findById foi chamado corretamente
        verify(usuarioRepository, times(1)).findById(userId);
        // Verifica se o método save foi chamado com o usuário atualizado
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        // Criação do usuário de exemplo
        Long userId = 1L;
        UserUpdateRequestDTO userDTO = new UserUpdateRequestDTO("newemail@example.com", "Novo Nome");

        // Mockando a busca do usuário, simulando que não encontrou
        when(usuarioRepository.findById(userId)).thenReturn(Optional.empty());

        // Executa o método de atualização e verifica se a exceção é lançada
        assertThrows(UserNotFoundException.class, () -> updateUsuarioUseCase.execute(userId, userDTO));

        // Verifica se o método findById foi chamado
        verify(usuarioRepository, times(1)).findById(userId);
        // Verifica se o método save NÃO foi chamado, pois o usuário não foi encontrado
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }
}
