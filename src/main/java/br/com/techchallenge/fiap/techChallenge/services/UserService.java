package br.com.techchallenge.fiap.techChallenge.services;

import br.com.techchallenge.fiap.techChallenge.dtos.UserChangePasswordRequestDTO;
import br.com.techchallenge.fiap.techChallenge.dtos.UserRequestDTO;
import br.com.techchallenge.fiap.techChallenge.entities.Client;
import br.com.techchallenge.fiap.techChallenge.entities.RestaurantOwner;
import br.com.techchallenge.fiap.techChallenge.entities.User;
import br.com.techchallenge.fiap.techChallenge.errors.PasswordDoesNotMatchException;
import br.com.techchallenge.fiap.techChallenge.errors.UnkownType;
import br.com.techchallenge.fiap.techChallenge.errors.UserNotFoundException;
import br.com.techchallenge.fiap.techChallenge.mappers.UserMapper;
import br.com.techchallenge.fiap.techChallenge.entities.UserPublicData;
import br.com.techchallenge.fiap.techChallenge.repositories.ClientRepository;
import br.com.techchallenge.fiap.techChallenge.repositories.RestaurantOwnerRepository;
import br.com.techchallenge.fiap.techChallenge.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RestaurantOwnerRepository restaurantOwnerRepository;

    private static final Logger logger = LoggerFactory.getLogger(User.class);

    public UserPublicData login(String login, String senha) {
        User user = this.userRepository.getByLogin(login).orElseThrow(UserNotFoundException::new);


        boolean matched = passwordMatch(senha, user);

        if(!matched) {
            throw new PasswordDoesNotMatchException();
        }

        return UserMapper.toAPI(user);
    }

    public List<UserPublicData> getAllUsers() {
        var users = this.userRepository.getAll();

        return users.stream().map(UserMapper::toAPI).collect(Collectors.toList());
    }

    public void createUser(UserRequestDTO user) {
        logger.info("Creating new user: " + user.type());
        logger.info("Creating new user: " + user);

        if(user.type().equalsIgnoreCase("client")) {
            this.clientRepository.save(new Client(user.nome(), user.email(), user.senha(), user.login(), user.endereco()));
        } else if (user.type().equalsIgnoreCase("restaurante_owner")) {
            this.restaurantOwnerRepository.save(new RestaurantOwner(user.nome(), user.email(), user.senha(), user.login(), user.endereco()));
        } else {
            throw new UnkownType();
        }
    }

    public void updateUser(String nome, String email, String endereco, String id) {
        var user = this.userRepository.getById(id).orElseThrow(() -> new UserNotFoundException(id));

        logger.info(String.valueOf(user));

        if(user instanceof Client) {
            this.clientRepository.update(nome, email, endereco, id);
        } else if (user instanceof RestaurantOwner) {
            this.restaurantOwnerRepository.update(nome, email, endereco, id);
        }
    }

    public void deleteUser(String id) {
        User user = this.userRepository.getById(id).orElseThrow(UserNotFoundException::new);

        if(user instanceof Client) {
            this.clientRepository.delete(id);
        } else if (user instanceof RestaurantOwner) {
            this.restaurantOwnerRepository.delete(id);
        } else {
            throw new UnkownType();
        }
    }

    public void changePassword(UserChangePasswordRequestDTO changePasswordRequest) {
        User user = this.userRepository
                .getByLogin(changePasswordRequest.login())
                .orElseThrow(UserNotFoundException::new);

        if(!this.passwordMatch(user.getSenha(), user)) {
            throw new PasswordDoesNotMatchException();
        }

        if (user instanceof RestaurantOwner) {
            this.restaurantOwnerRepository.updatePassword(
                    user.getId(),
                    changePasswordRequest.novaSenha()
            );
        } else if (user instanceof Client) {
            this.clientRepository.updatePassword(
                    user.getId(),
                    changePasswordRequest.novaSenha()
            );
        } else {
            throw new UnkownType();
        }
    }

    private boolean passwordMatch(String password, User user) {
        // Mudar implementação provavelmente para o BCrypt para verificação de senha hash com salt
        String userPassword = user.getSenha();
        return Objects.equals(password, userPassword);
    }
}
