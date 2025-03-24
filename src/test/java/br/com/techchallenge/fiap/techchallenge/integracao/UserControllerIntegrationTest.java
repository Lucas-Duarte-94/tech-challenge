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
public class UserControllerIntegrationTest {

    @BeforeAll
    static void init() {
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    void deveCriarUsuarioComSucesso() {
        CreateUsuarioDTO usuarioDTO = InternalMockDto.getCreateUsuarioDTO();

        given()
                .contentType(ContentType.JSON)
                .body(usuarioDTO)
                .when()
                .post("/user")
                .then()
                .statusCode(201);
    }

    @Test
    void deveFazerLoginComSucesso() {
        UserLoginRequestDTO loginDTO = InternalMockDto.getUserLoginRequestDTO();

        given()
                .contentType(ContentType.JSON)
                .body(loginDTO)
                .when()
                .post("/user/login")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("nomeCompleto", notNullValue());
    }

    @Test
    void deveBuscarUsuariosComSucesso() {
        given()
                .contentType(ContentType.JSON)
                .queryParam("page", 0)
                .queryParam("size", 10)
                .when()
                .get("/user")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    void deveAlterarSenhaComSucesso() {
        UserChangePasswordRequestDTO passwordDTO = InternalMockDto.getUserChangePasswordRequestDTO();

        given()
                .contentType(ContentType.JSON)
                .body(passwordDTO)
                .when()
                .put("/user/password")
                .then()
                .statusCode(204);
    }

    @Test
    void deveDeletarUsuarioComSucesso() {
        long idUsuario = 4L;

        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/user/{id}", idUsuario)
                .then()
                .statusCode(204);
    }
}
