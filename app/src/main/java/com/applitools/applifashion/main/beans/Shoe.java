package com.applitools.applifashion.main.beans;

import java.io.Serializable;

public class Shoe implements Serializable {
    private final String name;
    private final int imageId;
    private final String currentPrice;
    private String oldPrice;

    public Shoe(String name, int imageId, String currentPrice) {
        this.name = name;
        this.imageId = imageId;
        this.currentPrice = currentPrice;
    }
    public Shoe(String name, int imageId, String currentPrice, String oldPrice) {
        this(name, imageId, currentPrice);
        this.oldPrice = oldPrice;
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
