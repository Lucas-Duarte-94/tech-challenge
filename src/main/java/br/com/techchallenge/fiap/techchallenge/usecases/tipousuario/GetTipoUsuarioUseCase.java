package br.com.techchallenge.fiap.techchallenge.usecases.tipousuario;

import br.com.techchallenge.fiap.techchallenge.entities.TipoUsuario;
import br.com.techchallenge.fiap.techchallenge.enums.TipoUsuarioEnum;
import br.com.techchallenge.fiap.techchallenge.errors.TipoUsuarioNotFoundException;
import br.com.techchallenge.fiap.techchallenge.repositories.TipoUsuarioRepository;

public class GetTipoUsuarioUseCase {
    private final TipoUsuarioRepository tipoUsuarioRepository;

    private GetTipoUsuarioUseCase(TipoUsuarioRepository tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    public static GetTipoUsuarioUseCase create(TipoUsuarioRepository tipoUsuarioRepository) {
        return new GetTipoUsuarioUseCase(tipoUsuarioRepository);
    }

    public TipoUsuario execute(TipoUsuarioEnum descricaoTipoUsuario) {
        return tipoUsuarioRepository.findByDescricaoTipoUsuario(descricaoTipoUsuario).orElseThrow(TipoUsuarioNotFoundException::new);
    }
}
