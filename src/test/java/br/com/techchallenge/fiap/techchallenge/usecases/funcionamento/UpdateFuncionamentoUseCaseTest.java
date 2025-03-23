package br.com.techchallenge.fiap.techchallenge.usecases.funcionamento;

import br.com.techchallenge.fiap.techchallenge.dtos.UpdateFuncionamentoDTO;
import br.com.techchallenge.fiap.techchallenge.entities.Funcionamento;
import br.com.techchallenge.fiap.techchallenge.entities.Restaurante;
import br.com.techchallenge.fiap.techchallenge.entities.Usuario;
import br.com.techchallenge.fiap.techchallenge.errors.FuncionamentoNotFoundException;
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
public class UpdateFuncionamentoUseCaseTest {

    @Mock
    private FuncionamentoRepository funcionamentoRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private RestauranteRepository restauranteRepository;

    @InjectMocks
    private UpdateFuncionamentoUseCase updateFuncionamentoUseCase;

    @Test
    void deveAtualizarFuncionamentoComSucesso() {
        Long funcionamentoId = 1L;
        UpdateFuncionamentoDTO updateFuncionamentoDTO = InternalMockDto.getUpdateFuncionamentoDTO();
        Usuario usuario = InternalMock.getUsuario();
        Restaurante restaurante = InternalMock.getRestaurante();
        Funcionamento funcionamentoExistente = InternalMock.getFuncionamento();

        Funcionamento funcionamentoAtualizado = new Funcionamento(
                funcionamentoExistente.getIdFuncionamento(),
                updateFuncionamentoDTO.diaSemana(),
                updateFuncionamentoDTO.horaAbertura(),
                updateFuncionamentoDTO.horaFechamento(),
                restaurante
        );

        when(usuarioRepository.findById(updateFuncionamentoDTO.usuarioId())).thenReturn(Optional.of(usuario));
        when(restauranteRepository.findById(updateFuncionamentoDTO.restauranteId())).thenReturn(Optional.of(restaurante));
        when(funcionamentoRepository.findById(funcionamentoId)).thenReturn(Optional.of(funcionamentoExistente));
        when(funcionamentoRepository.save(any(Funcionamento.class))).thenReturn(funcionamentoAtualizado);

        Funcionamento resultado = updateFuncionamentoUseCase.execute(funcionamentoId, updateFuncionamentoDTO);

        assertNotNull(resultado);
        assertEquals(funcionamentoAtualizado.getDiaSemana(), resultado.getDiaSemana());
        assertEquals(funcionamentoAtualizado.getHoraAbertura(), resultado.getHoraAbertura());
        assertEquals(funcionamentoAtualizado.getHoraFechamento(), resultado.getHoraFechamento());

        verify(usuarioRepository, times(1)).findById(updateFuncionamentoDTO.usuarioId());
        verify(restauranteRepository, times(1)).findById(updateFuncionamentoDTO.restauranteId());
        verify(funcionamentoRepository, times(1)).findById(funcionamentoId);
        verify(funcionamentoRepository, times(1)).save(any(Funcionamento.class));
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        Long funcionamentoId = 1L;
        UpdateFuncionamentoDTO updateFuncionamentoDTO = InternalMockDto.getUpdateFuncionamentoDTO();

        when(usuarioRepository.findById(updateFuncionamentoDTO.usuarioId())).thenReturn(Optional.empty());

        assertThrows(UsuarioNotFoundException.class, () -> updateFuncionamentoUseCase.execute(funcionamentoId, updateFuncionamentoDTO));

        verify(usuarioRepository, times(1)).findById(updateFuncionamentoDTO.usuarioId());
        verify(restauranteRepository, never()).findById(updateFuncionamentoDTO.restauranteId());
        verify(funcionamentoRepository, never()).findById(funcionamentoId);
    }

    @Test
    void deveLancarExcecaoQuandoRestauranteNaoEncontrado() {
        Long funcionamentoId = 1L;
        UpdateFuncionamentoDTO updateFuncionamentoDTO = InternalMockDto.getUpdateFuncionamentoDTO();
        Usuario usuario = InternalMock.getUsuario();

        when(usuarioRepository.findById(updateFuncionamentoDTO.usuarioId())).thenReturn(Optional.of(usuario));
        when(restauranteRepository.findById(updateFuncionamentoDTO.restauranteId())).thenReturn(Optional.empty());

        assertThrows(RestauranteNotFoundException.class, () -> updateFuncionamentoUseCase.execute(funcionamentoId, updateFuncionamentoDTO));

        verify(usuarioRepository, times(1)).findById(updateFuncionamentoDTO.usuarioId());
        verify(restauranteRepository, times(1)).findById(updateFuncionamentoDTO.restauranteId());
        verify(funcionamentoRepository, never()).findById(funcionamentoId);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoTemPermissao() {
        Long funcionamentoId = 1L;
        UpdateFuncionamentoDTO updateFuncionamentoDTO = InternalMockDto.getUpdateFuncionamentoDTO();
        Usuario usuario = InternalMock.getUsuario();
        Restaurante restaurante = InternalMock.getRestaurante();

        usuario.setIdUsuario(Long.MAX_VALUE); // Usuário com ID diferente do dono do restaurante

        when(usuarioRepository.findById(updateFuncionamentoDTO.usuarioId())).thenReturn(Optional.of(usuario));
        when(restauranteRepository.findById(updateFuncionamentoDTO.restauranteId())).thenReturn(Optional.of(restaurante));

        assertThrows(UserDoesNotHavePermissionException.class, () -> updateFuncionamentoUseCase.execute(funcionamentoId, updateFuncionamentoDTO));

        verify(usuarioRepository, times(1)).findById(updateFuncionamentoDTO.usuarioId());
        verify(restauranteRepository, times(1)).findById(updateFuncionamentoDTO.restauranteId());
        verify(funcionamentoRepository, never()).findById(funcionamentoId);
    }

    @Test
    void deveLancarExcecaoQuandoFuncionamentoNaoEncontrado() {
        Long funcionamentoId = 1L;
        UpdateFuncionamentoDTO updateFuncionamentoDTO = InternalMockDto.getUpdateFuncionamentoDTO();
        Usuario usuario = InternalMock.getUsuario();
        Restaurante restaurante = InternalMock.getRestaurante();

        when(usuarioRepository.findById(updateFuncionamentoDTO.usuarioId())).thenReturn(Optional.of(usuario));
        when(restauranteRepository.findById(updateFuncionamentoDTO.restauranteId())).thenReturn(Optional.of(restaurante));
        when(funcionamentoRepository.findById(funcionamentoId)).thenReturn(Optional.empty());

        assertThrows(FuncionamentoNotFoundException.class, () -> updateFuncionamentoUseCase.execute(funcionamentoId, updateFuncionamentoDTO));

        verify(usuarioRepository, times(1)).findById(updateFuncionamentoDTO.usuarioId());
        verify(restauranteRepository, times(1)).findById(updateFuncionamentoDTO.restauranteId());
        verify(funcionamentoRepository, times(1)).findById(funcionamentoId);
    }

    @Test
    void deveCriarUseCaseComSucesso() {
        assertNotNull(
                UpdateFuncionamentoUseCase.create(funcionamentoRepository, usuarioRepository, restauranteRepository));
    }
}
