package br.com.techchallenge.fiap.techchallenge.integracao;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class CardapioControllerIntegrationTest {

//    @BeforeAll
//    static void init() {
//        RestAssured.baseURI = "http://localhost:8080";
//
//        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
//    }
//
//    @Test
//    void deveCriarCardapioComSucesso() {
//        String requestPayload = "{ \"id\": 1 }";
//
//        given()
//                .contentType(ContentType.JSON)
//                .body(requestPayload)
//                .when()
//                .post("/cardapio")
//                .then()
//                .statusCode(201)
//                .header("Location", containsString("/cardapio"))
//                .contentType(ContentType.JSON);
//    }
//
//    @Test
//    void deveDeletarCardapioComSucesso() {
//        long cardapioId = 1L;
//
//        given()
//                .contentType(ContentType.JSON)
//                .when()
//                .delete("/cardapio/{id}", cardapioId)
//                .then()
//                .statusCode(204);
//    }
//
//    @Test
//    void deveBuscarCardapioPorRestauranteIdComSucesso() {
//        long restauranteId = 1L;
//
//        given()
//                .contentType(ContentType.JSON)
//                .when()
//                .get("/cardapio/{id}", restauranteId)
//                .then()
//                .statusCode(200)
//                .body("restauranteId", equalTo((int) restauranteId))
//                .body("cardapioId", notNullValue())
//                .contentType(ContentType.JSON);
//    }
}
