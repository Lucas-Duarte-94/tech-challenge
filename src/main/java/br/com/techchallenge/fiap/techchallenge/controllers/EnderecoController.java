package br.com.techchallenge.fiap.techchallenge.controllers;

import br.com.techchallenge.fiap.techchallenge.dtos.UpdateEnderecoDTO;
import br.com.techchallenge.fiap.techchallenge.entities.Endereco;
import br.com.techchallenge.fiap.techchallenge.repositories.EnderecoRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;
import br.com.techchallenge.fiap.techchallenge.usecases.endereco.DeleteEnderecoUseCase;
import br.com.techchallenge.fiap.techchallenge.usecases.endereco.GetEnderecoByRestauranteIdUseCase;
import br.com.techchallenge.fiap.techchallenge.usecases.endereco.GetEnderecoByUsuarioIdUseCase;
import br.com.techchallenge.fiap.techchallenge.usecases.endereco.UpdateEnderecoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/endereco")
@Tag(name = "Endereço", description = "Controller CRUD de endereço")
public class EnderecoController {
    private final EnderecoRepository enderecoRepository;
    private final UsuarioRepository usuarioRepository;
    private final RestauranteRepository restauranteRepository;

    public EnderecoController(EnderecoRepository enderecoRepository, UsuarioRepository usuarioRepository, RestauranteRepository restauranteRepository) {
        this.enderecoRepository = enderecoRepository;
        this.usuarioRepository = usuarioRepository;
        this.restauranteRepository = restauranteRepository;
    }

    @Operation(
            description = "Rota para deletar endereço",
            summary = "Deletar endereço"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndereco(
            @PathVariable("id") Long id
    ) {
        var usecase = DeleteEnderecoUseCase.create(enderecoRepository);
        usecase.execute(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(
            description = "Busca de endereço pelo id do usuário",
            summary = "Busca de endereço"
    )
    @GetMapping("/usuario/{id}")
    public ResponseEntity<Endereco> getEnderecoByUsuarioId(
            @PathVariable("id") Long usuarioId
    ) {
        var usecase = GetEnderecoByUsuarioIdUseCase.create(usuarioRepository);
        return ResponseEntity.ok().body(usecase.execute(usuarioId));
    }

    @Operation(
            description = "Busca de endereço pelo id do restaurante",
            summary = "Busca de endereço"
    )
    @GetMapping("/restaurante/{id}")
    public ResponseEntity<Endereco> getEnderecoByRestauranteId(
            @PathVariable("id") Long id
    ) {
        var usecase = GetEnderecoByRestauranteIdUseCase.create(restauranteRepository);
        return ResponseEntity.ok().body(usecase.execute(id));
    }

    @Operation(
            description = "Rota para atualização de endereco",
            summary = "Atualização de endereco"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEndereco(
            @RequestBody UpdateEnderecoDTO request,
            @PathVariable Long id
    ) {
        var usecase = UpdateEnderecoUseCase.create(enderecoRepository);
        usecase.execute(id, request);

        return ResponseEntity.noContent().build();
    }
}
