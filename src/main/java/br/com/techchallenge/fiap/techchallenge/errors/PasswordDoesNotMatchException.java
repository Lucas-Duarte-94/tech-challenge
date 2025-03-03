package br.com.techchallenge.fiap.techchallenge.errors;

public class PasswordDoesNotMatchException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public PasswordDoesNotMatchException() {
        super("Usuário/senha errado!");
    }
}
