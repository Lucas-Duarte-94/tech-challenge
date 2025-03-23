package br.com.techchallenge.fiap.techchallenge.usecases.endereco;

import br.com.techchallenge.fiap.techchallenge.dtos.CreateEnderecoDTO;
import br.com.techchallenge.fiap.techchallenge.entities.Endereco;
import br.com.techchallenge.fiap.techchallenge.repositories.EnderecoRepository;

public class CreateEnderecoUseCase {
    private final EnderecoRepository enderecoRepository;

    private CreateEnderecoUseCase(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public static CreateEnderecoUseCase create(EnderecoRepository enderecoRepository) {
        return new CreateEnderecoUseCase(enderecoRepository);
    }

    public Endereco execute(CreateEnderecoDTO request) {
        Endereco novoEndereco = Endereco.builder()
                .descricaoBairro(request.descricaoBairro())
                .descricaoLogradouro(request.descricaoLogradouro())
                .numero(request.numero())
                .descricaoComplemento(request.descricaoComplemento())
                .descricaoCidade(request.descricaoCidade())
                .descricaoEstado(request.descricaoEstado())
                .numeroCEP(request.numeroCep())
                .build();

        return enderecoRepository.save(novoEndereco);
    }
}

