package br.com.techchallenge.fiap.techchallenge.controllers;

import br.com.techchallenge.fiap.techchallenge.dtos.CreateItemCardapioDTO;
import br.com.techchallenge.fiap.techchallenge.dtos.UpdateItemCardapioDTO;
import br.com.techchallenge.fiap.techchallenge.entities.ItemCardapio;
import br.com.techchallenge.fiap.techchallenge.repositories.CardapioRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.ItemCardapioRepository;
import br.com.techchallenge.fiap.techchallenge.usecases.itemcardapio.CreateItemCardapioUseCase;
import br.com.techchallenge.fiap.techchallenge.usecases.itemcardapio.DeleteItemCardapioUseCase;
import br.com.techchallenge.fiap.techchallenge.usecases.itemcardapio.GetItemCardapioByIdUseCase;
import br.com.techchallenge.fiap.techchallenge.usecases.itemcardapio.UpdateItemCardapioUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jfr.ContentType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/itemcardapio")
@Tag(name = "Itens Cardápio", description = "Controller CRUD de item do cardapio")
public class ItemCardapioController {
    private final ItemCardapioRepository itemCardapioRepository;
    private final CardapioRepository cardapioRepository;

    public ItemCardapioController(ItemCardapioRepository itemCardapioRepository, CardapioRepository cardapioRepository) {
        this.itemCardapioRepository = itemCardapioRepository;
        this.cardapioRepository = cardapioRepository;
    }

    @Operation(
            description = "Cria um novo itemCardapio",
            summary = "Criação de itemCardapio"
    )
    @PostMapping
    public ResponseEntity<Void> createItemCardapio(
            @RequestBody CreateItemCardapioDTO itemCardapioDTO
    ) {
        var usecase = CreateItemCardapioUseCase.create(itemCardapioRepository, cardapioRepository);
        usecase.execute(itemCardapioDTO);

        var status = HttpStatus.CREATED;

        return ResponseEntity.status(status.value()).build();
    }

    @Operation(
            description = "Rota para deletar item do cardapio",
            summary = "Deletar item do cardapio"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemCardapio(
            @PathVariable Long id
    ) {
        var usecase = DeleteItemCardapioUseCase.create(itemCardapioRepository);
        usecase.execute(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(
            description = "Rota para atualização de Item do cardapio",
            summary = "Atualização de dados"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateItemCardapio(
            @RequestBody UpdateItemCardapioDTO tipoUsuarioDTO,
            @PathVariable Long id
    ) {
        var usecase = UpdateItemCardapioUseCase.create(itemCardapioRepository);
        usecase.execute(id, tipoUsuarioDTO);

        return ResponseEntity.noContent().build();
    }

    @Operation(
            description = "Busca de Item do cardápio",
            summary = "Busca de item do cardápio"
    )
    @GetMapping("/{id}")
    public ResponseEntity<ItemCardapio> getItemCardapio(
            @PathVariable Long id
    ) {
        var usecase = GetItemCardapioByIdUseCase.create(itemCardapioRepository);
        var itemCardapio = usecase.execute(id);

        return ResponseEntity.ok().body(itemCardapio);
    }
}
