package br.com.techchallenge.fiap.techchallenge.controllers;

import br.com.techchallenge.fiap.techchallenge.dtos.CardapioResponseDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.CreateCardapioDTO;
import br.com.techchallenge.fiap.techchallenge.repositories.CardapioRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.ItemCardapioRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;
import br.com.techchallenge.fiap.techchallenge.usecases.cardapio.CreateCardapioUseCase;
import br.com.techchallenge.fiap.techchallenge.usecases.cardapio.DeleteCardapioUseCase;
import br.com.techchallenge.fiap.techchallenge.usecases.cardapio.GetCardapioByRestauranteIdUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cardapio")
@Tag(name = "Cardápios", description = "Controller CRUD de cardápios")
public class CardapioController {
    private final CardapioRepository cardapioRepository;
    private final RestauranteRepository restauranteRepository;
    private final ItemCardapioRepository itemCardapioRepository;

    public CardapioController(CardapioRepository cardapioRepository, RestauranteRepository restauranteRepository, ItemCardapioRepository itemCardapioRepository) {
        this.cardapioRepository = cardapioRepository;
        this.restauranteRepository = restauranteRepository;
        this.itemCardapioRepository = itemCardapioRepository;
    }

    @Operation(
            description = "Cria um novo cardapio para o restaurante",
            summary = "Criação de cardapio"
    )
    @PostMapping
    public ResponseEntity<Void> createCardapio(
            @RequestBody CreateCardapioDTO request
    ) {
        var usecase = CreateCardapioUseCase.create(cardapioRepository, restauranteRepository);
        usecase.execute(request.id());

        var status = HttpStatus.CREATED;

        return ResponseEntity.status(status.value()).build();
    }

    @Operation(
            description = "Rota para deletar cardapio",
            summary = "Deletar cardapio"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCardapio(
            @PathVariable Long id
    ) {
        var usecase = DeleteCardapioUseCase.create(cardapioRepository);
        usecase.execute(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(
            description = "Busca de cardapio do restaurante pelo id",
            summary = "Busca de cardapio"
    )
    @GetMapping("/{id}")
    public ResponseEntity<CardapioResponseDTO> getCardapio(
            @PathVariable Long id
    ) {
        var usecase = GetCardapioByRestauranteIdUseCase.create(cardapioRepository, restauranteRepository, itemCardapioRepository);
        return ResponseEntity.ok().body(usecase.execute(id));
    }
}
