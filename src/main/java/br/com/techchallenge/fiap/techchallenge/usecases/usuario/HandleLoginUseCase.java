package br.com.techchallenge.fiap.techchallenge.usecases.usuario;

import br.com.techchallenge.fiap.techchallenge.dtos.UserLoginRequestDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.UsuarioPublicDTO;
import br.com.techchallenge.fiap.techchallenge.errors.UserNotFoundException;
import br.com.techchallenge.fiap.techchallenge.mappers.UsuarioMapper;
import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;

public class HandleLoginUseCase {
    private final UsuarioRepository usuarioRepository;

    private HandleLoginUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public static HandleLoginUseCase create(UsuarioRepository usuarioRepository) {
        return new HandleLoginUseCase(usuarioRepository);
    }

    public UsuarioPublicDTO execute(UserLoginRequestDTO request) {
        var user = usuarioRepository.findByLogin(request.login()).orElseThrow(UserNotFoundException::new);

        return UsuarioMapper.toAPI(user);
    }
}
