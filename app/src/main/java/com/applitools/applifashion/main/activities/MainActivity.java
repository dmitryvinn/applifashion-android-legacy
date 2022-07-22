package com.applitools.applifashion.main.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SlidingDrawer;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.applitools.applifashion.main.FilteringActivity;
import com.applitools.applifashion.main.adapters.ProductAdapter;
import com.applitools.applifashion.main.R;
import com.applitools.applifashion.main.beans.Shoe;
import com.applitools.applifashion.main.utils.Brand;
import com.applitools.applifashion.main.utils.Color;
import com.applitools.applifashion.main.utils.PriceRange;
import com.applitools.applifashion.main.utils.ShoesGenerator;
import com.applitools.applifashion.main.utils.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private final static String[] SORT_BY_VALUES = new String[]{"Popularity", "Average rating", "Newness",
            "Price: low to high", "Price: high to low"};
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        setSortBySpinner();

        final List<Shoe> shoes = ShoesGenerator.getInstance().getShoes();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        }
        else {
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        }

        final ProductAdapter customAdapter = new ProductAdapter(MainActivity.this, shoes);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView

        final ImageView filter = (ImageView) findViewById(R.id.filter);

        filter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(getApplicationContext(), FilteringActivity.class);
//                intent.putExtra("shoe", shoe);
                startActivityForResult(intent, 1);
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {

            List<String> brands = data.getStringArrayListExtra("brands");
            List<String> colors = data.getStringArrayListExtra("colors");
            List<String> priceRanges = data.getStringArrayListExtra("priceRanges");
            List<String> types = data.getStringArrayListExtra("types");

            final List<Shoe> filteredShoes = ShoesGenerator.getInstance().getShoes()
                    .stream().filter(shoe -> {
                        boolean fitsBrand = true;
                        boolean fitsColor = true;
                        boolean fitsPrice = true;
                        boolean fitsType = true;

                        if (!brands.isEmpty()) {
                            fitsBrand = brands.stream().anyMatch(brand ->
                                    Brand.getEnum(brand).equals(shoe.getBrand()));
                        }

                        if (!colors.isEmpty()) {
                            fitsColor = colors.stream().anyMatch(color ->
                                    Color.getEnum(color).equals(shoe.getColor()));
                        }

                        if (!priceRanges.isEmpty()) {
                            fitsPrice = priceRanges.stream().anyMatch(priceRange ->
                                    shoe.getCurrentPriceValue() <= PriceRange.getEnum(priceRange).getMaxPrice());
                        }

                        if (!types.isEmpty()) {
                            fitsType = types.stream().anyMatch(type ->
                                    Type.getEnum(type).equals(shoe.getType()));
                        }
                        return fitsBrand && fitsColor && fitsPrice && fitsType;
                    }).collect(Collectors.toList());



            final ProductAdapter customAdapter = new ProductAdapter(MainActivity.this, filteredShoes);
            recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView

        }
    }

    private void setSortBySpinner() {
       final Spinner dropdown = findViewById(R.id.sort_by);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, SORT_BY_VALUES);
        dropdown.setAdapter(adapter);
    }
}

/**
 *
 *
 * <SlidingDrawer
 *         android:id="@+id/SlidingDrawer"
 *         android:layout_width="wrap_content"
 *         android:layout_height="250dip"
 *         android:content="@+id/contentLayout"
 *         android:handle="@+id/slideHandleButton"
 *         android:padding="10dip" >
 *
 *         <Button
 *             android:id="@+id/slideHandleButton"
 *             android:layout_width="wrap_content"
 *             android:layout_height="wrap_content"
 *             android:background="@drawable/closearrow" >
 *         </Button>
 *
 *         <LinearLayout
 *             android:id="@+id/contentLayout"
 *             android:layout_width="wrap_content"
 *             android:layout_height="wrap_content"
 *             android:gravity="center|top"
 *             android:orientation="vertical"
 *             android:background="#ff00ff"
 *             android:padding="10dip" >
 *
 *             <Button
 *                 android:id="@+id/Button01"
 *                 android:layout_width="wrap_content"
 *                 android:layout_height="wrap_content"
 *                 android:text="Content" >
 *             </Button>
 *
 *             <Button
 *                 android:id="@+id/Button02"
 *                 android:layout_width="wrap_content"
 *                 android:layout_height="wrap_content"
 *                 android:layout_gravity="center"
 *                 android:text="Content" >
 *
 *             </Button>
 *
 *             <Button
 *                 android:id="@+id/Button03"
 *                 android:layout_width="wrap_content"
 *                 android:layout_height="wrap_content"
 *                 android:text="Content" >
 *             </Button>
 *         </LinearLayout>
 *     </SlidingDrawer>
 *
 */