package br.com.techchallenge.fiap.techchallenge.usecases.restaurante;

import br.com.techchallenge.fiap.techchallenge.entities.Restaurante;
import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Sql(scripts = {"/db_load.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/db_clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class GetAllRestauranteUseCaseTest {

    @Mock
    private RestauranteRepository restauranteRepository;

    @InjectMocks
    private GetAllRestauranteUseCase getAllRestauranteUseCase;

    @Test
    void deveRetornarListaDeRestaurantesQuandoExistirem() {
        // Dados de entrada
        int page = 0;
        int size = 10;

        // Mockando o retorno do repositório
        List<Restaurante> mockRestaurantes = List.of(new Restaurante(), new Restaurante(), new Restaurante());
        Pageable pageable = PageRequest.of(page, size);

        when(restauranteRepository.findAll(pageable)).thenReturn(new PageImpl<Restaurante>(mockRestaurantes, pageable, mockRestaurantes.size()));

        // Executando o caso de uso
        List<Restaurante> result = getAllRestauranteUseCase.execute(size, page);

        // Validando o resultado
        assertNotNull(result);
        assertEquals(3, result.size()); // Devem retornar 3 restaurantes
        verify(restauranteRepository, times(1)).findAll(pageable); // Verificando se findAll foi chamado com a página e o tamanho corretos
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoExistiremRestaurantes() {
        // Dados de entrada
        int page = 0;
        int size = 10;

        // Mockando o retorno do repositório
        List<Restaurante> mockRestaurantes = List.of(); // Nenhum restaurante

        Pageable pageable = PageRequest.of(page, size);
        when(restauranteRepository.findAll(pageable)).thenReturn(new PageImpl<Restaurante>(mockRestaurantes, pageable, mockRestaurantes.size()));

        // Executando o caso de uso
        List<Restaurante> result = getAllRestauranteUseCase.execute(size, page);

        // Validando o resultado
        assertNotNull(result);
        assertTrue(result.isEmpty()); // Devem retornar uma lista vazia
        verify(restauranteRepository, times(1)).findAll(pageable); // Verificando se findAll foi chamado com a página e o tamanho corretos
    }

    @Test
    void deveChamarFindAllComParametrosCorretos() {
        // Dados de entrada
        int page = 1;
        int size = 20;

        // Mockando o retorno do repositório
        List<Restaurante> mockRestaurantes = List.of(new Restaurante(), new Restaurante());
        Pageable pageable = PageRequest.of(page, size);

        when(restauranteRepository.findAll(pageable)).thenReturn(new PageImpl<Restaurante>(mockRestaurantes, pageable, mockRestaurantes.size()));

        // Executando o caso de uso
        getAllRestauranteUseCase.execute(size, page);

        // Verificando se o repositório foi chamado com o PageRequest correto
        verify(restauranteRepository, times(1)).findAll(pageable);
    }

    @Test
    void deveCriarUseCaseComSucesso() {
        assertNotNull(
                GetAllRestauranteUseCase.create(restauranteRepository));
    }
}
