package br.com.techchallenge.fiap.techchallenge.repositories;

import br.com.techchallenge.fiap.techchallenge.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> getAll(int size, int offset);
    Optional<User> getById(String id);
    Optional<User> getByLogin(String login);
}
