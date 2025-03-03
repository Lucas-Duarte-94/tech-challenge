package br.com.techchallenge.fiap.techchallenge.repositories;

import br.com.techchallenge.fiap.techchallenge.entities.Client;
import br.com.techchallenge.fiap.techchallenge.entities.RestaurantOwner;
import br.com.techchallenge.fiap.techchallenge.entities.User;
import br.com.techchallenge.fiap.techchallenge.errors.UnkownType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public class UserRepositoryImp implements UserRepository {
    private final JdbcClient jdbcClient;

    private static final Logger logger = LoggerFactory.getLogger(User.class);

    public UserRepositoryImp(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<User> getAll(int size, int offset) {
        return this.jdbcClient.sql(
                "SELECT * " +
                "FROM (" +
                    "SELECT c.*, 'client' AS type " +
                    "FROM client AS c " +
                    "UNION ALL " +
                    "SELECT ro.* , 'restaurant_owner' AS type " +
                    "FROM restaurant_owner AS ro " +
                ") AS combined " +
                "LIMIT :size OFFSET :offset"
        )
        .param("size", size)
        .param("offset", offset)
        .query((rs, rowNum) -> {
            String type = rs.getString("type");
            String userId = rs.getString("id");
            String nome = rs.getString("nome");
            String email = rs.getString("email");
            String senha = rs.getString("senha");
            String login = rs.getString("login");
            Date ultimaAlteracao = rs.getDate("ultima_alteracao");
            String endereco = rs.getString("endereco");

            if ("client".equalsIgnoreCase(type)) {
                return new Client(userId, nome, email, senha, login, ultimaAlteracao, endereco);
            } else if ("restaurant_owner".equalsIgnoreCase(type)) {
                return new RestaurantOwner(userId, nome, email, senha, login, ultimaAlteracao, endereco);
            } else {
                throw new UnkownType();
            }}).list();
    }

    @Override
    public Optional<User> getById(String id) {
        return this.jdbcClient.sql(
                "SELECT c.id, c.nome, c.email, c.senha, c.login, c.ultima_alteracao, c.endereco, 'client' AS type  FROM client c " +
                "WHERE c.id = :id " +
                "UNION " +
                "SELECT ro.id, ro.nome, ro.email, ro.senha, ro.login, ro.ultima_alteracao, ro.endereco, 'restaurant_owner' AS type FROM restaurant_owner ro " +
                "WHERE ro.id = :id"
                )
                .param("id", id)
                .query((rs, rowNum) -> {
                    String type = rs.getString("type");
                    String userId = rs.getString("id");
                    String nome = rs.getString("nome");
                    String email = rs.getString("email");
                    String senha = rs.getString("senha");
                    String login = rs.getString("login");
                    Date ultimaAlteracao = rs.getDate("ultima_alteracao");
                    String endereco = rs.getString("endereco");

                    if ("client".equalsIgnoreCase(type)) {
                        return new Client(userId, nome, email, senha, login, ultimaAlteracao, endereco);
                    } else if ("restaurant_owner".equalsIgnoreCase(type)) {
                        return new RestaurantOwner(userId, nome, email, senha, login, ultimaAlteracao, endereco);
                    } else {
                        logger.warn("Unknown type: {}", type);
                        throw new UnkownType();
                    }
                })
                .optional();
    }

    @Override
    public Optional<User> getByLogin(String login) {
        return this.jdbcClient.sql(
                        "SELECT c.*, 'client' AS type  FROM client c " +
                        "WHERE c.login = :login " +
                        "UNION " +
                        "SELECT ro.*, 'restaurant_owner' AS type FROM restaurant_owner ro " +
                        "WHERE ro.login = :login "
                )
                .param("login", login)
                .query((rs, rowNum) -> {
                    String type = rs.getString("type");
                    String userId = rs.getString("id");
                    String nome = rs.getString("nome");
                    String email = rs.getString("email");
                    String senha = rs.getString("senha");
                    String userLogin = rs.getString("login");
                    Date ultimaAlteracao = rs.getDate("ultima_alteracao");
                    String endereco = rs.getString("endereco");

                    if ("client".equalsIgnoreCase(type)) {
                        return new Client(userId, nome, email, senha, userLogin, ultimaAlteracao, endereco);
                    } else if ("restaurant_owner".equalsIgnoreCase(type)) {
                        return new RestaurantOwner(userId, nome, email, senha, userLogin, ultimaAlteracao, endereco);
                    } else {
                        throw new UnkownType();
                    }
                })
                .optional();
    }
}
