package br.com.techchallenge.fiap.techChallenge.entities;

import java.util.Date;

public class Client extends User {
    public Client(Long id, String nome, String email, String senha, String login, Date ultimaAlteracao, String endereco) {
        super(id, nome, email, senha, login, ultimaAlteracao, endereco);
    }
}
