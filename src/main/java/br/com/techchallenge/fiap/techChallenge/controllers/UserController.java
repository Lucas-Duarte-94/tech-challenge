package br.com.techchallenge.fiap.techChallenge.controllers;

import br.com.techchallenge.fiap.techChallenge.dtos.UserChangePasswordRequestDTO;
import br.com.techchallenge.fiap.techChallenge.dtos.UserLoginRequestDTO;
import br.com.techchallenge.fiap.techChallenge.dtos.UserRequestDTO;
import br.com.techchallenge.fiap.techChallenge.dtos.UserUpdateRequestDTO;
import br.com.techchallenge.fiap.techChallenge.entities.UserPublicData;
import br.com.techchallenge.fiap.techChallenge.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            description = "Busca de usuários cadastrados paginado, retorno somente com dados públicos"
    )
    @GetMapping
    public ResponseEntity<List<UserPublicData>> getUsers(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        List<UserPublicData> users = this.userService.getAllUsers(size, page);
        return ResponseEntity.ok().body(users);
    }

    @PostMapping("/login")
    public ResponseEntity<UserPublicData> login(
            @RequestBody UserLoginRequestDTO loginRequest
        ) {
        UserPublicData user = this.userService.login(loginRequest.login(), loginRequest.senha());

        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<Void> createUser(
            @RequestBody UserRequestDTO user
            ) {
        this.userService.createUser(user);

        var status = HttpStatus.CREATED;

        return ResponseEntity.status(status.value()).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(
            @RequestBody UserUpdateRequestDTO userDTO,
            @PathVariable String id
    ) {
        this.userService.updateUser(userDTO.nome(), userDTO.email(), userDTO.endereco(), id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/password")
    public ResponseEntity<Void> changePassword(
            @RequestBody UserChangePasswordRequestDTO userChangePasswordRequestDTO
    ) {
        this.userService.changePassword(userChangePasswordRequestDTO);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable String id
    ) {
        this.userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}
