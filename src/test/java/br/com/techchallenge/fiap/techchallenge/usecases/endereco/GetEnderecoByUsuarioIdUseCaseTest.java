package br.com.techchallenge.fiap.techchallenge.usecases.endereco;

import br.com.techchallenge.fiap.techchallenge.entities.Endereco;
import br.com.techchallenge.fiap.techchallenge.errors.UsuarioNotFoundException;
import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;
import br.com.techchallenge.fiap.techchallenge.mocks.InternalMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetEnderecoByUsuarioIdUseCaseTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private GetEnderecoByUsuarioIdUseCase getEnderecoByUsuarioIdUseCase;

    @Test
    void deveCriarInstanciaDeGetEnderecoByUsuarioIdUseCase() {
        GetEnderecoByUsuarioIdUseCase instance = GetEnderecoByUsuarioIdUseCase.create(usuarioRepository);
        assertNotNull(instance);
    }

    @Test
    void deveRetornarEnderecoComSucesso() {
        Long usuarioId = 1L;
        Endereco enderecoEsperado = InternalMock.getEndereco();

        // Mocking
        when(usuarioRepository.findEnderecoByUsuarioId(usuarioId)).thenReturn(enderecoEsperado);

        // Executando o caso de uso
        Endereco enderecoObtido = getEnderecoByUsuarioIdUseCase.execute(usuarioId);

        // Verificando os resultados
        assertNotNull(enderecoObtido);
        assertEquals(enderecoEsperado, enderecoObtido);
    }

}
