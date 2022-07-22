package com.applitools.applifashion.main.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.applitools.applifashion.main.R;
import com.applitools.applifashion.main.utils.Brand;
import com.applitools.applifashion.main.utils.Color;
import com.applitools.applifashion.main.utils.PriceRange;
import com.applitools.applifashion.main.utils.Type;

import java.util.ArrayList;

public class FilteringActivity extends AppCompatActivity {

    private CheckBox colorBlack;
    private CheckBox colorWhite;
    private CheckBox colorBlue;
    private CheckBox colorGreen;
    private CheckBox colorYellow;


    private CheckBox brandAbibas;
    private CheckBox brandMykey;
    private CheckBox brandBans;
    private CheckBox brandOverArmour;
    private CheckBox brandImBalance;


    private CheckBox priceRangeUpTo50;
    private CheckBox priceRangeUpTo100;
    private CheckBox priceRangeUpTo150;
    private CheckBox priceRangeUpTo500;

    private CheckBox typeSoccer;
    private CheckBox typeBasketball;
    private CheckBox typeRunning;
    private CheckBox typeTraining;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtering);// Finding CheckBox by its unique ID

        colorBlack = findViewById(R.id.color_black);
        colorWhite = findViewById(R.id.color_white);
        colorBlue = findViewById(R.id.color_blue);
        colorGreen = findViewById(R.id.color_green);
        colorYellow = findViewById(R.id.color_yellow);

        brandAbibas = findViewById(R.id.brand_abibas);
        brandMykey = findViewById(R.id.brand_mykey);
        brandBans = findViewById(R.id.brand_bans);
        brandOverArmour = findViewById(R.id.brand_over_armour);
        brandImBalance = findViewById(R.id.brand_imbalance);

        priceRangeUpTo50 = findViewById(R.id.up_to_50);
        priceRangeUpTo100 = findViewById(R.id.up_to_100);
        priceRangeUpTo150 = findViewById(R.id.up_to_150);
        priceRangeUpTo500 = findViewById(R.id.up_to_500);

        typeSoccer = findViewById(R.id.type_soccer);
        typeBasketball = findViewById(R.id.type_basketball);
        typeRunning = findViewById(R.id.type_running);
        typeTraining = findViewById(R.id.type_training);

    }

    public void Reset(View v) {
        colorBlack.setChecked(false);
        colorWhite.setChecked(false);
        colorBlue.setChecked(false);
        colorGreen.setChecked(false);
        colorYellow.setChecked(false);

        brandAbibas.setChecked(false);
        brandMykey.setChecked(false);
        brandBans.setChecked(false);
        brandOverArmour.setChecked(false);
        brandImBalance.setChecked(false);

        priceRangeUpTo50.setChecked(false);
        priceRangeUpTo100.setChecked(false);
        priceRangeUpTo150.setChecked(false);
        priceRangeUpTo500.setChecked(false);

        typeSoccer.setChecked(false);
        typeBasketball.setChecked(false);
        typeRunning.setChecked(false);
        typeTraining.setChecked(false);
    }

    public void Filter(View v) {
        ArrayList<String> brands = getBrands();
        ArrayList<String> colors = getColors();
        ArrayList<String> priceRanges = getPriceRange();
        ArrayList<String> types = getTypes();




        // Put the String to pass back into an Intent and close this activity
        Intent intent = new Intent();
        intent.putStringArrayListExtra("brands", brands);
        intent.putStringArrayListExtra("colors", colors);
        intent.putStringArrayListExtra("priceRanges", priceRanges);
        intent.putStringArrayListExtra("types", types);

        setResult(RESULT_OK, intent);
        finish();
    }

    private ArrayList<String> getBrands() {
        final ArrayList<String> brands = new ArrayList<>();

        if (brandAbibas.isChecked()) {
            brands.add(Brand.ABIBAS.getValue());
        }

        if (brandMykey.isChecked()) {
            brands.add(Brand.MYKEY.getValue());
        }

        if (brandBans.isChecked()) {
            brands.add(Brand.BANS.getValue());
        }

        if (brandOverArmour.isChecked()) {
            brands.add(Brand.OVER_ARMOUR.getValue());
        }

        if (brandImBalance.isChecked()) {
            brands.add(Brand.IM_BALANCE.getValue());
        }

        return brands;
    }

    private ArrayList<String> getColors() {
        final ArrayList<String> colors = new ArrayList<>();

        if (colorBlack.isChecked()) {
            colors.add(Color.BLACK.getValue());
        }

        if (colorWhite.isChecked()) {
            colors.add(Color.WHITE.getValue());
        }

        if (colorBlue.isChecked()) {
            colors.add(Color.BLUE.getValue());
        }

        if (colorGreen.isChecked()) {
            colors.add(Color.BLUE.getValue());
        }

        if (colorYellow.isChecked()) {
            colors.add(Color.YELLOW.getValue());
        }

        return colors;
    }

    private ArrayList<String> getPriceRange() {
        final ArrayList<String> priceRanges = new ArrayList<>();

        if (priceRangeUpTo50.isChecked()) {
            priceRanges.add(PriceRange.UP_TO_50.getValue());
        }

        if (priceRangeUpTo100.isChecked()) {
            priceRanges.add(PriceRange.UP_TO_100.getValue());
        }

        if (priceRangeUpTo150.isChecked()) {
            priceRanges.add(PriceRange.UP_TO_150.getValue());
        }

        if (priceRangeUpTo500.isChecked()) {
            priceRanges.add(PriceRange.UP_TO_500.getValue());
        }

        return priceRanges;
    }


    private ArrayList<String> getTypes() {
        final ArrayList<String> types = new ArrayList<>();

        if (typeSoccer.isChecked()) {
            types.add(Type.SOCCER.getValue());
        }

        if (typeBasketball.isChecked()) {
            types.add(Type.BASKETBALL.getValue());
        }

        if (typeRunning.isChecked()) {
            types.add(Type.RUNNING.getValue());
        }

        if (typeTraining.isChecked()) {
            types.add(Type.TRAINING.getValue());
        }


        return types;
    }
}