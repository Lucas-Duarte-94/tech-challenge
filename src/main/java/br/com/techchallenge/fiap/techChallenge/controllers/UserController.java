package br.com.techchallenge.fiap.techChallenge.controllers;

import br.com.techchallenge.fiap.techChallenge.entities.User;
import br.com.techchallenge.fiap.techChallenge.mappers.UserPublicData;
import br.com.techchallenge.fiap.techChallenge.repositories.UserRepository;
import br.com.techchallenge.fiap.techChallenge.services.UserService;
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

//    @GetMapping
//    public ResponseEntity<List<User>> getUsers() {
//        List<User> users = this.userService.;
//        return ResponseEntity.ok().body(users);
//    }

    @PostMapping("/login")
    public ResponseEntity<UserPublicData> login(
            @RequestBody String login,
            @RequestBody String senha
    ) {
        UserPublicData user = this.userService.login(login, senha);

        return ResponseEntity.ok().body(user);
    }
}
