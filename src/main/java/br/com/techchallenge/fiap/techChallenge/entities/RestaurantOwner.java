package br.com.techchallenge.fiap.techChallenge.entities;

import java.util.Date;

public class RestaurantOwner extends User {

    public RestaurantOwner(Long id, String nome, String email, String senha, String login, Date ultimaAlteracao, String endereco) {
        super(id, nome, email, senha, login, ultimaAlteracao, endereco);
    }
}
