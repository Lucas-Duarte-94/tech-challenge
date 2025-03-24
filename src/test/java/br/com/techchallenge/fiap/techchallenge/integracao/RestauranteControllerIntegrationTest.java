package br.com.techchallenge.fiap.techchallenge.integracao;

import br.com.techchallenge.fiap.techchallenge.dtos.*;
import br.com.techchallenge.fiap.techchallenge.mocks.InternalMockDto;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RestauranteControllerIntegrationTest {

    @BeforeAll
    static void init() {
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    void deveCriarRestauranteComSucesso() {
        CreateRestauranteDTO restauranteDTO = InternalMockDto.getCreateRestauranteDTO();

        given()
                .contentType(ContentType.JSON)
                .body(restauranteDTO)
                .when()
                .post("/restaurante")
                .then()
                .statusCode(201);
    }

    @Test
    void deveBuscarRestaurantePorIdComSucesso() {
        long idRestaurante = 1L;

        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/restaurante/{id}", idRestaurante)
                .then()
                .statusCode(200)
                .body("idRestaurante", notNullValue())
                .body("nomeRestaurante", notNullValue())
                .contentType(ContentType.JSON);
    }

    @Test
    void deveAtualizarRestauranteComSucesso() {
        long idRestaurante = 1L; // Supondo que o ID 1 exista no banco
        UpdateRestauranteRequestDTO updateDTO = InternalMockDto.getUpdateRestauranteRequestDTO();

        given()
                .contentType(ContentType.JSON)
                .body(updateDTO)
                .when()
                .put("/restaurante/{id}", idRestaurante)
                .then()
                .statusCode(204);
    }

    @Test
    void deveDeletarRestauranteComSucesso() {
        long idRestaurante = 2L;
        DeleteRestauranteDTO deleteDTO = InternalMockDto.getDeleteRestauranteDto();

        given()
                .contentType(ContentType.JSON)
                .body(deleteDTO)
                .when()
                .delete("/restaurante/{id}", idRestaurante)
                .then()
                .statusCode(204);
    }

    @Test
    void deveBuscarRestaurantesComPaginacao() {
        given()
                .contentType(ContentType.JSON)
                .param("page", 0)
                .param("size", 10)
                .when()
                .get("/restaurante")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }
}
