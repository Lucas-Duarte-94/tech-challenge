package br.com.techchallenge.fiap.techchallenge.usecases.usuario;
import br.com.techchallenge.fiap.techchallenge.dtos.CreateEnderecoDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.CreateUsuarioDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.UsuarioPublicDTO;
import br.com.techchallenge.fiap.techchallenge.entities.Endereco;
import br.com.techchallenge.fiap.techchallenge.entities.TipoUsuario;
import br.com.techchallenge.fiap.techchallenge.entities.Usuario;
import br.com.techchallenge.fiap.techchallenge.enums.TipoUsuarioEnum;
import br.com.techchallenge.fiap.techchallenge.errors.TipoUsuarioNotFoundException;
import br.com.techchallenge.fiap.techchallenge.errors.UsuarioAlreadyExistsException;
import br.com.techchallenge.fiap.techchallenge.repositories.EnderecoRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.TipoUsuarioRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;
import br.com.techchallenge.fiap.techchallenge.usecases.usuario.CreateUsuarioUseCase;
import br.com.techchallenge.fiap.techchallenge.usecases.usuario.DeleteUsuarioUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateUsuarioUseCaseTest {
    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private CreateUsuarioUseCase createUsuarioUseCase;
    private CreateUsuarioDTO usuarioDTO;
    private TipoUsuario tipoUsuario;
    private Endereco endereco;
    private Usuario usuario;

    @BeforeEach
    void setUp() {

        tipoUsuario = new TipoUsuario(1L, TipoUsuarioEnum.CLIENTE);

        CreateEnderecoDTO enderecoDTO = new CreateEnderecoDTO("Rua A", "123", "Apto 101", "Centro", "São Paulo", "SP","01010-000", null, null );


        usuarioDTO = new CreateUsuarioDTO(
                "Usuário Teste",
                "teste@email.com",
                "usuarioteste",
                "senha123",
                enderecoDTO,
                tipoUsuario.getDescricaoTipoUsuario()
        );

        usuario = Usuario.builder()
                .nomeCompleto(usuarioDTO.nomeCompleto())
                .endereco(endereco)
                .email(usuarioDTO.email())
                .login(usuarioDTO.login())
                .senha(usuarioDTO.senha())
                .tipoUsuario(tipoUsuario)
                .build();
    }

    @Test
    void deveCriarUseCaseComSucesso() {
        assertNotNull(
                CreateUsuarioUseCase.create(usuarioRepository, tipoUsuarioRepository, enderecoRepository));
    }
    @Test
    void deveCriarUsuarioComSucesso() {
        // Simula que o tipo de usuário existe
        when(tipoUsuarioRepository.findByDescricaoTipoUsuario(TipoUsuarioEnum.CLIENTE))
                .thenReturn(Optional.of(tipoUsuario));

        // Simula que o login não está em uso
        when(usuarioRepository.findByLogin("usuarioteste"))
                .thenReturn(Optional.empty());

        // Simula a criação do usuário
        when(usuarioRepository.save(any(Usuario.class)))
                .thenReturn(usuario);

        // Executa o caso de uso
        UsuarioPublicDTO resultado = createUsuarioUseCase.execute(usuarioDTO);

        // Verificações
        assertNotNull(resultado);
        assertEquals(usuario.getNomeCompleto(), resultado.nomeCompleto());
        assertEquals(usuario.getEmail(), resultado.email());

        // Verifica se os métodos foram chamados corretamente
        verify(tipoUsuarioRepository, times(1)).findByDescricaoTipoUsuario(TipoUsuarioEnum.CLIENTE);
        verify(usuarioRepository, times(1)).findByLogin("usuarioteste");
        verify(enderecoRepository, times(1)).save(any(Endereco.class));
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void deveLancarExcecaoSeTipoUsuarioNaoForEncontrado() {
        // Simula que o tipo de usuário não existe
        when(tipoUsuarioRepository.findByDescricaoTipoUsuario(TipoUsuarioEnum.CLIENTE))
                .thenReturn(Optional.empty());

        // Verifica se a exceção correta é lançada
        assertThrows(TipoUsuarioNotFoundException.class, () -> createUsuarioUseCase.execute(usuarioDTO));

        // Verifica se nenhum usuário foi salvo
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    @Test
    void deveLancarExcecaoSeUsuarioJaExiste() {
        // Simula que o tipo de usuário existe
        when(tipoUsuarioRepository.findByDescricaoTipoUsuario(TipoUsuarioEnum.CLIENTE))
                .thenReturn(Optional.of(tipoUsuario));

        // Simula que o login já está em uso
        when(usuarioRepository.findByLogin("usuarioteste"))
                .thenReturn(Optional.of(usuario));

        // Verifica se a exceção correta é lançada
        assertThrows(UsuarioAlreadyExistsException.class, () -> createUsuarioUseCase.execute(usuarioDTO));

        // Verifica que o usuário não foi salvo
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    @Test
    void deveCriarInstanciaDeDeleteUsuarioUseCase() {
        // Executa o método estático create()
        CreateUsuarioUseCase instance = CreateUsuarioUseCase.create(usuarioRepository, tipoUsuarioRepository, enderecoRepository);

        // Verifica se a instância não é nula
        assertNotNull(instance);

        // Verifica se a instância criada é realmente do tipo DeleteUsuarioUseCase
        assertTrue(instance instanceof CreateUsuarioUseCase);
    }
}
