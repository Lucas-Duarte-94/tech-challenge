package br.com.techchallenge.fiap.techchallenge.usecases.usuario;

import br.com.techchallenge.fiap.techchallenge.entities.Usuario;
import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;

public class CreateUsuarioUseCase {
    private UsuarioRepository usuarioRepository;

    private CreateUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public static CreateUsuarioUseCase create(UsuarioRepository usuarioRepository) {
        return new CreateUsuarioUseCase(usuarioRepository);
    }

    public Usuario execute(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
