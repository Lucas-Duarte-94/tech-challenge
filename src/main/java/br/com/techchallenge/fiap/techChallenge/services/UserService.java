package br.com.techchallenge.fiap.techChallenge.services;

import br.com.techchallenge.fiap.techChallenge.entities.User;
import br.com.techchallenge.fiap.techChallenge.errors.PasswordDoesNotMatchException;
import br.com.techchallenge.fiap.techChallenge.errors.UserNotFoundException;
import br.com.techchallenge.fiap.techChallenge.mappers.UserMapper;
import br.com.techchallenge.fiap.techChallenge.mappers.UserPublicData;
import br.com.techchallenge.fiap.techChallenge.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(User.class);

    public UserPublicData login(String login, String senha) {
        User user = this.userRepository.getByLogin(login).orElseThrow(() -> new UserNotFoundException());


        boolean matched = passwordMatch(senha, user);

        if(!matched) {
            throw new PasswordDoesNotMatchException();
        }

        return UserMapper.toAPI(user);
    }

    private boolean passwordMatch(String password, User user) {
        // Mudar implementação provavelmente para o BCrypt para verificação de senha hash com salt
        if(password == user.getSenha()) {
            return true;
        }
        return false;
    }
}
