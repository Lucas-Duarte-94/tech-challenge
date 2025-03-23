package br.com.techchallenge.fiap.techchallenge.usecases.usuario;

import br.com.techchallenge.fiap.techchallenge.dtos.UsuarioPublicDTO;
import br.com.techchallenge.fiap.techchallenge.entities.Endereco;
import br.com.techchallenge.fiap.techchallenge.entities.Usuario;
import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;
import br.com.techchallenge.fiap.techchallenge.usecases.usuario.GetAllUsuarioUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllUsuarioUseCaseTest {
    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private GetAllUsuarioUseCase getAllUsuarioUseCase;

    private Endereco endereco;

    @Test
    void deveCriarUseCaseComSucesso() {
        assertNotNull(
                GetAllUsuarioUseCase.create(usuarioRepository));
    }
    @Test
    void deveCriarInstanciaDeGetAllUsuarioUseCase() {
        // Executa o método estático create()
        GetAllUsuarioUseCase instance = GetAllUsuarioUseCase.create(usuarioRepository);

        // Verifica se a instância não é nula
        assertNotNull(instance);

        // Verifica se a instância criada é do tipo correto
        assertTrue(instance instanceof GetAllUsuarioUseCase);
    }

    @Test
    void deveRetornarListaDeUsuariosComPaginacao() {
        // Definir parâmetros de paginação
        int size = 2;
        int page = 0;
        Pageable pageable = PageRequest.of(page, size);

        endereco = Endereco.builder()
                .descricaoLogradouro("Rua A")
                .descricaoBairro("Centro")
                .descricaoCidade("São Paulo")
                .descricaoComplemento("Apto 101")
                .descricaoEstado("SP")
                .numero("123")
                .numeroCEP("01010-000")
                .build();


        // Criando lista fictícia de usuários
        Usuario usuario1 = new Usuario(1L, "Usuário 1",endereco, "email1@email.com", "login1", "senha1", null, null);
        Usuario usuario2 = new Usuario(2L, "Usuário 2",endereco, "email2@email.com", "login2", "senha2", null, null);

        // Criando uma página de usuários simulada
        Page<Usuario> usuarioPage = new PageImpl<>(List.of(usuario1, usuario2), pageable, 2);

        // Mockando a chamada do repositório
        when(usuarioRepository.findAll(pageable)).thenReturn(usuarioPage);

        // Executa o caso de uso
        List<UsuarioPublicDTO> resultado = getAllUsuarioUseCase.execute(size, page);

        // Verificações
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Usuário 1", resultado.get(0).nomeCompleto());
        assertEquals("Usuário 2", resultado.get(1).nomeCompleto());

        // Verifica se findAll foi chamado corretamente
        verify(usuarioRepository, times(1)).findAll(pageable);
    }
}
