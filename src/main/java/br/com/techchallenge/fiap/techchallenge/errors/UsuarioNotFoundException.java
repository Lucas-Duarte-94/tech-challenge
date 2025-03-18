package br.com.techchallenge.fiap.techchallenge.errors;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException() {
        super("Usuário não encontrado!");
    }

    public UsuarioNotFoundException(String id) {
        super("Usuário com o ID " + id + " não foi encontrado!");
    }
}
