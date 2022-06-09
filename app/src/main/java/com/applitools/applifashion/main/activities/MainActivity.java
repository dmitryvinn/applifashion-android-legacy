package com.applitools.applifashion.main.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.applitools.applifashion.main.adapters.ProductAdapter;
import com.applitools.applifashion.main.R;
import com.applitools.applifashion.main.beans.Shoe;
import com.applitools.applifashion.main.utils.ShoesGenerator;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static String[] SORT_BY_VALUES = new String[]{"Popularity", "Average rating", "Newness",
            "Price: low to high", "Price: high to low"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        setSortBySpinner();

        final List<Shoe> shoes = ShoesGenerator.getInstance().getShoes();
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        }
        else {
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        }

        final ProductAdapter customAdapter = new ProductAdapter(MainActivity.this, shoes);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView

    }

    private void setSortBySpinner() {
       final Spinner dropdown = findViewById(R.id.sort_by);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, SORT_BY_VALUES);
        dropdown.setAdapter(adapter);
    }
}