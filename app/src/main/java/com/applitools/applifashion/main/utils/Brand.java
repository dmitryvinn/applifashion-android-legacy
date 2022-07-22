package com.applitools.applifashion.main.utils;

import java.util.Arrays;

public enum Brand {
    ANY("Any"),
    ABIBAS("Abibas"),
    MYKEY("Mykey"),
    BANS("Bans"),
    OVER_ARMOUR("Over Armour"),
    IM_BALANCE("ImBalance");

    final String value;

    Brand(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Brand getEnum(String value) {
        return Arrays.stream(Brand.values())
                .filter(c -> c.value.equals(value))
                .findFirst().orElse(ANY);
    }

}
