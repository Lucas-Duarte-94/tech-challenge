package br.com.techchallenge.fiap.techchallenge.usecases.endereco;

import br.com.techchallenge.fiap.techchallenge.dtos.CreateEnderecoDTO;
import br.com.techchallenge.fiap.techchallenge.entities.Endereco;
import br.com.techchallenge.fiap.techchallenge.errors.MissingIdForEnderecoException;
import br.com.techchallenge.fiap.techchallenge.repositories.EnderecoRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;

public class CreateEnderecoUseCase {
    private final EnderecoRepository enderecoRepository;
    private final UsuarioRepository usuarioRepository;
    private final RestauranteRepository restauranteRepository;

    private CreateEnderecoUseCase(EnderecoRepository enderecoRepository, UsuarioRepository usuarioRepository, RestauranteRepository restauranteRepository) {
        this.enderecoRepository = enderecoRepository;
        this.usuarioRepository = usuarioRepository;
        this.restauranteRepository = restauranteRepository;
    }

    public static CreateEnderecoUseCase create(EnderecoRepository enderecoRepository, UsuarioRepository usuarioRepository, RestauranteRepository restauranteRepository) {
        return new CreateEnderecoUseCase(enderecoRepository, usuarioRepository, restauranteRepository);
    }

    public Endereco execute(CreateEnderecoDTO createEnderecoDTO) {
        Endereco newEndereco = null;
        if(createEnderecoDTO.usuarioId() != null) {
            newEndereco = CreateUsuarioEndereco(createEnderecoDTO);
        } else if(createEnderecoDTO.restauranteId() != null) {
            newEndereco = CreateRestauranteEndereco(createEnderecoDTO);
        } else {
            throw new MissingIdForEnderecoException();
        }

        return enderecoRepository.save(newEndereco);
    }

    private Endereco CreateUsuarioEndereco(CreateEnderecoDTO createEnderecoDTO) {
        return Endereco.builder()
                .descricaoLogradouro(createEnderecoDTO.descricaoLogradouro())
                .descricaoBairro(createEnderecoDTO.descricaoBairro())
                .descricaoCidade(createEnderecoDTO.descricaoCidade())
                .descricaoComplemento(createEnderecoDTO.descricaoComplemento())
                .descricaoEstado(createEnderecoDTO.descricaoEstado())
                .numero(createEnderecoDTO.numero())
                .numeroCEP(createEnderecoDTO.numeroCep())
                .usuario(createEnderecoDTO.usuarioId())
                .build();
    }

    private Endereco CreateRestauranteEndereco(CreateEnderecoDTO createEnderecoDTO) {
        return Endereco.builder()
                .descricaoLogradouro(createEnderecoDTO.descricaoLogradouro())
                .descricaoBairro(createEnderecoDTO.descricaoBairro())
                .descricaoCidade(createEnderecoDTO.descricaoCidade())
                .descricaoComplemento(createEnderecoDTO.descricaoComplemento())
                .descricaoEstado(createEnderecoDTO.descricaoEstado())
                .numero(createEnderecoDTO.numero())
                .numeroCEP(createEnderecoDTO.numeroCep())
                .restaurante(createEnderecoDTO.restauranteId())
                .build();
    }
}
