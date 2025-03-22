package br.com.techchallenge.fiap.techchallenge.controllers;

import br.com.techchallenge.fiap.techchallenge.dtos.CreateFuncionamentoDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.UpdateFuncionamentoDTO;
import br.com.techchallenge.fiap.techchallenge.entities.Funcionamento;
import br.com.techchallenge.fiap.techchallenge.repositories.FuncionamentoRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.UsuarioRepository;
import br.com.techchallenge.fiap.techchallenge.usecases.funcionamento.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionamento")
@Tag(name = "Funcionamentos", description = "Controller CRUD do horário de funcionamento")
public class FuncionamentoController {
    private final FuncionamentoRepository funcionamentoRepository;
    private final RestauranteRepository restauranteRepository;
    private final UsuarioRepository usuarioRepository;

    public FuncionamentoController(FuncionamentoRepository funcionamentoRepository, RestauranteRepository restauranteRepository, UsuarioRepository usuarioRepository) {
        this.funcionamentoRepository = funcionamentoRepository;
        this.restauranteRepository = restauranteRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Operation(
            description = "Busca do horario de funcionamento pelo id",
            summary = "Busca do horario de funcionamento"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Funcionamento> getFuncionamento(
            @PathVariable Long id
    ) {
        var usecase = GetFuncionamentoUseCase.create(funcionamentoRepository);
        return ResponseEntity.ok().body(usecase.execute(id));
    }

    @Operation(
            description = "Busca do horario de funcionamento pelo id",
            summary = "Busca do horario de funcionamento"
    )
    @GetMapping("/{restaruranteId}")
    public ResponseEntity<List<Funcionamento>> getFuncionamentoByRestauranteId(
            @PathVariable Long restaruranteId
    ) {
        var usecase = GetFuncionamentoByRestauranteIdUseCase.create(funcionamentoRepository);
        return ResponseEntity.ok().body(usecase.execute(restaruranteId));
    }

    @Operation(
            description = "Cria um novo horário de funcionamento",
            summary = "Criação de horário de funcionamento"
    )
    @PostMapping
    public ResponseEntity<Void> createFuncionamento(
            @RequestBody CreateFuncionamentoDTO request
    ) {
        var usecase = CreateFuncionamentoUseCase.create(funcionamentoRepository, restauranteRepository, usuarioRepository);
        usecase.execute(request);

        var status = HttpStatus.CREATED;

        return ResponseEntity.status(status.value()).build();
    }

    @Operation(
            description = "Rota para atualização de horário de funcionamento",
            summary = "Atualização de horário de funcionamento"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateFuncionamento(
            @RequestBody UpdateFuncionamentoDTO request,
            @PathVariable Long id
    ) {
        var usecase = UpdateFuncionamentoUseCase.create(funcionamentoRepository, usuarioRepository, restauranteRepository);
        usecase.execute(id, request);

        return ResponseEntity.noContent().build();
    }

    @Operation(
            description = "Rota para deletar horário de funcionamento",
            summary = "Deletar horário de funcionamento"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuncionamento(
            @PathVariable Long id
    ) {
        var usecase = DeleteFuncionamentoUseCase.create(funcionamentoRepository);
        usecase.execute(id);

        return ResponseEntity.noContent().build();
    }
}
