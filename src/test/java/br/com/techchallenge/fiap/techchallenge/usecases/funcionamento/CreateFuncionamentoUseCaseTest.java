package br.com.techchallenge.fiap.techchallenge.usecases.funcionamento;

import br.com.techchallenge.fiap.techchallenge.dtos.CreateFuncionamentoDTO;
import br.com.techchallenge.fiap.techchallenge.entities.Funcionamento;
import br.com.techchallenge.fiap.techchallenge.entities.Restaurante;
import br.com.techchallenge.fiap.techchallenge.entities.Usuario;
import br.com.techchallenge.fiap.techchallenge.errors.RestauranteNotFoundException;
import br.com.techchallenge.fiap.techchallenge.errors.UserDoesNotHavePermissionException;
import br.com.techchallenge.fiap.techchallenge.errors.UsuarioNotFoundException;
import br.com.techchallenge.fiap.techchallenge.mocks.InternalMock;
import br.com.techchallenge.fiap.techchallenge.mocks.InternalMockDto;
import br.com.techchallenge.fiap.techchallenge.repositories.FuncionamentoRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateFuncionamentoUseCaseTest {

    @Mock
    private FuncionamentoRepository funcionamentoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private RestauranteRepository restauranteRepository;

    @InjectMocks
    private CreateFuncionamentoUseCase createFuncionamentoUseCase;

    @Test
    void deveCriarFuncionamentoComSucesso() {
        Long usuarioId = 1L;
        Long restauranteId = 1L;

        Usuario usuario = InternalMock.getUsuario();
        Restaurante restaurante = InternalMock.getRestaurante();
        CreateFuncionamentoDTO dto = InternalMockDto.getCreateFuncionamentoDTO();

        Funcionamento funcionamentoCriado = InternalMock.getFuncionamento();

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));
        when(restauranteRepository.findById(restauranteId)).thenReturn(Optional.of(restaurante));
        when(funcionamentoRepository.save(any(Funcionamento.class))).thenReturn(funcionamentoCriado);

        Funcionamento funcionamento = createFuncionamentoUseCase.execute(dto);

        assertNotNull(funcionamento);
        assertEquals(funcionamentoCriado.getDiaSemana(), funcionamento.getDiaSemana());
        assertEquals(funcionamentoCriado.getHoraAbertura(), funcionamento.getHoraAbertura());
        assertEquals(funcionamentoCriado.getHoraFechamento(), funcionamento.getHoraFechamento());

        verify(usuarioRepository, times(1)).findById(usuarioId);
        verify(restauranteRepository, times(2)).findById(restauranteId);
        verify(funcionamentoRepository, times(1)).save(any(Funcionamento.class));
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        Long usuarioId = 1L;
        Long restauranteId = 1L;
        CreateFuncionamentoDTO dto = InternalMockDto.getCreateFuncionamentoDTO();

        assertThrows(UsuarioNotFoundException.class, () -> createFuncionamentoUseCase.execute(dto));

        verify(usuarioRepository, times(1)).findById(usuarioId);
        verify(restauranteRepository, never()).findById(restauranteId);
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoEncontrado() {
        Long usuarioId = 1L;
        Long restauranteId = 1L;

        Usuario usuario = InternalMock.getUsuario();
        CreateFuncionamentoDTO dto = InternalMockDto.getCreateFuncionamentoDTO();

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));
        when(restauranteRepository.findById(restauranteId)).thenReturn(Optional.empty());

        assertThrows(RestauranteNotFoundException.class, () -> createFuncionamentoUseCase.execute(dto));

        verify(usuarioRepository, times(1)).findById(usuarioId);
        verify(restauranteRepository, times(1)).findById(restauranteId);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoTemPermissao() {
        Long usuarioId = 1L;
        Long restauranteId = 1L;

        Usuario usuario = InternalMock.getUsuario();

        usuario.setIdUsuario(Long.MAX_VALUE);

        Restaurante restaurante = InternalMock.getRestaurante();
        CreateFuncionamentoDTO dto = InternalMockDto.getCreateFuncionamentoDTO();

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));
        when(restauranteRepository.findById(restauranteId)).thenReturn(Optional.of(restaurante));

        assertThrows(UserDoesNotHavePermissionException.class, () -> createFuncionamentoUseCase.execute(dto));

        verify(usuarioRepository, times(1)).findById(usuarioId);
        verify(restauranteRepository, times(1)).findById(restauranteId);
    }
}
