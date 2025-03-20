package br.com.techchallenge.fiap.techchallenge.errors;

public class MissingIdForEnderecoException extends RuntimeException {
    public MissingIdForEnderecoException(String message) {
        super(message);
    }
  public MissingIdForEnderecoException() {
    super("Id de restaurante ou usuário não informado!");
  }
}
