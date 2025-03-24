package br.com.techchallenge.fiap.techchallenge.integracao;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class TipoUsuarioControllerIntegrationTest {


    @BeforeAll
    static void init() {
        RestAssured.baseURI = "http://localhost:8080";

        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    void deveBuscarPorIdComSucesso() {
        long idTipoUsuario = 1L;

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/tipousuario/{id}", idTipoUsuario)
                .then()
                .statusCode(200)
                .body("idTipoUsuario", notNullValue())
                .contentType(ContentType.JSON);
    }
}
