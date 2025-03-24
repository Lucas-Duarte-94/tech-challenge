package br.com.techchallenge.fiap.techchallenge.integracao;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

public class FuncionamentoControllerIntegrationTest {

    @BeforeAll
    static void init() {
        RestAssured.baseURI = "http://localhost:8080";

        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    void deveCriarHorarioFuncionamento() {
        String requestBody = "{\"diaSemana\":\"MONDAY\",\"horaAbertura\":\"08:00\",\"horaFechamento\":\"18:00\",\"restauranteId\":2, \"usuarioId\":2}";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/funcionamento")
                .then()
                .statusCode(201);
    }

    @Test
    void deveBuscarHorarioFuncionamentoPorId() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/funcionamento/1")
                .then()
                .statusCode(200);
    }

    @Test
    void deveBuscarHorarioFuncionamentoPorRestauranteId() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/funcionamento/1")
                .then()
                .statusCode(200);
    }

//    @Test
//    void deveAtualizarHorarioFuncionamento() {
//        String requestBody = "{"dtaSemana":"Terça-feira","horaAbertura":"09:00","horaFecha":"19:00"}";
//
//        given()
//                .contentType(ContentType.JSON)
//                .body(requestBody)
//                .when()
//                .put("/funcionamento/1")
//                .then()
//                .statusCode(204);
//    }

    @Test
    void deveDeletarHorarioFuncionamento() {
        given()
                .when()
                .delete("/funcionamento/1")
                .then()
                .statusCode(204);
    }
}
