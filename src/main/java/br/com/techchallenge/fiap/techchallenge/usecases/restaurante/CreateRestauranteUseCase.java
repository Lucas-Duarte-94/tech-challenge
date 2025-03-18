package br.com.techchallenge.fiap.techchallenge.usecases.restaurante;

import br.com.techchallenge.fiap.techchallenge.dtos.CreateRestauranteDTO;
import br.com.techchallenge.fiap.techchallenge.entities.Endereco;
import br.com.techchallenge.fiap.techchallenge.entities.Restaurante;
import br.com.techchallenge.fiap.techchallenge.entities.Usuario;
import br.com.techchallenge.fiap.techchallenge.errors.UserDoesNotHavePermissionException;
import br.com.techchallenge.fiap.techchallenge.errors.UsuarioNotFoundException;
import br.com.techchallenge.fiap.techchallenge.repositories.EnderecoRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;

import static br.com.techchallenge.fiap.techchallenge.enums.TipoUsuarioEnum.DONO_RESTAURANTE;

public class CreateRestauranteUseCase {
    private final RestauranteRepository restauranteRepository;
    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;

    private CreateRestauranteUseCase(RestauranteRepository restauranteRepository, UsuarioRepository usuarioRepository, EnderecoRepository enderecoRepository) {
        this.restauranteRepository = restauranteRepository;
        this.usuarioRepository = usuarioRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public static CreateRestauranteUseCase create(RestauranteRepository restauranteRepository, UsuarioRepository usuarioRepository, EnderecoRepository enderecoRepository) {
        return new CreateRestauranteUseCase(restauranteRepository, usuarioRepository, enderecoRepository);
    }

    @Transactional
    public Restaurante execute(CreateRestauranteDTO restaurante) {
        Usuario usuario = usuarioRepository.findById(restaurante.usuarioId()).orElseThrow(UsuarioNotFoundException::new);

        if(!usuario.getTipoUsuario().getDescricaoTipoUsuario().equals(DONO_RESTAURANTE)) {
            throw new UserDoesNotHavePermissionException();
        }

        Endereco endereco = Endereco.builder()
                .descricaoLogradouro(restaurante.endereco().descricaoLogradouro())
                .descricaoBairro(restaurante.endereco().descricaoBairro())
                .descricaoCidade(restaurante.endereco().descricaoCidade())
                .descricaoComplemento(restaurante.endereco().descricaoComplemento())
                .descricaoEstado(restaurante.endereco().descricaoEstado())
                .numero(restaurante.endereco().numero())
                .numeroCEP(restaurante.endereco().numeroCep())
                .build();

        Restaurante newRestaurante = Restaurante.builder()
                .nomeRestaurante(restaurante.nomeRestaurante())
                .tipoCozinha(restaurante.tipoCozinha())
                .usuario(usuario)
                .endereco(endereco)
                .build();

        enderecoRepository.save(endereco);
        return restauranteRepository.save(newRestaurante);
    }
}
