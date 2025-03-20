package br.com.techchallenge.fiap.techchallenge.usecases.endereco;

import br.com.techchallenge.fiap.techchallenge.dtos.CreateEnderecoDTO;
import br.com.techchallenge.fiap.techchallenge.entities.Endereco;
import br.com.techchallenge.fiap.techchallenge.errors.MissingIdForEnderecoException;
import br.com.techchallenge.fiap.techchallenge.repositories.EnderecoRepository;

public class UpdateEnderecoUseCase {
    private final EnderecoRepository enderecoRepository;

    private UpdateEnderecoUseCase(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public static UpdateEnderecoUseCase create(EnderecoRepository enderecoRepository) {
        return new UpdateEnderecoUseCase(enderecoRepository);
    }

    public Endereco execute(Endereco endereco) {
        enderecoRepository.findById(endereco.getIdEndereco()).orElseThrow(MissingIdForEnderecoException::new);

        return enderecoRepository.save(endereco);
    }
}
