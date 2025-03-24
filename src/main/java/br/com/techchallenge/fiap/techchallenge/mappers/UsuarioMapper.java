package br.com.techchallenge.fiap.techchallenge.mappers;

import br.com.techchallenge.fiap.techchallenge.dtos.UsuarioPublicDTO;
import br.com.techchallenge.fiap.techchallenge.entities.Usuario;

public class UsuarioMapper {
    public static UsuarioPublicDTO toAPI(Usuario user) {
        return new UsuarioPublicDTO(
                user.getIdUsuario(),
                user.getNomeCompleto(),
                user.getEmail(),
                user.getLogin(),
                user.getDataAtualizacao(),
                user.getEndereco(),
                user.getTipoUsuario()
        );
    }
}
