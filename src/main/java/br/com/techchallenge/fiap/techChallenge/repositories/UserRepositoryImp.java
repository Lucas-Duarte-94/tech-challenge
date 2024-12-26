package br.com.techchallenge.fiap.techChallenge.repositories;

import br.com.techchallenge.fiap.techChallenge.entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImp implements UserRepository {
    @Override
    public List<User> getAll() {
        return List.of();
    }

    @Override
    public Optional<User> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getByLogin(String login) {
        return Optional.empty();
    }

    @Override
    public Integer save(User user) {
        return 0;
    }

    @Override
    public Integer update(User user) {
        return 0;
    }

    @Override
    public Integer delete(User user) {
        return 0;
    }
}
