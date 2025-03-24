package br.com.techchallenge.fiap.techchallenge.usecases.endereco;

import br.com.techchallenge.fiap.techchallenge.entities.Endereco;
import br.com.techchallenge.fiap.techchallenge.errors.RestauranteNotFoundException;
import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;
import br.com.techchallenge.fiap.techchallenge.mocks.InternalMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Sql(scripts = {"/db_load.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/db_clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class GetEnderecoByRestauranteIdUseCaseTest {

    @Mock
    private RestauranteRepository restauranteRepository;

    @InjectMocks
    private GetEnderecoByRestauranteIdUseCase getEnderecoByRestauranteIdUseCase;

    @Test
    void deveCriarInstanciaDeGetEnderecoByRestauranteIdUseCase() {
        GetEnderecoByRestauranteIdUseCase instance = GetEnderecoByRestauranteIdUseCase.create(restauranteRepository);
        assertNotNull(instance);
    }

    @Test
    void deveRetornarEnderecoComSucesso() {
        Long restauranteId = 1L;
        Endereco enderecoEsperado = InternalMock.getEndereco();

        // Mocking
        when(restauranteRepository.findEnderecoByRestauranteId(restauranteId)).thenReturn(enderecoEsperado);

        // Executando o caso de uso
        Endereco enderecoObtido = getEnderecoByRestauranteIdUseCase.execute(restauranteId);

        // Verificando os resultados
        assertNotNull(enderecoObtido);
        assertEquals(enderecoEsperado, enderecoObtido);
    }

}
