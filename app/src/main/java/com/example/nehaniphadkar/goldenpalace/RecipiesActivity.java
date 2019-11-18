package com.example.nehaniphadkar.goldenpalace;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class RecipiesActivity extends AppCompatActivity {

    private RecyclerView recipesRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipies);
        ArrayList<SaladModel> data = new ArrayList<>();
        recipesRecycler = (RecyclerView) findViewById(R.id.recipesRecycler);
        SaladModel saladModel = new SaladModel();
        saladModel.setTxt("Salad");
        saladModel.setImageId(R.drawable.rsalad);
        saladModel.setId("fdwes");
        data.add(saladModel);
        saladModel = new SaladModel();
        saladModel.setTxt("Desserts");
        saladModel.setImageId(R.drawable.rdessert);
        saladModel.setId("fdwes");
        data.add(saladModel);
        saladModel = new SaladModel();
        saladModel.setTxt("Seafood");
        saladModel.setImageId(R.drawable.rseafood);
        saladModel.setId("fdwes");
        data.add(saladModel);
        saladModel = new SaladModel();
        saladModel.setTxt("Drinks");
        saladModel.setImageId(R.drawable.rdrinks);
        saladModel.setId("fdwes");
        data.add(saladModel);
        saladModel = new SaladModel();
        saladModel.setTxt("Tagine");
        saladModel.setImageId(R.drawable.rtagine);
        saladModel.setId("fdwes");
        data.add(saladModel);
        saladModel = new SaladModel();
        saladModel.setTxt("Special Events");
        saladModel.setImageId(R.drawable.rspecialevent);
        saladModel.setId("fdwes");
        data.add(saladModel);
        RecipesAdapter dataAdapter = new RecipesAdapter(RecipiesActivity.this, data);

        LinearLayoutManager horizontalLayoutManager = new GridLayoutManager(RecipiesActivity.this, 2);
        recipesRecycler.setLayoutManager(horizontalLayoutManager);

        recipesRecycler.setAdapter(dataAdapter);

    }
}
