package br.com.techchallenge.fiap.techchallenge.errors;

public class UnkownType extends RuntimeException {
    public UnkownType() {
        super("Tipo de usuário desconhecido: ");
    }
}
