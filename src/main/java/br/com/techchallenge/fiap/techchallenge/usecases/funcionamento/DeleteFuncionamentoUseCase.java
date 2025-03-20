package br.com.techchallenge.fiap.techchallenge.usecases.funcionamento;

import br.com.techchallenge.fiap.techchallenge.repositories.FuncionamentoRepository;

public class DeleteFuncionamentoUseCase {
    private final FuncionamentoRepository funcionamentoRepository;

    private DeleteFuncionamentoUseCase(FuncionamentoRepository funcionamentoRepository) {
        this.funcionamentoRepository = funcionamentoRepository;
    }

    public static DeleteFuncionamentoUseCase create(FuncionamentoRepository funcionamentoRepository) {
        return new DeleteFuncionamentoUseCase(funcionamentoRepository);
    }

    public void execute(Long funcionamentoId) {
        funcionamentoRepository.deleteById(funcionamentoId);
    }
}
