package com.applitools.applifashion.main.beans;

import com.applitools.applifashion.main.utils.Brand;
import com.applitools.applifashion.main.utils.Color;
import com.applitools.applifashion.main.utils.Type;

import java.io.Serializable;

public class Shoe implements Serializable {
    private final String name;
    private final int imageId;
    private final String currentPrice;
    private final int currentPriceValue;
    private final Color color;
    private final Type type;
    private final Brand brand;

    private String oldPrice;

    public Shoe(String name, int imageId, Color color, Type type, Brand brand, String currentPrice) {
        this.name = name;
        this.imageId = imageId;
        this.currentPrice = currentPrice;
        this.color = color;
        this.type = type;
        this.brand = brand;
        currentPriceValue = ((Double)Double.parseDouble(currentPrice.substring(1))).intValue();
    }
    public Shoe(String name, int imageId, Color color, Type type, Brand brand,  String currentPrice, String oldPrice) {
        this(name, imageId, color, type, brand, currentPrice);
        this.oldPrice = oldPrice;
    }

    public int getCurrentPriceValue() {
        return currentPriceValue;
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public Brand getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public String getOldPrice() {
        return oldPrice;
    }
}
