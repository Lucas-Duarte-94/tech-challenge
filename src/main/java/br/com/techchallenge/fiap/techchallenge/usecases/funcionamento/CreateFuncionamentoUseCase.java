package br.com.techchallenge.fiap.techchallenge.usecases.funcionamento;

import br.com.techchallenge.fiap.techchallenge.repositories.FuncionamentoRepository;

public class CreateFuncionamentoUseCase {
    private final FuncionamentoRepository funcionamentoRepository;

    private CreateFuncionamentoUseCase(FuncionamentoRepository funcionamentoRepository) {
        this.funcionamentoRepository = funcionamentoRepository;
    }

    public static CreateFuncionamentoUseCase create(FuncionamentoRepository funcionamentoRepository) {
        return new CreateFuncionamentoUseCase(funcionamentoRepository);
    }
}
