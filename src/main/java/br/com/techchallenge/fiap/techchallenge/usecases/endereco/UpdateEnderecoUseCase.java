package br.com.techchallenge.fiap.techchallenge.usecases.endereco;

import br.com.techchallenge.fiap.techchallenge.repositories.EnderecoRepository;

public class UpdateEnderecoUseCase {
    private final EnderecoRepository enderecoRepository;

    private UpdateEnderecoUseCase(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public static UpdateEnderecoUseCase create(EnderecoRepository enderecoRepository) {
        return new UpdateEnderecoUseCase(enderecoRepository);
    }
}
