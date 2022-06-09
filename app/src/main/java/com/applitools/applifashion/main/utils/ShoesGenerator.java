package com.applitools.applifashion.main.utils;

import com.applitools.applifashion.main.R;
import com.applitools.applifashion.main.beans.Shoe;

import java.util.ArrayList;
import java.util.List;

public class ShoesGenerator {

    private static ShoesGenerator instance;
    private final List<Shoe> shoes;
    private ShoesGenerator() {
        shoes = new ArrayList<>();

        shoes.add(new Shoe("Appli Air x Night",
                R.drawable.dress_shoes,
                "$33.00",
                "$48.00"));


                shoes.add(new Shoe(
                "Appli ACG React",
                R.drawable.yellow_shoes,
                "$110.00"));
        shoes.add(new Shoe(
                "Appli Air Zoom",
                R.drawable.orange_shoes,
                "$140.00"));

        shoes.add(new Shoe(
                "Appli Air Wild",
                R.drawable.blue_shoes,
                "$52.00",
                "$75.00"));
        shoes.add(new Shoe(
                "Appli Air Alpha",
                R.drawable.green_shoes,
                "$170.00"));

        shoes.add(new Shoe(
                "Appli Air 98",
                R.drawable.purple_shoes,
                "$190.00"));
        shoes.add(new Shoe(
                "Appli Air 720",
                R.drawable.black_shoes,
                "$200.00"));
        shoes.add(new Shoe(
                "Appli Okwahn II",
                R.drawable.white_shoes,
                "$62.00",
                "$90.00"));
    }

    public static ShoesGenerator getInstance()
    {
        if (instance == null)
            instance = new ShoesGenerator();
        return instance;
    }

    public List<Shoe> getShoes() {
        return shoes;
    }

}
