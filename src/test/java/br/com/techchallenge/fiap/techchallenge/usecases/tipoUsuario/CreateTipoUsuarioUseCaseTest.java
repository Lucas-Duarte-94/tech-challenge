package br.com.techchallenge.fiap.techchallenge.usecases.tipoUsuario;

import br.com.techchallenge.fiap.techchallenge.entities.TipoUsuario;
import br.com.techchallenge.fiap.techchallenge.enums.TipoUsuarioEnum;
import br.com.techchallenge.fiap.techchallenge.repositories.TipoUsuarioRepository;
import br.com.techchallenge.fiap.techchallenge.usecases.restaurante.UpdateRestauranteUseCase;
import br.com.techchallenge.fiap.techchallenge.usecases.tipousuario.CreateTipoUsuarioUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateTipoUsuarioUseCaseTest {
    @Mock
    private TipoUsuarioRepository tipoUsuarioRepository;

    @InjectMocks
    private CreateTipoUsuarioUseCase createTipoUsuarioUseCase;

    @Test
    void deveCriarUseCaseComSucesso() {
        assertNotNull(
                createTipoUsuarioUseCase.create(tipoUsuarioRepository));
    }
    @Test
    void deveCriarInstanciaDeCreateTipoUsuarioUseCase() {
        // Executa o método estático create() para criar uma instância do caso de uso
        CreateTipoUsuarioUseCase instance = CreateTipoUsuarioUseCase.create(tipoUsuarioRepository);

        // Verifica se a instância não é nula
        assertNotNull(instance);

        // Verifica se a instância criada é do tipo correto (CreateTipoUsuarioUseCase)
        assertTrue(instance instanceof CreateTipoUsuarioUseCase);
    }

    @Test
    void deveCriarETipoUsuarioComSucesso() {
        // Descrição do Tipo de Usuário
        String descricaoTipoUsuario = "ADMIN";

        // Criando o TipoUsuario esperado
        TipoUsuario tipoUsuarioEsperado = TipoUsuario.builder()
                .descricaoTipoUsuario(TipoUsuarioEnum.fromValue(descricaoTipoUsuario))
                .build();

        // Mockando o comportamento do repositório para retornar o TipoUsuario criado
        when(tipoUsuarioRepository.save(any(TipoUsuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Executa o método de criação de TipoUsuario
        TipoUsuario tipoUsuarioCriado = createTipoUsuarioUseCase.execute(descricaoTipoUsuario);

        // Verifica se o tipo de usuário foi criado com sucesso
        assertNotNull(tipoUsuarioCriado);
        assertEquals(descricaoTipoUsuario, tipoUsuarioCriado.getDescricaoTipoUsuario().toString());

        // Verifica se o método save foi chamado corretamente no repositório
        verify(tipoUsuarioRepository, times(1)).save(any(TipoUsuario.class));
    }

    @Test
    void deveLancarExcecaoQuandoDescricaoTipoUsuarioInvalida() {
        // Descrição de Tipo de Usuário inválida
        String descricaoTipoUsuarioInvalida = "INVALID_TYPE";

        // Simulando que o TipoUsuarioEnum não reconhece esse valor
        assertThrows(IllegalArgumentException.class, () -> TipoUsuarioEnum.fromValue(descricaoTipoUsuarioInvalida));
    }
}
