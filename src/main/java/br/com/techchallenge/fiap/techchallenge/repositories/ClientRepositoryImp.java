package br.com.techchallenge.fiap.techchallenge.repositories;

import br.com.techchallenge.fiap.techchallenge.entities.User;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

@Repository
public class ClientRepositoryImp implements ClientRepository {
    private final JdbcClient jdbcClient;

    public ClientRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public void save(User user) {
        String uuid = UUID.randomUUID().toString();

        this.jdbcClient.sql(
                "INSERT INTO client (id, nome, email, login, senha, endereco, ultima_alteracao) " +
                "VALUES (:uuid, :nome, :email, :login, :senha, :endereco, :ultima_alteracao)"
                    )
                .param("uuid", uuid)
                .param("nome", user.getNome())
                .param("email", user.getEmail())
                .param("login", user.getLogin())
                .param("senha", user.getSenha())
                .param("endereco", user.getEndereco())
                .param("ultima_alteracao", user.getUltimaAlteracao())
                .update();
    }

    @Override
    public void update(String nome, String email, String endereco, String id) {
        this.jdbcClient.sql(
                        "UPDATE client SET nome = :nome, email = :email, endereco = :endereco, ultima_alteracao = :date WHERE id = :id"
                ).param("nome", nome)
                .param("email", email)
                .param("endereco", endereco)
                .param("date", new Date())
                .param("id", id)
                .update();
    }

    @Override
    public void delete(String id) {
        this.jdbcClient.sql(
                "DELETE FROM client WHERE id = :id"
        ).param("id", id).update();
    }

    @Override
    public void updatePassword(String id, String senha) {
        this.jdbcClient.sql(
                        "UPDATE client SET senha = :senha WHERE id = :id"
                )
                .param("senha", senha)
                .param("id", id)
                .update();
    }
}
