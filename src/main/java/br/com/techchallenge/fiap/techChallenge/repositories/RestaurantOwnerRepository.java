package br.com.techchallenge.fiap.techChallenge.repositories;

import br.com.techchallenge.fiap.techChallenge.entities.User;

public interface RestaurantOwnerRepository {
    public void save(User user);
    public void update(String nome, String email, String endereco, Long id);
    public void delete(Long id);
    public void updatePassword(Long id, String password);
}
