package com.example.nehaniphadkar.goldenpalace;

import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ankushgrover.hourglass.Hourglass;
import com.example.nehaniphadkar.goldenpalace.navigationdrawer.BaseActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SaladItemsActivity extends BaseActivity {

    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private String id;
    private DatabaseReference database;
    private RecyclerView directionRecycler;
    private Hourglass hourglass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salad_items);
        id = getIntent().getExtras().getString("id");
        database = FirebaseDatabase.getInstance().getReference("description").child(id);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot descriptionSnapshot : dataSnapshot.getChildren()) {
                    final SaladDescription saladDescription = descriptionSnapshot.getValue(SaladDescription.class);
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.incredientsRecycler);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SaladItemsActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    ArrayList<String> data = new ArrayList<>();
                    data.addAll(saladDescription.getItemsRequired());


                    IncredientsAdapter incredientsAdapter = new IncredientsAdapter(SaladItemsActivity.this, data, true);
                    recyclerView.setAdapter(incredientsAdapter);
                    directionRecycler = (RecyclerView) findViewById(R.id.directionRecycler);
                    directionRecycler.setLayoutManager(new LinearLayoutManager(SaladItemsActivity.this));
                    data = new ArrayList<>();
                    data.addAll(saladDescription.getListProcedure());


                    incredientsAdapter = new IncredientsAdapter(SaladItemsActivity.this, data, false);
                    directionRecycler.setAdapter(incredientsAdapter);
                    ImageView image = (ImageView) findViewById(R.id.image);
                    image.setImageResource(saladDescription.getImageId());
                    final TextView texttimer = (TextView) findViewById(R.id.texttimer);
                    texttimer.setText(saladDescription.getTime() + " m");

                    ImageView imageView = (ImageView) findViewById(R.id.timer);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            hourglass = new Hourglass(Integer.parseInt(saladDescription.getTime()) * 60 * 1000, 1000) {
                                @Override
                                public void onTimerTick(long millisUntilFinished) {
                                    String v = String.format("%02d", millisUntilFinished / 60000);
                                    int va = (int) ((millisUntilFinished % 60000) / 1000);
                                    texttimer.setText(v + ":" + String.format("%02d", va));
                                    final Button button = (Button) findViewById(R.id.pause);
                                    button.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if (hourglass.isPaused()) {
                                                button.setText("Pause Timer");
                                                hourglass.resumeTimer();
                                            } else {
                                                button.setText("Resume Timer");

                                                hourglass.pauseTimer();
                                            }
                                        }
                                    });
                                }

                                @Override
                                public void onTimerFinish() {
                                    // Timer finished


                                }
                            };

                            hourglass.startTimer();
                        }
                    });


                    final TextView calorieCounter = (TextView) findViewById(R.id.calorieCounter);

                    final TextView calories = (TextView) findViewById(R.id.calories);
                    ImageView caloriesup = (ImageView) findViewById(R.id.caloriesup);
                    ImageView caloriesdown = (ImageView) findViewById(R.id.caloriesdown);
                    calorieCounter.setText("1");
                    calories.setText(saladDescription.getCalories() + " Calories");
                    caloriesup.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int a = Integer.parseInt(calorieCounter.getText().toString());
                            int b = a + 1;
                            Log.v("ggg", String.valueOf(a));
                            calorieCounter.setText(String.valueOf(b));
                            int d = Integer.parseInt(calorieCounter.getText().toString());
                            int f = d * Integer.parseInt(saladDescription.getCalories());
                            calories.setText(String.valueOf(f) + " Calories");
                        }
                    });
                    caloriesdown.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int d = Integer.parseInt(calorieCounter.getText().toString());
                            if (d!=1) {
                                int y = d - 1;
                                calorieCounter.setText(String.valueOf(y));
                                int h = Integer.parseInt(calorieCounter.getText().toString());
                                Log.v("ggggkk", String.valueOf(h));
                                int p = h * Integer.parseInt(saladDescription.getCalories());
                                calories.setText(String.valueOf(p) + " Calories");
                            }
                        }
                    });

                }

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

}
