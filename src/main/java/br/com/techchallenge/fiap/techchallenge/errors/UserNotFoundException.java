package br.com.techchallenge.fiap.techchallenge.errors;

public class UserNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public UserNotFoundException() {
        super("Usuário não encontrado!");
    }

    public UserNotFoundException(String id) {
        super("Usuário com o ID " + id + " não foi encontrado!");
    }
}
