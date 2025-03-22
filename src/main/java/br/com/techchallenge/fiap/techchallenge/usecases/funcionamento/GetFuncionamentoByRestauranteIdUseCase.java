package br.com.techchallenge.fiap.techchallenge.usecases.funcionamento;

import br.com.techchallenge.fiap.techchallenge.entities.Funcionamento;
import br.com.techchallenge.fiap.techchallenge.repositories.FuncionamentoRepository;

import java.util.List;

public class GetFuncionamentoByRestauranteIdUseCase {
    private final FuncionamentoRepository funcionamentoRepository;

    private GetFuncionamentoByRestauranteIdUseCase(FuncionamentoRepository funcionamentoRepository) {
        this.funcionamentoRepository = funcionamentoRepository;
    }

    public static GetFuncionamentoByRestauranteIdUseCase create(FuncionamentoRepository funcionamentoRepository) {
        return new GetFuncionamentoByRestauranteIdUseCase(funcionamentoRepository);
    }

    public List<Funcionamento> execute(Long restauranteId) {
        return funcionamentoRepository.findByRestaurante_IdRestaurante(restauranteId);
    }
}
