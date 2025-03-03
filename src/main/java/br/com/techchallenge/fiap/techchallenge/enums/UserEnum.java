package br.com.techchallenge.fiap.techchallenge.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserEnum {
    client("client"),
    restaurant_owner("restaurant_owner");

    private final String value;

    UserEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @JsonCreator
    public static UserEnum fromValue(String value) {
        for (UserEnum userEnum : UserEnum.values()) {
            if (userEnum.value.equals(value)) {
                return userEnum;
            }
        }
        throw new IllegalArgumentException("Valor inválido para UserEnum: " + value);
    }
}
