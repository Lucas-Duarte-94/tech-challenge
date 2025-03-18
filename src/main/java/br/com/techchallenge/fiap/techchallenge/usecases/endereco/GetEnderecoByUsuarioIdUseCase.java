package br.com.techchallenge.fiap.techchallenge.usecases.endereco;

import br.com.techchallenge.fiap.techchallenge.repositories.EnderecoRepository;

public class GetEnderecoByUsuarioIdUseCase {
    private final EnderecoRepository enderecoRepository;

    private GetEnderecoByUsuarioIdUseCase(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public static GetEnderecoByUsuarioIdUseCase create(EnderecoRepository enderecoRepository) {
        return new GetEnderecoByUsuarioIdUseCase(enderecoRepository);
    }
}
