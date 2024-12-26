package br.com.techchallenge.fiap.techChallenge.repositories;

import br.com.techchallenge.fiap.techChallenge.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> getAll();
    Optional<User> getById(Long id);
    Optional<User> getByLogin(String login);
    Integer save(User user);
    Integer update(User user);
    Integer delete(User user);
}
