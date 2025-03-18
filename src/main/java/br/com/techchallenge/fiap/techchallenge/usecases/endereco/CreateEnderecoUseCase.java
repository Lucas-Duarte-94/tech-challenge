package br.com.techchallenge.fiap.techchallenge.usecases.endereco;

import br.com.techchallenge.fiap.techchallenge.repositories.EnderecoRepository;

public class CreateEnderecoUseCase {
    private final EnderecoRepository enderecoRepository;

    private CreateEnderecoUseCase(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public static CreateEnderecoUseCase create(EnderecoRepository enderecoRepository) {
        return new CreateEnderecoUseCase(enderecoRepository);
    }
}
