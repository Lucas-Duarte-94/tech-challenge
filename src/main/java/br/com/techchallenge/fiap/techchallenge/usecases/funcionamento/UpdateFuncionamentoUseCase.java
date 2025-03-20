package br.com.techchallenge.fiap.techchallenge.usecases.funcionamento;

import br.com.techchallenge.fiap.techchallenge.entities.Funcionamento;
import br.com.techchallenge.fiap.techchallenge.errors.FuncionamentoNotFoundException;
import br.com.techchallenge.fiap.techchallenge.repositories.FuncionamentoRepository;

public class UpdateFuncionamentoUseCase {
    private final FuncionamentoRepository funcionamentoRepository;

    private UpdateFuncionamentoUseCase(FuncionamentoRepository funcionamentoRepository) {
        this.funcionamentoRepository = funcionamentoRepository;
    }

    public static UpdateFuncionamentoUseCase create(FuncionamentoRepository funcionamentoRepository) {
        return new UpdateFuncionamentoUseCase(funcionamentoRepository);
    }

    public Funcionamento execute(Funcionamento funcionamento) {
        // Aplicar lógica com token do usuario para validar se o restaurante pertence a quem está fazendo alteração

        // TODO


        funcionamentoRepository.findById(funcionamento.getIdFuncionamento()).orElseThrow(FuncionamentoNotFoundException::new);

        return funcionamentoRepository.save(funcionamento);
    }
}
