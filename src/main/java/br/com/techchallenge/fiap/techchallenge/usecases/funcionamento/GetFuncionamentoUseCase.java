package br.com.techchallenge.fiap.techchallenge.usecases.funcionamento;

import br.com.techchallenge.fiap.techchallenge.repositories.FuncionamentoRepository;

public class GetFuncionamentoUseCase {
    private final FuncionamentoRepository funcionamentoRepository;

    private GetFuncionamentoUseCase(FuncionamentoRepository funcionamentoRepository) {
        this.funcionamentoRepository = funcionamentoRepository;
    }

    public static GetFuncionamentoUseCase create(FuncionamentoRepository funcionamentoRepository) {
        return new GetFuncionamentoUseCase(funcionamentoRepository);
    }
}
