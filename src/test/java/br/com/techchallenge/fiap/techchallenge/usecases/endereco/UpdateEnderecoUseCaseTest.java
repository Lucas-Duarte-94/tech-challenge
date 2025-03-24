package br.com.techchallenge.fiap.techchallenge.usecases.endereco;

import br.com.techchallenge.fiap.techchallenge.dtos.UpdateEnderecoDTO;
import br.com.techchallenge.fiap.techchallenge.entities.Endereco;
import br.com.techchallenge.fiap.techchallenge.mocks.InternalMockDto;
import br.com.techchallenge.fiap.techchallenge.repositories.EnderecoRepository;
import br.com.techchallenge.fiap.techchallenge.mocks.InternalMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateEnderecoUseCaseTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private UpdateEnderecoUseCase updateEnderecoUseCase;

    @Test
    void deveCriarInstanciaDeUpdateEnderecoUseCase() {
        UpdateEnderecoUseCase instance = UpdateEnderecoUseCase.create(enderecoRepository);
        assertNotNull(instance);
    }

    @Test
    void deveAtualizarEnderecoComSucesso() {
        Long enderecoId = 1L;
        Endereco enderecoExistente = InternalMock.getEndereco();
        UpdateEnderecoDTO updateEnderecoDTO = InternalMockDto.getUpdateEnderecoDto();
        Endereco enderecoAtualizado = new Endereco(
                enderecoId, "Novo Bairro", "Novo Logradouro", "123", "Complemento", "Nova Cidade", "Novo Estado", "12345-678"
        );

        // Mocking
        when(enderecoRepository.findById(enderecoId)).thenReturn(java.util.Optional.of(enderecoExistente));
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(enderecoAtualizado);

        // Executando o caso de uso
        Endereco enderecoRetornado = updateEnderecoUseCase.execute(enderecoId, updateEnderecoDTO);

        // Verificando os resultados
        assertNotNull(enderecoRetornado);
        assertEquals(enderecoAtualizado.getDescricaoBairro(), enderecoRetornado.getDescricaoBairro());
        assertEquals(enderecoAtualizado.getDescricaoLogradouro(), enderecoRetornado.getDescricaoLogradouro());
        assertEquals(enderecoAtualizado.getNumero(), enderecoRetornado.getNumero());
        assertEquals(enderecoAtualizado.getDescricaoComplemento(), enderecoRetornado.getDescricaoComplemento());
        assertEquals(enderecoAtualizado.getDescricaoCidade(), enderecoRetornado.getDescricaoCidade());
        assertEquals(enderecoAtualizado.getDescricaoEstado(), enderecoRetornado.getDescricaoEstado());
        assertEquals(enderecoAtualizado.getNumeroCEP(), enderecoRetornado.getNumeroCEP());

        verify(enderecoRepository, times(1)).findById(enderecoId);
        verify(enderecoRepository, times(1)).save(any(Endereco.class));
    }

}
