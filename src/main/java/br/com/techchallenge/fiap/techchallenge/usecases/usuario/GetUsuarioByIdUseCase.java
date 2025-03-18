package br.com.techchallenge.fiap.techchallenge.usecases.usuario;

import br.com.techchallenge.fiap.techchallenge.dtos.UsuarioPublicDTO;
import br.com.techchallenge.fiap.techchallenge.errors.UserNotFoundException;
import br.com.techchallenge.fiap.techchallenge.mappers.UsuarioMapper;
import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;


public class GetUsuarioByIdUseCase {
    private final UsuarioRepository usuarioRepository;

    private GetUsuarioByIdUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public static GetUsuarioByIdUseCase create(UsuarioRepository usuarioRepository) {
        return new GetUsuarioByIdUseCase(usuarioRepository);
    }

    public UsuarioPublicDTO execute(Long id) {
        var user = usuarioRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return UsuarioMapper.toAPI(user);
    }
}
