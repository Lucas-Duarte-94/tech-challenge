package br.com.techchallenge.fiap.techchallenge.usecases.funcionamento;

import br.com.techchallenge.fiap.techchallenge.dtos.CreateFuncionamentoDTO;
import br.com.techchallenge.fiap.techchallenge.entities.Funcionamento;
import br.com.techchallenge.fiap.techchallenge.entities.Restaurante;
import br.com.techchallenge.fiap.techchallenge.errors.RestauranteNotFoundException;
import br.com.techchallenge.fiap.techchallenge.repositories.FuncionamentoRepository;
import br.com.techchallenge.fiap.techchallenge.repositories.RestauranteRepository;

public class CreateFuncionamentoUseCase {
    private final FuncionamentoRepository funcionamentoRepository;
    private final RestauranteRepository restauranteRepository;

    private CreateFuncionamentoUseCase(FuncionamentoRepository funcionamentoRepository,RestauranteRepository restauranteRepository) {
        this.funcionamentoRepository = funcionamentoRepository;
        this.restauranteRepository = restauranteRepository;
    }

    public static CreateFuncionamentoUseCase create(FuncionamentoRepository funcionamentoRepository, RestauranteRepository restauranteRepository) {
        return new CreateFuncionamentoUseCase(funcionamentoRepository, restauranteRepository);
    }

    public Funcionamento execute(CreateFuncionamentoDTO funcionamento) {
        Restaurante existingRestaurante = restauranteRepository.findById(funcionamento.restauranteId()).orElseThrow(RestauranteNotFoundException::new);

        Funcionamento newFuncionamento = Funcionamento.builder()
                .diaSemana(funcionamento.diaSemana())
                .horaAbertura(funcionamento.horaAbertura())
                .horaFechamento(funcionamento.horaFechamento())
                .restaurante(existingRestaurante)
                .build();

        return funcionamentoRepository.save(newFuncionamento);
    }
}
