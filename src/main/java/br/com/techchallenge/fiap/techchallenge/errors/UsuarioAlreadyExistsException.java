package br.com.techchallenge.fiap.techchallenge.errors;

public class UsuarioAlreadyExistsException extends RuntimeException {
    public UsuarioAlreadyExistsException(String message) {
        super(message);
    }
    public UsuarioAlreadyExistsException() {
        super("O usuário com esse login já existe");
    }
}
