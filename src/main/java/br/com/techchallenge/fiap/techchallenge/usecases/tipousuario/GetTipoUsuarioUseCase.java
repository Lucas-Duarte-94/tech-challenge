package br.com.techchallenge.fiap.techchallenge.usecases.tipousuario;

import br.com.techchallenge.fiap.techchallenge.repositories.TipoUsuarioRepository;

public class GetTipoUsuarioUseCase {
    private final TipoUsuarioRepository tipoUsuarioRepository;

    private GetTipoUsuarioUseCase(TipoUsuarioRepository tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    public static GetTipoUsuarioUseCase create(TipoUsuarioRepository tipoUsuarioRepository) {
        return new GetTipoUsuarioUseCase(tipoUsuarioRepository);
    }
}
