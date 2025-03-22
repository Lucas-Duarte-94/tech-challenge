package br.com.techchallenge.fiap.techchallenge.usecases.endereco;

import br.com.techchallenge.fiap.techchallenge.errors.MissingIdForEnderecoException;
import br.com.techchallenge.fiap.techchallenge.repositories.EnderecoRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;

import java.util.Optional;

public class DeleteEnderecoUseCase {
    private final EnderecoRepository enderecoRepository;
    private final UsuarioRepository usuarioRepository;
    private final RestauranteRepository restauranteRepository;

    private DeleteEnderecoUseCase(EnderecoRepository enderecoRepository, UsuarioRepository usuarioRepository, RestauranteRepository restauranteRepository) {
        this.enderecoRepository = enderecoRepository;
        this.usuarioRepository = usuarioRepository;
        this.restauranteRepository = restauranteRepository;
    }

    public static DeleteEnderecoUseCase create(EnderecoRepository enderecoRepository, UsuarioRepository usuarioRepository, RestauranteRepository restauranteRepository) {
        return new DeleteEnderecoUseCase(enderecoRepository, usuarioRepository, restauranteRepository);
    }

    @Transactional
    public void execute(Long enderecoId, Optional<Long> restauranteId, Optional<Long> usuarioId) {
        if(restauranteId.isPresent()) {
            restauranteRepository.updateEnderecoToNull(restauranteId.get());
        } else if(usuarioId.isPresent()) {
            usuarioRepository.updateEnderecoToNull(usuarioId.get());
        } else {
            throw new MissingIdForEnderecoException();
        }

        enderecoRepository.deleteById(enderecoId);
    }
}
