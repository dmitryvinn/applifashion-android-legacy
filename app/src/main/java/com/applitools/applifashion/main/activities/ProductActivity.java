package com.applitools.applifashion.main.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.applitools.applifashion.main.R;
import com.applitools.applifashion.main.beans.Shoe;

public class ProductActivity extends AppCompatActivity {
    private final static String[] PRODUCT_SIZES = new String[]{"Small (S)", "M", "L", "XL"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_layout);

        final Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        setSizeSpinner();

        final Intent intent = getIntent(); // get Intent which was set from adapter of Previous Activity
        final Shoe shoe = (Shoe) intent.getSerializableExtra("shoe");
        updateActivity(shoe);
        activateCounter();
    }

    private void updateActivity(final Shoe shoe) {
        final TextView shoeName = (TextView) findViewById(R.id.shoe_name_product_page);
        final ImageView shoeImage = (ImageView) findViewById(R.id.shoe_image_product_page);
        shoeName.setText(shoe.getName());
        @SuppressLint("UseCompatLoadingForDrawables") final Drawable shoeImageDrawable
                = getResources().getDrawable(shoe.getImageId());
        shoeImage.setImageDrawable(shoeImageDrawable);

        final TextView currentPrice = (TextView) findViewById(R.id.current_price_product_page);
        currentPrice.setText(shoe.getCurrentPrice());
        final TextView oldPrice = (TextView) findViewById(R.id.old_price_product_page);
        if (shoe.getOldPrice() != null) {
            oldPrice.setText(shoe.getOldPrice());
            oldPrice.setPaintFlags(oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            oldPrice.setVisibility(View.GONE);
        }

    }

    private void setSizeSpinner() {
        final Spinner dropdown = findViewById(R.id.size_dropdown);
        final ArrayAdapter<String> adapter
                = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, PRODUCT_SIZES);
        dropdown.setAdapter(adapter);
    }

    private void activateCounter() {
        final EditText counter = (EditText) findViewById(R.id.quantity);
        final Button increment = (Button) findViewById(R.id.incremental);
        final Button decremental = (Button) findViewById(R.id.decremental);
        increment.setOnClickListener(view -> {
            Integer counterValue = Integer.parseInt(counter.getText().toString());
            counter.setText(String.valueOf(++counterValue));

        });
        decremental.setOnClickListener(view -> {
            Integer counterValue = Integer.parseInt(counter.getText().toString());
            if (counterValue > 0) {
                counter.setText(String.valueOf(--counterValue));
            }
        });
    }
}
