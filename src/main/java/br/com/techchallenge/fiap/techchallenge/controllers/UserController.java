package br.com.techchallenge.fiap.techchallenge.controllers;

import br.com.techchallenge.fiap.techchallenge.dtos.*;
import br.com.techchallenge.fiap.techchallenge.repositories.EnderecoRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.TipoUsuarioRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;
import br.com.techchallenge.fiap.techchallenge.usecases.usuario.*;
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
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        var usecase = GetAllUsuarioUseCase.create(usuarioRepository);
        List<UsuarioPublicDTO> users = usecase.execute(size, page);
        return ResponseEntity.ok().body(users);
    }

    @Operation(
            description = "Rota de login para todos os usuários",
            summary = "Rota de login"
    )
    @PostMapping("/login")
    public ResponseEntity<UsuarioPublicDTO> login(
            @RequestBody UserLoginRequestDTO loginRequest
        ) {
        var usecase = HandleLoginUseCase.create(usuarioRepository);
        UsuarioPublicDTO user = usecase.execute(loginRequest);

        return ResponseEntity.ok().body(user);
    }

    @Operation(
            description = "Rota de criação de usuário, para todos os perfis",
            summary = "Criação de usuário"
    )
    @PostMapping
    public ResponseEntity<Void> createUser(
            @RequestBody CreateUsuarioDTO user
            ) {
        var usecase = CreateUsuarioUseCase.create(usuarioRepository, tipoUsuarioRepository, enderecoRepository);
        usecase.execute(user);

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
            @PathVariable Long id
    ) {
        var usecase = UpdateUsuarioUseCase.create(usuarioRepository);
        usecase.execute(id, userDTO);

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
        var usecase = ChangeSenhaUsuarioUseCase.create(usuarioRepository);
        usecase.execute(userChangePasswordRequestDTO);

        return ResponseEntity.noContent().build();
    }

    @Operation(
            description = "Rota para deletar usuário utilizando o id",
            summary = "Deletar usuário"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long id
    ) {
        var usecase = DeleteUsuarioUseCase.create(usuarioRepository);
        usecase.execute(id);

        return ResponseEntity.noContent().build();
    }
}
