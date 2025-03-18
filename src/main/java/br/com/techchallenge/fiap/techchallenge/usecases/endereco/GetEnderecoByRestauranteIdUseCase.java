package br.com.techchallenge.fiap.techchallenge.usecases.endereco;

import br.com.techchallenge.fiap.techchallenge.repositories.EnderecoRepository;

public class GetEnderecoByRestauranteIdUseCase {
    private final EnderecoRepository enderecoRepository;

    private GetEnderecoByRestauranteIdUseCase(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public static GetEnderecoByRestauranteIdUseCase create(EnderecoRepository enderecoRepository) {
        return new GetEnderecoByRestauranteIdUseCase(enderecoRepository);
    }
}
