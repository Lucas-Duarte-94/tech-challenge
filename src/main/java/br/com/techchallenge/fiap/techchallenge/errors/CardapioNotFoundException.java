package br.com.techchallenge.fiap.techchallenge.errors;

public class CardapioNotFoundException extends RuntimeException {
    public CardapioNotFoundException(String message) {
        super(message);
    }
    public CardapioNotFoundException() {
        super("Cardapio não encontrado!");
    }
}
