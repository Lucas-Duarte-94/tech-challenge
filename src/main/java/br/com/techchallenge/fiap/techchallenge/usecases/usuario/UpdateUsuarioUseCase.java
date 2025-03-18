package br.com.techchallenge.fiap.techchallenge.usecases.usuario;

import br.com.techchallenge.fiap.techchallenge.dtos.UsuarioPublicDTO;
import br.com.techchallenge.fiap.techchallenge.entities.Usuario;
import br.com.techchallenge.fiap.techchallenge.errors.UserNotFoundException;
import br.com.techchallenge.fiap.techchallenge.mappers.UsuarioMapper;
import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;

public class UpdateUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;

    private UpdateUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public static UpdateUsuarioUseCase create(UsuarioRepository usuarioRepository) {
        return new UpdateUsuarioUseCase(usuarioRepository);
    }

    public UsuarioPublicDTO execute(Usuario usuario) {
        usuarioRepository.findById(usuario.getIdUsuario()).orElseThrow(UserNotFoundException::new);

        var updatedUser = usuarioRepository.save(usuario);

        return UsuarioMapper.toAPI(updatedUser);
    }
}
