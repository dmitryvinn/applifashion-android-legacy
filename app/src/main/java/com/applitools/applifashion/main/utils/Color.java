package com.applitools.applifashion.main.utils;

import java.util.Arrays;

public enum Color {
    ANY("Any"),
    BLACK("Black"),
    WHITE("White"),
    BLUE("Blue"),
    GREEN("Green"),
    YELLOW("Yellow");

    final String value;

    Color(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Color getEnum(String value) {
        return Arrays.stream(Color.values())
                .filter(c -> c.value.equals(value))
                .findFirst().orElse(ANY);
    }
}
