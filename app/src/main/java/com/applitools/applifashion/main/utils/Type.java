package com.applitools.applifashion.main.utils;

import java.util.Arrays;

public enum Type {
    ANY("Any"),
    SOCCER("Soccer"),
    BASKETBALL("Basketball"),
    RUNNING("Running"),
    TRAINING("Training");

    final String value;

    Type(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Type getEnum(String value) {
        return Arrays.stream(Type.values())
                .filter(c -> c.value.equals(value))
                .findFirst().orElse(ANY);
    }
}
