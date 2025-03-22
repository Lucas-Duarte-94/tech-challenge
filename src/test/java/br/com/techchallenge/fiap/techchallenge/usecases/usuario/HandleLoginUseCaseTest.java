package br.com.techchallenge.fiap.techchallenge.usecases.usuario;

import br.com.techchallenge.fiap.techchallenge.dtos.UserLoginRequestDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.UsuarioPublicDTO;
import br.com.techchallenge.fiap.techchallenge.entities.Endereco;
import br.com.techchallenge.fiap.techchallenge.entities.Usuario;
import br.com.techchallenge.fiap.techchallenge.errors.UserNotFoundException;
import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;
import br.com.techchallenge.fiap.techchallenge.usecases.usuario.HandleLoginUseCase;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HandleLoginUseCaseTest {
    @InjectMocks
    private HandleLoginUseCase handleLoginUseCase;

    @Mock
    private UsuarioRepository usuarioRepository;

    private Endereco endereco;


    @Test
    void deveCriarInstanciaDeHandleLoginUseCase() {
        // Executa o método estático create()
        HandleLoginUseCase instance = HandleLoginUseCase.create(usuarioRepository);

        // Verifica se a instância não é nula
        assertNotNull(instance);

        // Verifica se a instância criada é do tipo correto
        assertTrue(instance instanceof HandleLoginUseCase);
    }

    @Test
    void deveRetornarUsuarioQuandoLoginExistir() {
        // Criando usuário fictício
        String login = "usuarioTeste";
        String senha = "senha";
        endereco = Endereco.builder()
                .descricaoLogradouro("Rua A")
                .descricaoBairro("Centro")
                .descricaoCidade("São Paulo")
                .descricaoComplemento("Apto 101")
                .descricaoEstado("SP")
                .numero("123")
                .numeroCEP("01010-000")
                .build();

        Usuario usuario = new Usuario(1L, "Usuário Teste",endereco,  "email@email.com", login, "senha123", null, null);
        UserLoginRequestDTO requestDTO = new UserLoginRequestDTO(login, senha);

        // Mockando a chamada do repositório
        when(usuarioRepository.findByLogin(login)).thenReturn(Optional.of(usuario));

        // Executa o caso de uso
        UsuarioPublicDTO resultado = handleLoginUseCase.execute(requestDTO);

        // Verificações
        assertNotNull(resultado);
        assertEquals(usuario.getNomeCompleto(), resultado.nomeCompleto());
        assertEquals(usuario.getEmail(), resultado.email());

        // Verifica se findByLogin foi chamado corretamente
        verify(usuarioRepository, times(1)).findByLogin(login);
    }

    @Test
    void deveLancarExcecaoQuandoLoginNaoExistir() {
        // Criando login fictício inexistente
        String login = "usuarioInexistente";
        String senha = "senha";
        UserLoginRequestDTO requestDTO = new UserLoginRequestDTO(login, senha);

        // Simula que o repositório retorna um Optional vazio
        when(usuarioRepository.findByLogin(login)).thenReturn(Optional.empty());

        // Verifica se a exceção é lançada
        assertThrows(UserNotFoundException.class, () -> handleLoginUseCase.execute(requestDTO));

        // Verifica que findByLogin foi chamado corretamente
        verify(usuarioRepository, times(1)).findByLogin(login);
    }

}
