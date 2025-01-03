package br.com.techchallenge.fiap.techChallenge.entities;

import java.util.Date;

public class UserPublicData {
    private String id;
    private String nome;
    private String email;
    private String login;
    private Date ultimaAlteracao;
    private String endereco;
    private String type;

    public UserPublicData(User user) {
        this.id = user.getId();
        this.nome = user.getNome();
        this.email = user.getEmail();
        this.login = user.getLogin();
        this.ultimaAlteracao = user.getUltimaAlteracao();
        this.endereco = user.getEndereco();
        this.type = user.getClass().getSimpleName();
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public Date getUltimaAlteracao() {
        return ultimaAlteracao;
    }

    public String getEndereco() {
        return endereco;
    }
}
