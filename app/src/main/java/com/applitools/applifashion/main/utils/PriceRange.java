package com.applitools.applifashion.main.utils;

import java.util.Arrays;

public enum PriceRange {
    ANY("Any", 0),
    UP_TO_50("$0 - $50", 50),
    UP_TO_100("$50 - $100", 100),
    UP_TO_150("$100 - $150", 150),
    UP_TO_500("$150 - $500", 500);

    final String value;
    final int maxPrice;

    PriceRange(String value, int maxPrice) {
        this.value = value;
        this.maxPrice = maxPrice;

    }

    public String getValue() {
        return value;
    }

    public static PriceRange getEnum(String value) {
        return Arrays.stream(PriceRange.values())
                .filter(c -> c.value.equals(value))
                .findFirst().orElse(ANY);
    }

    public int getMaxPrice() {
        return maxPrice;
    }
}