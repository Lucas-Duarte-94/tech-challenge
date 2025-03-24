package br.com.techchallenge.fiap.techchallenge.usecases.usuario;

import br.com.techchallenge.fiap.techchallenge.dtos.UsuarioPublicDTO;
import br.com.techchallenge.fiap.techchallenge.entities.Endereco;
import br.com.techchallenge.fiap.techchallenge.entities.Usuario;
import br.com.techchallenge.fiap.techchallenge.errors.UserNotFoundException;
import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;
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
public class GetUsuarioByIdUseCaseTest {
    @InjectMocks
    private GetUsuarioByIdUseCase getUsuarioByIdUseCase;

    @Mock
    private UsuarioRepository usuarioRepository;

    private Endereco endereco;

    @Test
    void deveCriarUseCaseComSucesso() {
        assertNotNull(
                GetUsuarioByIdUseCase.create(usuarioRepository));
    }
    @Test
    void deveCriarInstanciaDeGetUsuarioByIdUseCase() {
        // Executa o método estático create()
        GetUsuarioByIdUseCase instance = GetUsuarioByIdUseCase.create(usuarioRepository);

        // Verifica se a instância não é nula
        assertNotNull(instance);

        // Verifica se a instância criada é do tipo correto
        assertTrue(instance instanceof GetUsuarioByIdUseCase);
    }

    @Test
    void deveRetornarUsuarioQuandoIdExistir() {
        // Criando usuário fictício
        Long usuarioId = 1L;

        endereco = Endereco.builder()
                .descricaoLogradouro("Rua A")
                .descricaoBairro("Centro")
                .descricaoCidade("São Paulo")
                .descricaoComplemento("Apto 101")
                .descricaoEstado("SP")
                .numero("123")
                .numeroCEP("01010-000")
                .build();

        Usuario usuario = new Usuario(usuarioId, "Usuário Teste", endereco, "email@email.com", "login", "senha", null, null);

        // Mockando a chamada do repositório
        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));

        // Executa o caso de uso
        UsuarioPublicDTO resultado = getUsuarioByIdUseCase.execute(usuarioId);

        // Verificações
        assertNotNull(resultado);
        assertEquals(usuario.getNomeCompleto(), resultado.nomeCompleto());
        assertEquals(usuario.getEmail(), resultado.email());

        // Verifica se findById foi chamado corretamente
        verify(usuarioRepository, times(1)).findById(usuarioId);
    }

    @Test
    void deveLancarExcecaoQuandoIdNaoExistir() {
        // ID fictício inexistente
        Long usuarioId = 99L;

        // Simula que o repositório retorna um Optional vazio
        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.empty());

        // Verifica se a exceção é lançada
        assertThrows(UserNotFoundException.class, () -> getUsuarioByIdUseCase.execute(usuarioId));

        // Verifica que findById foi chamado corretamente
        verify(usuarioRepository, times(1)).findById(usuarioId);
    }
}
