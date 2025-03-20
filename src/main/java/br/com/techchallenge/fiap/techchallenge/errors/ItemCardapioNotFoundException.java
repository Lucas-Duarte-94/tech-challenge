package br.com.techchallenge.fiap.techchallenge.errors;

public class ItemCardapioNotFoundException extends RuntimeException {
    public ItemCardapioNotFoundException(String message) {
        super(message);
    }
  public ItemCardapioNotFoundException() {
    super("Item do cardápio não encontrado!");
  }
}
