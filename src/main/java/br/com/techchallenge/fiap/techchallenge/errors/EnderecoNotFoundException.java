package br.com.techchallenge.fiap.techchallenge.errors;

public class EnderecoNotFoundException extends RuntimeException {
    public EnderecoNotFoundException(String message) {
        super(message);
    }
  public EnderecoNotFoundException() {
    super("endereço não encontrado!");
  }
}
