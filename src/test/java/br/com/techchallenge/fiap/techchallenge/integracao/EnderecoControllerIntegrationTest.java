package br.com.techchallenge.fiap.techchallenge.integracao;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class EnderecoControllerIntegrationTest {

    @BeforeAll
    static void init() {
        RestAssured.baseURI = "http://localhost:8080";

        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    void deveBuscarEnderecoPorUsuarioId() {
        given()

                .contentType(ContentType.JSON)
                .when()
                .get("/endereco/usuario/1")
                .then()
                .statusCode(200)
                .body("idEndereco", notNullValue());
    }

    @Test
    void deveBuscarEnderecoPorRestauranteId() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/endereco/restaurante/1")
                .then()
                .statusCode(200)
                .body("idEndereco", notNullValue());
    }

    @Test
    void deveAtualizarEndereco() {
        String requestPayload = "{" +
                "\"descricaoLogradouro\":\"Rua Alterada\"," +
                "\"numero\":\"456\"," +
                "\"descricaoComplemento\":\"Apto 2\"," +
                "\"descricaoBairro\":\"Novo Bairro\"," +
                "\"descricaoCidade\":\"Rio de Janeiro\"," +
                "\"descricaoEstado\":\"RJ\"," +
                "\"numeroCep\":\"22000-000\"}";

        given()
                .contentType(ContentType.JSON)
                .body(requestPayload)
                .when()
                .put("/endereco/1")
                .then()
                .statusCode(204);
    }

    @Test
    void deveDeletarEndereco() {
        given()
                .when()
                .delete("/endereco/3?restauranteId=3")
                .then()
                .statusCode(204);
    }
}
