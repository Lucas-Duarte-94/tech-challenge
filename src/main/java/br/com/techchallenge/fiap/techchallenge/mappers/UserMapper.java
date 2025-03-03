package br.com.techchallenge.fiap.techchallenge.mappers;

import br.com.techchallenge.fiap.techchallenge.dtos.UserPublicDTO;
import br.com.techchallenge.fiap.techchallenge.entities.User;

public class UserMapper {
    public static UserPublicDTO toAPI(User user) {
        return new UserPublicDTO(
                user.getId(),
                user.getNome(),
                user.getEmail(),
                user.getLogin(),
                user.getUltimaAlteracao(),
                user.getEndereco(),
                user.getClass().getSimpleName()
        );
    }
}
