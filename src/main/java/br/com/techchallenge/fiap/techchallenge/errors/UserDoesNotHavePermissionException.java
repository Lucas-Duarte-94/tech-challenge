package br.com.techchallenge.fiap.techchallenge.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserDoesNotHavePermissionException extends RuntimeException {
    public UserDoesNotHavePermissionException(String message) {
        super(message);
    }
    public UserDoesNotHavePermissionException() {
        super("O usuário não possui a permissão necessária!");
    }
}
