package br.com.techchallenge.fiap.techchallenge.usecases.usuario;

import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;


public class DeleteUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;

    private DeleteUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public static DeleteUsuarioUseCase create(UsuarioRepository usuarioRepository) {
        return new DeleteUsuarioUseCase(usuarioRepository);
    }

    public void execute(Long id) {
        usuarioRepository.deleteById(id);
    }
}
