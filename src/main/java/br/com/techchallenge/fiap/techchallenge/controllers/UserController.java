package br.com.techchallenge.fiap.techchallenge.controllers;

import br.com.techchallenge.fiap.techchallenge.dtos.*;
import br.com.techchallenge.fiap.techchallenge.repositories.EnderecoRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.TipoUsuarioRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;
import br.com.techchallenge.fiap.techchallenge.usecases.usuario.GetAllUsuarioUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "Usuários", description = "Controller CRUD e auth de usuários")
public class UserController {
    private final UsuarioRepository usuarioRepository;
    private final TipoUsuarioRepository tipoUsuarioRepository;
    private final EnderecoRepository enderecoRepository;

    public UserController(UsuarioRepository usuarioRepository, TipoUsuarioRepository tipoUsuarioRepository, EnderecoRepository enderecoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.tipoUsuarioRepository = tipoUsuarioRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Operation(
            description = "Busca de usuários cadastrados paginado, retorno somente com dados públicos",
            summary = "Busca de usuários"
    )
    @GetMapping
    public ResponseEntity<List<UsuarioPublicDTO>> getUsers(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        var usecase = GetAllUsuarioUseCase.create(usuarioRepository);
        List<UsuarioPublicDTO> users = usecase.execute(size, page);
        return ResponseEntity.ok().body(users);
    }

//    @Operation(
//            description = "Rota de login para todos os usuários",
//            summary = "Rota de login"
//    )
//    @PostMapping("/login")
//    public ResponseEntity<UserPublicDTO> login(
//            @RequestBody UserLoginRequestDTO loginRequest
//        ) {
//        UserPublicDTO user = this.userService.login(loginRequest.login(), loginRequest.senha());
//
//        return ResponseEntity.ok().body(user);
//    }
//
//    @Operation(
//            description = "Rota de criação de usuário, para todos os perfis",
//            summary = "Criação de usuário"
//    )
//    @PostMapping
//    public ResponseEntity<Void> createUser(
//            @RequestBody UserRequestDTO user
//            ) {
//        this.userService.createUser(user);
//
//        var status = HttpStatus.CREATED;
//
//        return ResponseEntity.status(status.value()).build();
//    }
//
//    @Operation(
//            description = "Rota para atualização de dados como nome, email e endereço",
//            summary = "Atualização de dados"
//    )
//    @PutMapping("/{id}")
//    public ResponseEntity<Void> updateUser(
//            @RequestBody UserUpdateRequestDTO userDTO,
//            @PathVariable String id
//    ) {
//        this.userService.updateUser(userDTO.nome(), userDTO.email(), userDTO.endereco(), id);
//
//        return ResponseEntity.noContent().build();
//    }
//
//    @Operation(
//            description = "Rota para alterar a senha utilizando o login",
//            summary = "Mudança de senha"
//    )
//    @PutMapping("/password")
//    public ResponseEntity<Void> changePassword(
//            @RequestBody UserChangePasswordRequestDTO userChangePasswordRequestDTO
//    ) {
//        this.userService.changePassword(userChangePasswordRequestDTO);
//
//        return ResponseEntity.noContent().build();
//    }
//
//    @Operation(
//            description = "Rota para deletar usuário utilizando o id",
//            summary = "Deletar usuário"
//    )
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(
//            @PathVariable String id
//    ) {
//        this.userService.deleteUser(id);
//
//        return ResponseEntity.noContent().build();
//    }
}
