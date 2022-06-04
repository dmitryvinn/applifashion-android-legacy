package com.applitools.applifashion.main;

import java.io.Serializable;

public class Shoe implements Serializable {
    public Shoe(String name, int imageId, String currentPrice) {
        this.name = name;
        this.imageId = imageId;
        this.currentPrice = currentPrice;
    }
    public Shoe(String name, int imageId, String currentPrice, String oldPrice) {
        this(name, imageId, currentPrice);
        this.oldPrice = oldPrice;
    }

    private String name;
    private int imageId;
    private String currentPrice;
    private String oldPrice;

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
