package com.applitools.applifashion.main.utils;

import java.util.Arrays;

public enum PriceRange {
    UP_TO_50("$0 - $50", 0, 50),
    UP_TO_100("$50 - $100", 50, 100),
    UP_TO_150("$100 - $150", 100, 150),
    UP_TO_500("$150 - $500", 150, 500);

    final String value;
    final int minPrice;
    final int maxPrice;

    PriceRange(String value, int minPrice, int maxPrice) {
        this.value = value;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;

    }

    public String getValue() {
        return value;
    }

    public static PriceRange getEnum(String value) {
        return Arrays.stream(PriceRange.values())
                .filter(c -> c.value.equals(value))
                .findFirst().orElse(null);
    }

    public int getMinPrice() {
        return minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }
}