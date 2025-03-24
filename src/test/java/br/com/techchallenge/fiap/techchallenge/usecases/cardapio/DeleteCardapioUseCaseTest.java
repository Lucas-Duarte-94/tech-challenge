package br.com.techchallenge.fiap.techchallenge.usecases.cardapio;

import br.com.techchallenge.fiap.techchallenge.repositories.CardapioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@Sql(scripts = {"/db_load.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/db_clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class DeleteCardapioUseCaseTest {

    @Mock
    private CardapioRepository cardapioRepository;

    @InjectMocks
    private DeleteCardapioUseCase deleteCardapioUseCase;

    @Test
    void deveCriarInstanciaDeDeleteCardapioUseCase() {
        DeleteCardapioUseCase instance = DeleteCardapioUseCase.create(cardapioRepository);
        assertNotNull(instance);
        assertTrue(instance instanceof DeleteCardapioUseCase);
    }

    @Test
    void deveDeletarCardapioComSucesso() {
        Long cardapioId = 1L;

        deleteCardapioUseCase.execute(cardapioId);

        verify(cardapioRepository, times(1)).deleteById(cardapioId);
    }

}
