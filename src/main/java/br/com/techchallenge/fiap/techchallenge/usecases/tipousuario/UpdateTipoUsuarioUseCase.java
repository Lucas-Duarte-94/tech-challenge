package br.com.techchallenge.fiap.techchallenge.usecases.tipousuario;

import br.com.techchallenge.fiap.techchallenge.entities.TipoUsuario;
import br.com.techchallenge.fiap.techchallenge.enums.TipoUsuarioEnum;
import br.com.techchallenge.fiap.techchallenge.errors.TipoUsuarioNotFoundException;
import br.com.techchallenge.fiap.techchallenge.repositories.TipoUsuarioRepository;

public class UpdateTipoUsuarioUseCase {
    private final TipoUsuarioRepository tipoUsuarioRepository;

    private UpdateTipoUsuarioUseCase(TipoUsuarioRepository tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    public static UpdateTipoUsuarioUseCase create(TipoUsuarioRepository tipoUsuarioRepository) {
        return new UpdateTipoUsuarioUseCase(tipoUsuarioRepository);
    }

    public TipoUsuario execute(Long id, String descTipoUsuario) {
        tipoUsuarioRepository.findById(id).orElseThrow(TipoUsuarioNotFoundException::new);

        TipoUsuario updatedTipoUsuario = TipoUsuario.builder()
                .idTipoUsuario(id).
                descricaoTipoUsuario(TipoUsuarioEnum.fromValue(descTipoUsuario))
                .build();

        return tipoUsuarioRepository.save(updatedTipoUsuario);
    }
}
