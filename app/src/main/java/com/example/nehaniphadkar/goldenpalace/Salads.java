package com.example.nehaniphadkar.goldenpalace;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.nehaniphadkar.goldenpalace.navigationdrawer.BaseActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Salads extends BaseActivity {
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private RecyclerView horizontal_recycler_view;
    private List<SaladModel> data;
    DataAdapter DataAdapter;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salads);
        data = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("salads");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                data.clear();
                for (DataSnapshot saladsSnapshot : dataSnapshot.getChildren()) {
                    SaladModel saladModel = saladsSnapshot.getValue(SaladModel.class);
                    data.add(saladModel);

                }
                horizontal_recycler_view = (RecyclerView) findViewById(R.id.card_recycler_view);

                System.out.println("cemsdfc"+data.get(0).getTxt());

                DataAdapter = new DataAdapter(Salads.this, data);

                LinearLayoutManager horizontalLayoutManager = new GridLayoutManager(Salads.this, 2);
                horizontal_recycler_view.setLayoutManager(horizontalLayoutManager);

                horizontal_recycler_view.setAdapter(DataAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items); // load
        // titles
        // from
        // strings.xml

        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);// load icons from
        // strings.xml

        set(navMenuTitles, navMenuIcons);


    }






       /* navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items); // load
        // titles
        // from
        // strings.xml

        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);// load icons from
        // strings.xml

        set(navMenuTitles, navMenuIcons);
*/


}
