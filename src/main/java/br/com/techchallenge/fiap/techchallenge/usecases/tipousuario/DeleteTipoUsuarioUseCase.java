package br.com.techchallenge.fiap.techchallenge.usecases.tipousuario;

import br.com.techchallenge.fiap.techchallenge.repositories.TipoUsuarioRepository;

public class DeleteTipoUsuarioUseCase {
    private final TipoUsuarioRepository tipoUsuarioRepository;

    private DeleteTipoUsuarioUseCase(TipoUsuarioRepository tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    public static DeleteTipoUsuarioUseCase create(TipoUsuarioRepository tipoUsuarioRepository) {
        return new DeleteTipoUsuarioUseCase(tipoUsuarioRepository);
    }

    public void execute(Long idTipoUsuario) {
        tipoUsuarioRepository.deleteById(idTipoUsuario);
    }
}
