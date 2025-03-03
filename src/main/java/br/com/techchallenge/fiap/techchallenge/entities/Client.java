package br.com.techchallenge.fiap.techchallenge.entities;

import java.util.Date;

public class Client extends User {
    public Client() {
        super();
    }

    public Client(String id, String nome, String email, String senha, String login, Date ultimaAlteracao, String endereco) {
        super(id, nome, email, senha, login, ultimaAlteracao, endereco);
    }

    public Client(String nome, String email, String senha, String login, String endereco) {
        super(nome, email, senha, login, endereco);
    }
}
