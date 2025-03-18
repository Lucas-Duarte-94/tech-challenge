package br.com.techchallenge.fiap.techchallenge.usecases.funcionamento;

import br.com.techchallenge.fiap.techchallenge.repositories.FuncionamentoRepository;

public class UpdateFuncionamentoUseCase {
    private final FuncionamentoRepository funcionamentoRepository;

    private UpdateFuncionamentoUseCase(FuncionamentoRepository funcionamentoRepository) {
        this.funcionamentoRepository = funcionamentoRepository;
    }

    public static UpdateFuncionamentoUseCase create(FuncionamentoRepository funcionamentoRepository) {
        return new UpdateFuncionamentoUseCase(funcionamentoRepository);
    }
}
