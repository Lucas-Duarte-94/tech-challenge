package br.com.techchallenge.fiap.techchallenge.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoUsuarioEnum {
    CLIENTE("cliente"),
    DONO_RESTAURANTE("dono_restaurante"),
    ADMIN("admin");

    private final String value;

    TipoUsuarioEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static TipoUsuarioEnum fromValue(String value) {
        for (TipoUsuarioEnum tipo : TipoUsuarioEnum.values()) {
            if (tipo.value.equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Valor inválido para TipoUsuario: " + value);
    }
}
