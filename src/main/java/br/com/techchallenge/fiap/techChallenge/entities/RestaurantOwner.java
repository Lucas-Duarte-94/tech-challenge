package br.com.techchallenge.fiap.techChallenge.entities;

import java.util.Date;

public class RestaurantOwner extends User {
    public RestaurantOwner() {
        super();
    }

    public RestaurantOwner(String id, String nome, String email, String senha, String login, Date ultimaAlteracao, String endereco) {
        super(id, nome, email, senha, login, ultimaAlteracao, endereco);
    }

    public RestaurantOwner(String nome, String email, String senha, String login, String endereco) {
        super(nome, email, senha, login, endereco);
    }
}
