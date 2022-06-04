package com.applitools.applifashion.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class ProductActivity extends AppCompatActivity {
    ImageView selectedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_layout);
        selectedImage = (ImageView) findViewById(R.id.selectedImage); // init a ImageView
        Intent intent = getIntent(); // get Intent which was set from adapter of Previous Activity
        selectedImage.setImageResource(intent.getIntExtra("image", 0)); // get image from Intent and set it in ImageView
    }
}


/**
 *   android:paddingBottom="@dimen/activity_vertical_margin"
 *     android:paddingLeft="@dimen/activity_horizontal_margin"
 *     android:paddingRight="@dimen/activity_horizontal_margin"
 *     android:paddingTop="@dimen/activity_vertical_margin"
 */