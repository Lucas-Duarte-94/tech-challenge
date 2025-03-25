package br.com.techchallenge.fiap.techchallenge.integracao;

import br.com.techchallenge.fiap.techchallenge.dtos.*;
import br.com.techchallenge.fiap.techchallenge.mocks.InternalMockDto;
import br.com.techchallenge.fiap.techchallenge.repositories.TipoUsuarioRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"/db_load.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/db_clean.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserControllerIntegrationTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @LocalServerPort
    private int port;

    @BeforeEach
    void init() {
        RestAssured.baseURI = "http://localhost:" + port;
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
                .body(new UserLoginRequestDTO(
                        "ana_souza",
                        "senha456"
                ))
                .when()
                .post("/user/login")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("nomeCompleto", notNullValue());
    }

    @Test
    void deveBuscarUsuariosComSucesso() {
        var users = usuarioRepository.findAll();
        System.out.println(users);

        var tipoUsuarios = tipoUsuarioRepository.findAll();
        System.out.println(tipoUsuarios);

        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("page", 0)
                .queryParam("size", 10)
                .when()
                .get("/user");

        System.out.println("Response Body: " + response.getBody().asString());

        response.then()
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
