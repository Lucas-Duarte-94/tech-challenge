package br.com.techchallenge.fiap.techchallenge.usecases.tipousuario;

import br.com.techchallenge.fiap.techchallenge.repositories.TipoUsuarioRepository;

public class UpdateTipoUsuarioUseCase {
    private final TipoUsuarioRepository tipoUsuarioRepository;

    private UpdateTipoUsuarioUseCase(TipoUsuarioRepository tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    public static UpdateTipoUsuarioUseCase create(TipoUsuarioRepository tipoUsuarioRepository) {
        return new UpdateTipoUsuarioUseCase(tipoUsuarioRepository);
    }
}
