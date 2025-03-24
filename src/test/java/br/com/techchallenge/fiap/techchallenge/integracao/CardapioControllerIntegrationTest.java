package br.com.techchallenge.fiap.techchallenge.integracao;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.given;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@Sql(scripts = {"/db_clean.sql", "/db_load.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/db_clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_CLASS)
public class CardapioControllerIntegrationTest {

    @BeforeAll
    static void init() {
        RestAssured.baseURI = "http://localhost:8080";

        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    void deveCriarCardapioComSucesso() {
        String requestPayload = "{ \"id\": 1 }";

        given()
                .contentType(ContentType.JSON)
                .body(requestPayload)
                .when()
                .post("/cardapio")
                .then()
                .statusCode(201);
    }

    @Test
    void deveDeletarCardapioComSucesso() {
        long cardapioId = 3L;

        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/cardapio/{id}", cardapioId)
                .then()
                .statusCode(204);
    }

    @Test
    void deveBuscarCardapioPorIdComSucesso() {
        long id = 1L;

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/cardapio/{id}", id)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }
}
