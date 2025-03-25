package br.com.techchallenge.fiap.techchallenge.integracao;

import br.com.techchallenge.fiap.techchallenge.dtos.*;
import br.com.techchallenge.fiap.techchallenge.mocks.InternalMockDto;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"/db_load.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/db_clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class RestauranteControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @BeforeEach
    void init() {
        RestAssured.baseURI = "http://localhost:" + port;
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
