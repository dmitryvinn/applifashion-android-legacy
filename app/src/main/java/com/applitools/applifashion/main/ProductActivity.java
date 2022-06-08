package com.applitools.applifashion.main;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.Image;
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

import java.util.List;

public class ProductActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_layout);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
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
        final Drawable shoeImageDrawable = getResources().getDrawable(shoe.getImageId());
        shoeImage.setImageDrawable(shoeImageDrawable);

        // Price and add to cart button
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
        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner1);
//create a list of items for the spinner.
        String[] items = new String[]{"Small (S)", "M", "L", "XL"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
    }


    private void activateCounter() {
        final EditText counter = (EditText) findViewById(R.id.quantity);
        final Button increment = (Button) findViewById(R.id.incremental);
        final Button decremental = (Button) findViewById(R.id.decremental);
        increment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Integer counterValue = Integer.parseInt(counter.getText().toString());
                counter.setText(String.valueOf(++counterValue));

            }
        });
        decremental.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Integer counterValue = Integer.parseInt(counter.getText().toString());
                if (counterValue > 0) {
                    counter.setText(String.valueOf(--counterValue));
                }
            }
        });

    }
}


/**
 *   android:paddingBottom="@dimen/activity_vertical_margin"
 *     android:paddingLeft="@dimen/activity_horizontal_margin"
 *     android:paddingRight="@dimen/activity_horizontal_margin"
 *     android:paddingTop="@dimen/activity_vertical_margin"
 */