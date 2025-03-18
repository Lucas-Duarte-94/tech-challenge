package br.com.techchallenge.fiap.techchallenge.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TipoUsuarioNotFound extends RuntimeException {
    public TipoUsuarioNotFound() {
        super("Tipo de usuário não encontrado");
    }
}
