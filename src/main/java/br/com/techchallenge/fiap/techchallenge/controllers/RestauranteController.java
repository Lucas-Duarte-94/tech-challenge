package br.com.techchallenge.fiap.techchallenge.controllers;

import br.com.techchallenge.fiap.techchallenge.dtos.*;
import br.com.techchallenge.fiap.techchallenge.entities.Restaurante;
import br.com.techchallenge.fiap.techchallenge.entities.TipoUsuario;
import br.com.techchallenge.fiap.techchallenge.repositories.EnderecoRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.TipoUsuarioRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;
import br.com.techchallenge.fiap.techchallenge.usecases.restaurante.*;
import br.com.techchallenge.fiap.techchallenge.usecases.tipousuario.CreateTipoUsuarioUseCase;
import br.com.techchallenge.fiap.techchallenge.usecases.tipousuario.DeleteTipoUsuarioUseCase;
import br.com.techchallenge.fiap.techchallenge.usecases.tipousuario.GetTipoUsuarioUseCase;
import br.com.techchallenge.fiap.techchallenge.usecases.tipousuario.UpdateTipoUsuarioUseCase;
import br.com.techchallenge.fiap.techchallenge.usecases.usuario.GetAllUsuarioUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurante")
@Tag(name = "Restaurantes", description = "Controller CRUD de Restaurante")
public class RestauranteController {
    private final RestauranteRepository restauranteRepository;
    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;

    public RestauranteController(RestauranteRepository restauranteRepository, UsuarioRepository usuarioRepository, EnderecoRepository enderecoRepository) {
        this.restauranteRepository = restauranteRepository;
        this.usuarioRepository = usuarioRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Operation(
            description = "Busca de restaurantes cadastrados paginado",
            summary = "Busca de restaurantes"
    )
    @GetMapping
    public ResponseEntity<List<Restaurante>> getRestaurantes(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        var usecase = GetAllRestauranteUseCase.create(restauranteRepository);
        List<Restaurante> users = usecase.execute(size, page);
        return ResponseEntity.ok().body(users);
    }

    @Operation(
            description = "Cria um novo restaurante",
            summary = "Criação de restaurante"
    )
    @PostMapping
    public ResponseEntity<Void> createRestaurante(
            @RequestBody CreateRestauranteDTO restauranteDTO
    ) {
        var usecase = CreateRestauranteUseCase.create(restauranteRepository, usuarioRepository, enderecoRepository);
        usecase.execute(restauranteDTO);

        var status = HttpStatus.CREATED;

        return ResponseEntity.status(status.value()).build();
    }

    @Operation(
            description = "Busca de restaurante pelo id",
            summary = "Busca de restaurante"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> getRestaurante(
            @PathVariable Long id
    ) {
        var usecase = GetRestauranteByIdUseCase.create(restauranteRepository);
        return ResponseEntity.ok().body(usecase.execute(id));
    }

    @Operation(
            description = "Rota para atualização de restaurante",
            summary = "Atualização de dados"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRestaurante(
            @RequestBody UpdateRestauranteRequestDTO tipoUsuarioDTO,
            @PathVariable Long id
    ) {
        var usecase = UpdateRestauranteUseCase.create(restauranteRepository);
        usecase.execute(id, tipoUsuarioDTO);

        return ResponseEntity.noContent().build();
    }

    @Operation(
            description = "Rota para deletar restaurante",
            summary = "Deletar restaurante"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurante(
            @PathVariable Long id
    ) {
        var usecase = DeleteRestauranteUseCase.create(restauranteRepository);
        usecase.execute(id);

        return ResponseEntity.noContent().build();
    }
}
