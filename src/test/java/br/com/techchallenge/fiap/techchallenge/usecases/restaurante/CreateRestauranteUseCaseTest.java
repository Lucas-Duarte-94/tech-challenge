package br.com.techchallenge.fiap.techchallenge.usecases.restaurante;

import br.com.techchallenge.fiap.techchallenge.dtos.CreateRestauranteDTO;
import br.com.techchallenge.fiap.techchallenge.entities.Endereco;
import br.com.techchallenge.fiap.techchallenge.entities.Restaurante;
import br.com.techchallenge.fiap.techchallenge.entities.TipoUsuario;
import br.com.techchallenge.fiap.techchallenge.entities.Usuario;
import br.com.techchallenge.fiap.techchallenge.enums.TipoUsuarioEnum;
import br.com.techchallenge.fiap.techchallenge.errors.UserDoesNotHavePermissionException;
import br.com.techchallenge.fiap.techchallenge.errors.UsuarioNotFoundException;
import br.com.techchallenge.fiap.techchallenge.mocks.InternalMock;
import br.com.techchallenge.fiap.techchallenge.mocks.InternalMockDto;
import br.com.techchallenge.fiap.techchallenge.repositories.EnderecoRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateRestauranteUseCaseTest {

    @Mock
    private RestauranteRepository restauranteRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private CreateRestauranteUseCase createRestauranteUseCase;

    @Test
    void deveCriarUseCaseComSucesso() {
        assertNotNull(
                CreateRestauranteUseCase.create(restauranteRepository, usuarioRepository, enderecoRepository));
    }
    @Test
    void deveCriarRestauranteQuandoUsuarioForValido() {
        Long usuarioId = 2L;
        CreateRestauranteDTO createDTO = InternalMockDto.getCreateRestauranteDTO();
        Usuario usuario = InternalMock.getUsuario();

        when(usuarioRepository.findById(usuarioId)).thenReturn(java.util.Optional.of(usuario));
        when(restauranteRepository.save(any(Restaurante.class))).thenReturn(new Restaurante());
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(new Endereco());

        Restaurante result = createRestauranteUseCase.execute(createDTO);

        assertNotNull(result);
        verify(usuarioRepository, times(1)).findById(usuarioId);
        verify(enderecoRepository, times(1)).save(any(Endereco.class));
        verify(restauranteRepository, times(1)).save(any(Restaurante.class));
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoForEncontrado() {
        Long usuarioId = 1L;
        CreateRestauranteDTO createDTO = new CreateRestauranteDTO(
                "Restaurante Teste", "Italiana", InternalMockDto.getEnderecoDto(), usuarioId
        );

        when(usuarioRepository.findById(usuarioId)).thenReturn(java.util.Optional.empty());

        assertThrows(UsuarioNotFoundException.class, () -> createRestauranteUseCase.execute(createDTO));
        verify(usuarioRepository, times(1)).findById(usuarioId);
        verify(enderecoRepository, never()).save(any(Endereco.class));
        verify(restauranteRepository, never()).save(any(Restaurante.class));
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoForDonoRestaurante() {
        Long usuarioId = 1L;
        CreateRestauranteDTO createDTO = new CreateRestauranteDTO(
                "Restaurante Teste", "Italiana",  InternalMockDto.getEnderecoDto(), usuarioId
        );
        Usuario usuario = InternalMock.getUsuario();
        usuario.setTipoUsuario(new TipoUsuario(1L, TipoUsuarioEnum.CLIENTE));

        when(usuarioRepository.findById(usuarioId)).thenReturn(java.util.Optional.of(usuario));

        assertThrows(UserDoesNotHavePermissionException.class, () -> createRestauranteUseCase.execute(createDTO));
        verify(usuarioRepository, times(1)).findById(usuarioId);
        verify(enderecoRepository, never()).save(any(Endereco.class));
        verify(restauranteRepository, never()).save(any(Restaurante.class));
    }
}
