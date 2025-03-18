package br.com.techchallenge.fiap.techchallenge.usecases.tipousuario;

import br.com.techchallenge.fiap.techchallenge.repositories.TipoUsuarioRepository;

public class CreateTipoUsuarioUseCase {
    private final TipoUsuarioRepository tipoUsuarioRepository;

    private CreateTipoUsuarioUseCase(TipoUsuarioRepository tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    public static CreateTipoUsuarioUseCase create(TipoUsuarioRepository tipoUsuarioRepository) {
        return new CreateTipoUsuarioUseCase(tipoUsuarioRepository);
    }
}
