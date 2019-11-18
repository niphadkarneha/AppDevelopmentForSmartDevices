package com.example.nehaniphadkar.goldenpalace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SplashScreen extends AppCompatActivity {
    private FirebaseDatabase mFirebaseInstance;
    private DatabaseReference mFirebaseDatabase;
    private String TAG = "esdfcsd";
    private String userId;
    private DatabaseReference mFirebaseDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("salads");

        Button button = (Button) findViewById(R.id.enter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseDatabase = FirebaseDatabase.getInstance().getReference("salads");

                mFirebaseDescription = FirebaseDatabase.getInstance().getReference("description");
                mFirebaseDatabase.removeValue();
                mFirebaseDescription.removeValue();
                addArtist();
                startActivity(new Intent(SplashScreen.this, Dashboard.class));
            }
        });

    }


    private void addArtist() {
        //getting the values to save

        String name = "Beetroot";
        int idd = R.drawable.be;
        String id = mFirebaseDatabase.push().getKey();
        SaladModel artist = new SaladModel();
        artist.setId(id);
        artist.setImageId(idd);
        artist.setTxt(name);
        mFirebaseDatabase.child(id).setValue(artist);
        mFirebaseDescription = FirebaseDatabase.getInstance().getReference("description").child(id);
        name = "Beetroot";
        idd = R.drawable.be;
        SaladDescription description = new SaladDescription();
        description.setId(id);
        description.setImageId(idd);
        description.setTxt(name);
        description.setTime("20");
        ArrayList<String> strings = new ArrayList<>();
        strings.add("3 medium beetroots");
        strings.add("1/2 lemon");
        strings.add("1 medium onion");
        strings.add("A small bunch of Parsley");
        strings.add("2 tbs of olive oil");
        strings.add("1 tbs of lemon juice");
        strings.add("1/4 tsp cumin");
        strings.add("Pinch of salt");
        strings.add("Pinch of pepper");
        description.setItemsRequired(strings);
        strings = new ArrayList<>();
        strings.add("Fill a pot with water and place the beetroots on it with 1/2 lemon. Place over medium heat and let the beetroots cook in boiling water for 45-60 minutes until tender. The skin should come off ");
        strings.add("Once done, drain the beetroots and let them cool down.");
        strings.add("Peel the beetroots and remove the two ends");
        strings.add("A small bunch of Parsley");
        strings.add("Slice the beetroots into small cubes and place them in a large bowl.");
        strings.add("Cut the onion, chop the parsley, and place them in a small bowl. Add the olive oil, lemon juice, cumin, salt, and pepper");
        strings.add("Add the onion mixture to the diced beetroots. Mix until combined");
        strings.add("Serve the salad at room temperature or cold. It can be eaten alone, with bread, or tossed in a garden salad.");
        description.setListProcedure(strings);
        description.setCalories("100");
        mFirebaseDescription.child(id).setValue(description);


        name = "Carrot Salad";
        idd = R.drawable.carrots;
        id = mFirebaseDatabase.push().getKey();
        artist = new SaladModel();
        artist.setId(id);
        artist.setImageId(idd);
        artist.setTxt(name);
        mFirebaseDatabase.child(id).setValue(artist);

        mFirebaseDescription = FirebaseDatabase.getInstance().getReference("description").child(id);
        name = "Carrot Salad";
        idd = R.drawable.carrots;
        description = new SaladDescription();
        description.setId(id);
        description.setTime("40");

        description.setImageId(idd);
        description.setTxt(name);
        strings = new ArrayList<>();
        strings.add("3 large carrots");
        strings.add("1/4 cup of cilantro");
        strings.add("2 garlic cloves");
        strings.add("2 tbls olive oil");
        strings.add("Juice of 1/2 lemon");
        strings.add("1/2 tsp of paprika");
        strings.add("1/2 tsp cumin");
        strings.add("1/4 tsp of chili powder");
        strings.add("Pinch of pepper");
        description.setItemsRequired(strings);
        strings = new ArrayList<>();
        strings.add("Peel the carrots and cut them into 0.5 cm / 0.2 inches thick slices");
        strings.add("In a large pot over medium heat, boil water. Place the carrot slices in the pot with 1/2 teaspoon of salt and cook for 15 to 20 minutes until tender. Drain the carrots.");
        strings.add("Finely chop the cilantro and garlic");
        strings.add("A small bunch of Parsley");
        strings.add("Heat olive oil in a medium pan over low heat. Add the garlic and cook for 2 minutes while stirring regularly");
        strings.add("Now add the lemon juice, ground paprika, ground cumin, chili powder, salt, and pepper");
        strings.add("Add chopped cilantro and mix well.");
        strings.add("Add the carrot slices, toss with the spices, and cook for another 5 minutes on low heat");
        description.setListProcedure(strings);
        description.setCalories("200");
        mFirebaseDescription.child(id).setValue(description);

        name = "Fava Beans Salad";
        idd = R.drawable.fava;
        id = mFirebaseDatabase.push().getKey();
        artist = new SaladModel();
        artist.setId(id);
        artist.setImageId(idd);
        artist.setTxt(name);
        mFirebaseDatabase.child(id).setValue(artist);
        mFirebaseDescription = FirebaseDatabase.getInstance().getReference("description").child(id);
        name = "Fava Beans Salad";
        idd = R.drawable.fava;
        description = new SaladDescription();
        description.setId(id);
        description.setTime("60");

        description.setImageId(idd);
        description.setTxt(name);
        strings = new ArrayList<>();
        strings.add("1lbs fresh fava beans");
        strings.add("1 tomato (grated)");
        strings.add("1 medium onion");
        strings.add("1/3 cup finely chopped cilantro");
        strings.add("3 tbls olive oil");
        strings.add("3 garlic cloves (crushed)");
        strings.add("1 tsp of cumin");
        strings.add("1 tsp of salt");
        strings.add("1 tsp of pepper");
        description.setItemsRequired(strings);
        strings = new ArrayList<>();
        strings.add("Fill a pot with water and place the beetroots on it with 1/2 lemon. Place over medium heat and let the beetroots cook in boiling water for 45-60 minutes until tender. The skin should come off ");
        strings.add("Once done, drain the beetroots and let them cool down.");
        strings.add("Peel the beetroots and remove the two ends");
        strings.add("A small bunch of Parsley");
        strings.add("Slice the beetroots into small cubes and place them in a large bowl.");
        strings.add("Cut the onion, chop the parsley, and place them in a small bowl. Add the olive oil, lemon juice, cumin, salt, and pepper");
        strings.add("Add the onion mixture to the diced beetroots. Mix until combined");
        strings.add("Serve the salad at room temperature or cold. It can be eaten alone, with bread, or tossed in a garden salad.");
        description.setListProcedure(strings);
        description.setCalories("600");
        mFirebaseDescription.child(id).setValue(description);

        name = "Lentil Salad";
        idd = R.drawable.lentils;
        id = mFirebaseDatabase.push().getKey();
        artist = new SaladModel();
        artist.setId(id);
        artist.setImageId(idd);
        artist.setTxt(name);
        mFirebaseDatabase.child(id).setValue(artist);
        mFirebaseDescription = FirebaseDatabase.getInstance().getReference("description").child(id);
        name = "Lentil Salad";
        idd = R.drawable.lentils;
        description = new SaladDescription();
        description.setId(id);
        description.setImageId(idd);
        description.setTxt(name);
        description.setTime("55");

        strings = new ArrayList<>();
        strings.add("3 medium beetroots");
        strings.add("1/2 lemon");
        strings.add("1 medium onion");
        strings.add("A small bunch of Parsley");
        strings.add("2 tbs of olive oil");
        strings.add("1 tbs of lemon juice");
        strings.add("1/4 tsp cumin");
        strings.add("Pinch of salt");
        strings.add("Pinch of pepper");
        description.setItemsRequired(strings);
        strings = new ArrayList<>();
        strings.add("Fill a pot with water and place the beetroots on it with 1/2 lemon. Place over medium heat and let the beetroots cook in boiling water for 45-60 minutes until tender. The skin should come off ");
        strings.add("Once done, drain the beetroots and let them cool down.");
        strings.add("Peel the beetroots and remove the two ends");
        strings.add("A small bunch of Parsley");
        strings.add("Slice the beetroots into small cubes and place them in a large bowl.");
        strings.add("Cut the onion, chop the parsley, and place them in a small bowl. Add the olive oil, lemon juice, cumin, salt, and pepper");
        strings.add("Add the onion mixture to the diced beetroots. Mix until combined");
        strings.add("Serve the salad at room temperature or cold. It can be eaten alone, with bread, or tossed in a garden salad.");
        description.setListProcedure(strings);
        description.setCalories("100");
        mFirebaseDescription.child(id).setValue(description);

        name = "Rice Tuna Salad";
        idd = R.drawable.rice;
        id = mFirebaseDatabase.push().getKey();
        artist = new SaladModel();
        artist.setId(id);

        artist.setImageId(idd);
        artist.setTxt(name);
        mFirebaseDatabase.child(id).setValue(artist);
        mFirebaseDescription = FirebaseDatabase.getInstance().getReference("description").child(id);
        name = "Rice Tuna Salad";
        idd = R.drawable.rice;
        description = new SaladDescription();
        description.setId(id);
        description.setTime("28");

        description.setImageId(idd);
        description.setTxt(name);
        strings = new ArrayList<>();
        strings.add("3 medium beetroots");
        strings.add("1/2 lemon");
        strings.add("1 medium onion");
        strings.add("A small bunch of Parsley");
        strings.add("2 tbs of olive oil");
        strings.add("1 tbs of lemon juice");
        strings.add("1/4 tsp cumin");
        strings.add("Pinch of salt");
        strings.add("Pinch of pepper");
        description.setItemsRequired(strings);
        strings = new ArrayList<>();
        strings.add("Fill a pot with water and place the beetroots on it with 1/2 lemon. Place over medium heat and let the beetroots cook in boiling water for 45-60 minutes until tender. The skin should come off ");
        strings.add("Once done, drain the beetroots and let them cool down.");
        strings.add("Peel the beetroots and remove the two ends");
        strings.add("A small bunch of Parsley");
        strings.add("Slice the beetroots into small cubes and place them in a large bowl.");
        strings.add("Cut the onion, chop the parsley, and place them in a small bowl. Add the olive oil, lemon juice, cumin, salt, and pepper");
        strings.add("Add the onion mixture to the diced beetroots. Mix until combined");
        strings.add("Serve the salad at room temperature or cold. It can be eaten alone, with bread, or tossed in a garden salad.");
        description.setListProcedure(strings);
        description.setCalories("100");
        mFirebaseDescription.child(id).setValue(description);

        name = "Taktouka Salad";
        idd = R.drawable.tak;
        id = mFirebaseDatabase.push().getKey();
        artist = new SaladModel();
        artist.setId(id);
        artist.setImageId(idd);
        artist.setTxt(name);
        mFirebaseDatabase.child(id).setValue(artist);
        mFirebaseDescription = FirebaseDatabase.getInstance().getReference("description").child(id);
        name = "Taktouka Salad";
        idd = R.drawable.tak;
        description = new SaladDescription();
        description.setId(id);
        description.setTime("80");

        description.setImageId(idd);
        description.setTxt(name);
        strings = new ArrayList<>();
        strings.add("3 medium beetroots");
        strings.add("1/2 lemon");
        strings.add("1 medium onion");
        strings.add("A small bunch of Parsley");
        strings.add("2 tbs of olive oil");
        strings.add("1 tbs of lemon juice");
        strings.add("1/4 tsp cumin");
        strings.add("Pinch of salt");
        strings.add("Pinch of pepper");
        description.setItemsRequired(strings);
        strings = new ArrayList<>();
        strings.add("Fill a pot with water and place the beetroots on it with 1/2 lemon. Place over medium heat and let the beetroots cook in boiling water for 45-60 minutes until tender. The skin should come off ");
        strings.add("Once done, drain the beetroots and let them cool down.");
        strings.add("Peel the beetroots and remove the two ends");
        strings.add("A small bunch of Parsley");
        strings.add("Slice the beetroots into small cubes and place them in a large bowl.");
        strings.add("Cut the onion, chop the parsley, and place them in a small bowl. Add the olive oil, lemon juice, cumin, salt, and pepper");
        strings.add("Add the onion mixture to the diced beetroots. Mix until combined");
        strings.add("Serve the salad at room temperature or cold. It can be eaten alone, with bread, or tossed in a garden salad.");
        description.setListProcedure(strings);
        description.setCalories("100");
        mFirebaseDescription.child(id).setValue(description);

        name = "Cucumber Tomatoes Salad";
        idd = R.drawable.cucumbertomatoe;
        id = mFirebaseDatabase.push().getKey();
        artist = new SaladModel();
        artist.setId(id);
        artist.setImageId(idd);
        artist.setTxt(name);
        mFirebaseDatabase.child(id).setValue(artist);
        mFirebaseDescription = FirebaseDatabase.getInstance().getReference("description").child(id);
        name = "Cucumber Tomatoes Salad";
        idd = R.drawable.cucumbertomatoe;
        description = new SaladDescription();
        description.setId(id);
        description.setTime("30");

        description.setImageId(idd);
        description.setTxt(name);
        strings = new ArrayList<>();
        strings.add("3 medium beetroots");
        strings.add("1/2 lemon");
        strings.add("1 medium onion");
        strings.add("A small bunch of Parsley");
        strings.add("2 tbs of olive oil");
        strings.add("1 tbs of lemon juice");
        strings.add("1/4 tsp cumin");
        strings.add("Pinch of salt");
        strings.add("Pinch of pepper");
        description.setItemsRequired(strings);
        strings = new ArrayList<>();
        strings.add("Fill a pot with water and place the beetroots on it with 1/2 lemon. Place over medium heat and let the beetroots cook in boiling water for 45-60 minutes until tender. The skin should come off ");
        strings.add("Once done, drain the beetroots and let them cool down.");
        strings.add("Peel the beetroots and remove the two ends");
        strings.add("A small bunch of Parsley");
        strings.add("Slice the beetroots into small cubes and place them in a large bowl.");
        strings.add("Cut the onion, chop the parsley, and place them in a small bowl. Add the olive oil, lemon juice, cumin, salt, and pepper");
        strings.add("Add the onion mixture to the diced beetroots. Mix until combined");
        strings.add("Serve the salad at room temperature or cold. It can be eaten alone, with bread, or tossed in a garden salad.");
        description.setListProcedure(strings);
        description.setCalories("100");
        mFirebaseDescription.child(id).setValue(description);

        name = "Zaalouk Salad";
        idd = R.drawable.zaalouk;
        id = mFirebaseDatabase.push().getKey();
        artist = new SaladModel();
        artist.setId(id);
        artist.setImageId(idd);
        artist.setTxt(name);
        mFirebaseDatabase.child(id).setValue(artist);
        mFirebaseDescription = FirebaseDatabase.getInstance().getReference("description").child(id);
        name = "Zaalouk Salad";
        idd = R.drawable.zaalouk;
        description = new SaladDescription();
        description.setId(id);
        description.setTime("70");

        description.setImageId(idd);
        description.setTxt(name);
        strings = new ArrayList<>();
        strings.add("3 medium beetroots");
        strings.add("1/2 lemon");
        strings.add("1 medium onion");
        strings.add("A small bunch of Parsley");
        strings.add("2 tbs of olive oil");
        strings.add("1 tbs of lemon juice");
        strings.add("1/4 tsp cumin");
        strings.add("Pinch of salt");
        strings.add("Pinch of pepper");
        description.setItemsRequired(strings);
        strings = new ArrayList<>();
        strings.add("Fill a pot with water and place the beetroots on it with 1/2 lemon. Place over medium heat and let the beetroots cook in boiling water for 45-60 minutes until tender. The skin should come off ");
        strings.add("Once done, drain the beetroots and let them cool down.");
        strings.add("Peel the beetroots and remove the two ends");
        strings.add("A small bunch of Parsley");
        strings.add("Slice the beetroots into small cubes and place them in a large bowl.");
        strings.add("Cut the onion, chop the parsley, and place them in a small bowl. Add the olive oil, lemon juice, cumin, salt, and pepper");
        strings.add("Add the onion mixture to the diced beetroots. Mix until combined");
        strings.add("Serve the salad at room temperature or cold. It can be eaten alone, with bread, or tossed in a garden salad.");
        description.setListProcedure(strings);
        description.setCalories("300");
        mFirebaseDescription.child(id).setValue(description);

    }

}