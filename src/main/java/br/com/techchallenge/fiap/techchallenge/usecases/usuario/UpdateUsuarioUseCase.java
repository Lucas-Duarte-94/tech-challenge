package br.com.techchallenge.fiap.techchallenge.usecases.usuario;

import br.com.techchallenge.fiap.techchallenge.dtos.UserUpdateRequestDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.UsuarioPublicDTO;
import br.com.techchallenge.fiap.techchallenge.errors.UserNotFoundException;
import br.com.techchallenge.fiap.techchallenge.mappers.UsuarioMapper;
import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;

import java.time.OffsetDateTime;

public class UpdateUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;

    private UpdateUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public static UpdateUsuarioUseCase create(UsuarioRepository usuarioRepository) {
        return new UpdateUsuarioUseCase(usuarioRepository);
    }

    public UsuarioPublicDTO execute(Long id, UserUpdateRequestDTO userDTO) {
        var usuario = usuarioRepository.findById(id).orElseThrow(UserNotFoundException::new);

        usuario.setEmail(userDTO.email());
        usuario.setNomeCompleto(userDTO.nomeCompleto());
        usuario.setDataAtualizacao(OffsetDateTime.now());

        var updatedUser = usuarioRepository.save(usuario);

        return UsuarioMapper.toAPI(updatedUser);
    }
}
