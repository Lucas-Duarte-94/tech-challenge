package br.com.techchallenge.fiap.techchallenge.controllers;

import br.com.techchallenge.fiap.techchallenge.dtos.UserChangePasswordRequestDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.UserLoginRequestDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.UserRequestDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.UserUpdateRequestDTO;
import br.com.techchallenge.fiap.techchallenge.entities.UserPublicData;
import br.com.techchallenge.fiap.techchallenge.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "Usuários", description = "Controller CRUD e auth de usuários")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            description = "Busca de usuários cadastrados paginado, retorno somente com dados públicos",
            summary = "Busca de usuários"
    )
    @GetMapping
    public ResponseEntity<List<UserPublicData>> getUsers(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        List<UserPublicData> users = this.userService.getAllUsers(size, page);
        return ResponseEntity.ok().body(users);
    }

    @Operation(
            description = "Rota de login para todos os usuários",
            summary = "Rota de login"
    )
    @PostMapping("/login")
    public ResponseEntity<UserPublicData> login(
            @RequestBody UserLoginRequestDTO loginRequest
        ) {
        UserPublicData user = this.userService.login(loginRequest.login(), loginRequest.senha());

        return ResponseEntity.ok().body(user);
    }

    @Operation(
            description = "Rota de criação de usuário, para todos os perfis",
            summary = "Criação de usuário"
    )
    @PostMapping
    public ResponseEntity<Void> createUser(
            @RequestBody UserRequestDTO user
            ) {
        this.userService.createUser(user);

        var status = HttpStatus.CREATED;

        return ResponseEntity.status(status.value()).build();
    }

    @Operation(
            description = "Rota para atualização de dados como nome, email e endereço",
            summary = "Atualização de dados"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(
            @RequestBody UserUpdateRequestDTO userDTO,
            @PathVariable String id
    ) {
        this.userService.updateUser(userDTO.nome(), userDTO.email(), userDTO.endereco(), id);

        return ResponseEntity.noContent().build();
    }

    @Operation(
            description = "Rota para alterar a senha utilizando o login",
            summary = "Mudança de senha"
    )
    @PutMapping("/password")
    public ResponseEntity<Void> changePassword(
            @RequestBody UserChangePasswordRequestDTO userChangePasswordRequestDTO
    ) {
        this.userService.changePassword(userChangePasswordRequestDTO);

        return ResponseEntity.noContent().build();
    }

    @Operation(
            description = "Rota para deletar usuário utilizando o id",
            summary = "Deletar usuário"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable String id
    ) {
        this.userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}
