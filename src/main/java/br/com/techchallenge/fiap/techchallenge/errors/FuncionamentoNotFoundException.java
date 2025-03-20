package br.com.techchallenge.fiap.techchallenge.errors;

public class FuncionamentoNotFoundException extends RuntimeException {
    public FuncionamentoNotFoundException(String message) {
        super(message);
    }
    public FuncionamentoNotFoundException() {
        super("Horario de funcionamento não encontrado para este restaurante.");
    }
}
