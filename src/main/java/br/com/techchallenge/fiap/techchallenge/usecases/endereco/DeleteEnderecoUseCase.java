package br.com.techchallenge.fiap.techchallenge.usecases.endereco;

import br.com.techchallenge.fiap.techchallenge.repositories.EnderecoRepository;

public class DeleteEnderecoUseCase {
    private final EnderecoRepository enderecoRepository;

    private DeleteEnderecoUseCase(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public static DeleteEnderecoUseCase create(EnderecoRepository enderecoRepository) {
        return new DeleteEnderecoUseCase(enderecoRepository);
    }
}
