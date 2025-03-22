package br.com.techchallenge.fiap.techchallenge.errors;


public class TipoUsuarioNotFoundException extends RuntimeException {
    public TipoUsuarioNotFoundException() {
        super("Tipo de usuário não encontrado");
    }
}
