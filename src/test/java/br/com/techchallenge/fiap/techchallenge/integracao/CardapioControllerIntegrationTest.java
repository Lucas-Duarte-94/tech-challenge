package br.com.techchallenge.fiap.techchallenge.integracao;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

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
