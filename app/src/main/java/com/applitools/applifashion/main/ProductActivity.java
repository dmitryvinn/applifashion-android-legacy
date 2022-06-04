package com.applitools.applifashion.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

public class ProductActivity extends AppCompatActivity {
    ImageView selectedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_layout);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
//
//        selectedImage = (ImageView) findViewById(R.id.selectedImage); // init a ImageView
//        Intent intent = getIntent(); // get Intent which was set from adapter of Previous Activity
//        selectedImage.setImageResource(intent.getIntExtra("image", 0)); // get image from Intent and set it in ImageView
//
//        final List<Shoe> shoes = ShoesGenerator.getInstance().getShoes();
    }
}


/**
 *   android:paddingBottom="@dimen/activity_vertical_margin"
 *     android:paddingLeft="@dimen/activity_horizontal_margin"
 *     android:paddingRight="@dimen/activity_horizontal_margin"
 *     android:paddingTop="@dimen/activity_vertical_margin"
 */