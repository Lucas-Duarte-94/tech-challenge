package br.com.techchallenge.fiap.techChallenge.errors;

public class UnkownType extends RuntimeException {
    public UnkownType() {
        super("Tipo de usuário desconhecido: ");
    }
}
