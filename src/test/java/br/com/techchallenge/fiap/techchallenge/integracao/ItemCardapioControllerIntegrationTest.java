package br.com.techchallenge.fiap.techchallenge.integracao;

import br.com.techchallenge.fiap.techchallenge.dtos.*;
import br.com.techchallenge.fiap.techchallenge.mocks.InternalMockDto;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ItemCardapioControllerIntegrationTest {

    @BeforeAll
    static void init() {
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    void deveCriarItemCardapioComSucesso() {
        CreateItemCardapioDTO itemCardapioDTO = InternalMockDto.getCreateItemCardapioDTO();

        given()
                .contentType(ContentType.JSON)
                .body(itemCardapioDTO)
                .when()
                .post("/itemcardapio")
                .then()
                .statusCode(201);
    }

    @Test
    void deveBuscarItemCardapioPorIdComSucesso() {
        long idItemCardapio = 1L;

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/itemcardapio/{id}", idItemCardapio)
                .then()
                .statusCode(200)
                .body("idItemCardapio", notNullValue())
                .body("nome", notNullValue())
                .body("descricao", notNullValue())
                .contentType(ContentType.JSON);
    }

    @Test
    void deveAtualizarItemCardapioComSucesso() {
        long idItemCardapio = 1L;
        UpdateItemCardapioDTO updateDTO = InternalMockDto.getUpdateItemCardapioDTO();

        given()
                .contentType(ContentType.JSON)
                .body(updateDTO)
                .when()
                .put("/itemcardapio/{id}", idItemCardapio)
                .then()
                .statusCode(204);
    }

    @Test
    void deveDeletarItemCardapioComSucesso() {
        long idItemCardapio = 2L;

        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/itemcardapio/{id}", idItemCardapio)
                .then()
                .statusCode(204);
    }
}
