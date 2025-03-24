package br.com.techchallenge.fiap.techchallenge.usecases.endereco;

import br.com.techchallenge.fiap.techchallenge.dtos.UpdateEnderecoDTO;
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

    public Endereco execute(Long id, UpdateEnderecoDTO request) {
        var endereco = enderecoRepository.findById(id).orElseThrow(MissingIdForEnderecoException::new);

        endereco.setDescricaoBairro(request.descricaoBairro());
        endereco.setDescricaoLogradouro(request.descricaoLogradouro());
        endereco.setNumero(request.numero());
        endereco.setDescricaoComplemento(request.descricaoComplemento());
        endereco.setDescricaoCidade(request.descricaoCidade());
        endereco.setDescricaoEstado(request.descricaoEstado());
        endereco.setNumeroCEP(request.numeroCep());


        return enderecoRepository.save(endereco);
    }
}
