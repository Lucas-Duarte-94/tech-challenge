package br.com.techchallenge.fiap.techchallenge.usecases.endereco;

import br.com.techchallenge.fiap.techchallenge.entities.Endereco;
import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;

public class GetEnderecoByUsuarioIdUseCase {
    private final UsuarioRepository usuarioRepository;

    private GetEnderecoByUsuarioIdUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public static GetEnderecoByUsuarioIdUseCase create(UsuarioRepository usuarioRepository) {
        return new GetEnderecoByUsuarioIdUseCase(usuarioRepository);
    }

    public Endereco execute(Long usuarioId) {
        return usuarioRepository.findEnderecoByUsuarioId(usuarioId);
    }
}
