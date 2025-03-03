package br.com.techchallenge.fiap.techchallenge.repositories;

import br.com.techchallenge.fiap.techchallenge.entities.User;

public interface ClientRepository {
    public void save(User user);
    public void update(String nome, String email, String endereco, String id);
    public void delete(String id);
    public void updatePassword(String id, String password);
}
