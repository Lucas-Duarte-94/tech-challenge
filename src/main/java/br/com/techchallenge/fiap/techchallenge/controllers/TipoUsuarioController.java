package br.com.techchallenge.fiap.techchallenge.controllers;

import br.com.techchallenge.fiap.techchallenge.dtos.CreateTipoUsuarioRequestDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.TipoUsuarioUpdateRequestDTO;
import br.com.techchallenge.fiap.techchallenge.entities.TipoUsuario;
import br.com.techchallenge.fiap.techchallenge.repositories.TipoUsuarioRepository;
import br.com.techchallenge.fiap.techchallenge.usecases.tipousuario.CreateTipoUsuarioUseCase;
import br.com.techchallenge.fiap.techchallenge.usecases.tipousuario.DeleteTipoUsuarioUseCase;
import br.com.techchallenge.fiap.techchallenge.usecases.tipousuario.GetTipoUsuarioUseCase;
import br.com.techchallenge.fiap.techchallenge.usecases.tipousuario.UpdateTipoUsuarioUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/tipousuario")
@Tag(name = "Tipo de Usuários", description = "Controller CRUD de TipoUsuario")
public class TipoUsuarioController {
    private final TipoUsuarioRepository tipoUsuarioRepository;

    public TipoUsuarioController(TipoUsuarioRepository tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    @Operation(
            description = "Cria um novo tipo de usuário",
            summary = "Criação de tipo de usuários"
    )
    @PostMapping
    public ResponseEntity<TipoUsuario> createTipoUsuario(
            @RequestBody CreateTipoUsuarioRequestDTO tipoUsuarioDTO
    ) {
        var usecase = CreateTipoUsuarioUseCase.create(tipoUsuarioRepository);
        TipoUsuario novoTipoUsuario = usecase.execute(tipoUsuarioDTO.descricaoTipoUsuario());
        return ResponseEntity.ok().body(novoTipoUsuario);
    }

    @Operation(
            description = "Busca de tipo de usuário pelo id",
            summary = "Busca de tipo de usuários"
    )
    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuario> getTipoUsuario(
            @PathVariable Long id
    ) {
        var usecase = GetTipoUsuarioUseCase.create(tipoUsuarioRepository);
        return ResponseEntity.ok().body(usecase.execute(id));
    }

        @Operation(
            description = "Rota para atualização de tipo de usuário",
            summary = "Atualização de dados"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTipoUsuario(
            @RequestBody TipoUsuarioUpdateRequestDTO tipoUsuarioDTO,
            @PathVariable Long id
    ) {
        var usecase = UpdateTipoUsuarioUseCase.create(tipoUsuarioRepository);
        usecase.execute(id, tipoUsuarioDTO.descricaoTipoUsuario());

        return ResponseEntity.noContent().build();
    }

    @Operation(
            description = "Rota para deletar tipo de usuário",
            summary = "Deletar tipo de usuário"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoUsuario(
            @PathVariable Long id
    ) {
        var usecase = DeleteTipoUsuarioUseCase.create(tipoUsuarioRepository);
        usecase.execute(id);

        return ResponseEntity.noContent().build();
    }
}
