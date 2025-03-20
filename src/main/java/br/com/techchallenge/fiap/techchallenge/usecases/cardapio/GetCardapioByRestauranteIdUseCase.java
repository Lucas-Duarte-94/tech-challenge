package br.com.techchallenge.fiap.techchallenge.usecases.cardapio;

import br.com.techchallenge.fiap.techchallenge.dtos.CardapioResponseDTO;
import br.com.techchallenge.fiap.techchallenge.entities.Cardapio;
import br.com.techchallenge.fiap.techchallenge.entities.ItemCardapio;
import br.com.techchallenge.fiap.techchallenge.errors.CardapioNotFoundException;
import br.com.techchallenge.fiap.techchallenge.repositories.CardapioRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.ItemCardapioRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;

import java.util.List;

public class GetCardapioByRestauranteIdUseCase {
    private final CardapioRepository cardapioRepository;
    private final RestauranteRepository restauranteRepository;
    private final ItemCardapioRepository itemCardapioRepository;

    private GetCardapioByRestauranteIdUseCase(
            CardapioRepository cardapioRepository,
            RestauranteRepository restauranteRepository,
            ItemCardapioRepository itemCardapioRepository
    ) {
        this.cardapioRepository = cardapioRepository;
        this.restauranteRepository = restauranteRepository;
        this.itemCardapioRepository = itemCardapioRepository;
    }

    public static GetCardapioByRestauranteIdUseCase create(
            CardapioRepository cardapioRepository,
            RestauranteRepository restauranteRepository,
            ItemCardapioRepository itemCardapioRepository
    ) {
        return new GetCardapioByRestauranteIdUseCase(cardapioRepository, restauranteRepository, itemCardapioRepository);
    }

    public CardapioResponseDTO execute(Long restauranteId) {
        var cardapio = cardapioRepository.findByRestauranteId(restauranteId).orElseThrow(CardapioNotFoundException::new);
        
        List<ItemCardapio> cardapioItems = itemCardapioRepository.findAllByCardapio_Id(cardapio.getIdCardapio());
        return new CardapioResponseDTO(cardapio, cardapioItems);
    }
}
