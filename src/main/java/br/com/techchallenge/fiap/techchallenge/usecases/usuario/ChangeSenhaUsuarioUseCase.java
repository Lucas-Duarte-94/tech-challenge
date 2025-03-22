package br.com.techchallenge.fiap.techchallenge.usecases.usuario;

import br.com.techchallenge.fiap.techchallenge.dtos.UserChangePasswordRequestDTO;
import br.com.techchallenge.fiap.techchallenge.errors.PasswordDoesNotMatchException;
import br.com.techchallenge.fiap.techchallenge.errors.UserNotFoundException;
import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;

import java.util.Objects;

public class ChangeSenhaUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;

    private ChangeSenhaUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public static ChangeSenhaUsuarioUseCase create(UsuarioRepository usuarioRepository) {
        return new ChangeSenhaUsuarioUseCase(usuarioRepository);
    }

    public void execute(UserChangePasswordRequestDTO dto) {
        var user = usuarioRepository.findByLogin(dto.login()).orElseThrow(UserNotFoundException::new);

        if(!Objects.equals(user.getSenha(), dto.senha())) {
            throw new PasswordDoesNotMatchException();
        }

        user.setSenha(dto.novaSenha());

        usuarioRepository.save(user);
    }
}
