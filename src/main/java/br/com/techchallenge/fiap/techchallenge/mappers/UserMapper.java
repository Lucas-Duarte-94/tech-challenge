package br.com.techchallenge.fiap.techchallenge.mappers;

import br.com.techchallenge.fiap.techchallenge.entities.User;
import br.com.techchallenge.fiap.techchallenge.entities.UserPublicData;

public class UserMapper {
    public static UserPublicData toAPI(User user) {
        return new UserPublicData(user);
    }
}
