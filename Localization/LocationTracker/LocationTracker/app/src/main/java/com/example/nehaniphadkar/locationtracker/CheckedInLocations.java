package com.example.nehaniphadkar.locationtracker;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Map;

public class CheckedInLocations extends Activity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checked_in_locations);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DBHelper dbHelper=new DBHelper(this);
        MapAdapter mapAdapter=new MapAdapter(this,dbHelper.showList());
        recyclerView.setAdapter(mapAdapter);


    }
}
