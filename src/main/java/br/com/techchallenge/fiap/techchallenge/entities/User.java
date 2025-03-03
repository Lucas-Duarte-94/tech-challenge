package br.com.techchallenge.fiap.techchallenge.entities;

import java.util.Date;

public abstract class User {
    private String id;
    private String nome;
    private String email;
    private String senha;
    private String login;
    private Date ultimaAlteracao;
    private String endereco;

    public User() {}

    public User(String id, String nome, String email, String senha, String login, Date ultimaAlteracao, String endereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.login = login;
        this.ultimaAlteracao = ultimaAlteracao;
        this.endereco = endereco;
    }

    public User(String nome, String email, String senha, String login, String endereco) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.login = login;
        this.endereco = endereco;
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

    public String getSenha() {
        return senha;
    }

    public String getLogin() {
        return login;
    }

    public Date getUltimaAlteracao() {
        return ultimaAlteracao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setUltimaAlteracao(Date ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }
}
