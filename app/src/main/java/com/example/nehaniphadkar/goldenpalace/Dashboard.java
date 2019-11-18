package com.example.nehaniphadkar.goldenpalace;

import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.nehaniphadkar.goldenpalace.navigationdrawer.BaseActivity;

public class Dashboard extends BaseActivity implements View.OnClickListener{

    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items); // load
        // titles
        // from
        // strings.xml

        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);// load icons from
        // strings.xml

        set(navMenuTitles, navMenuIcons);
        initUI();
    }

    private void initUI() {
        LinearLayout recipies=(LinearLayout)findViewById(R.id.recipies);
        LinearLayout stores=(LinearLayout)findViewById(R.id.stores);
        LinearLayout fourums=(LinearLayout)findViewById(R.id.fourums);
        LinearLayout invitations=(LinearLayout)findViewById(R.id.invitations);

        recipies.setOnClickListener(this);
        stores.setOnClickListener(this);
        fourums.setOnClickListener(this);
        invitations.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.fourums:
                startActivity(new Intent(this,FourumActivity.class));
                break;
            case R.id.recipies:
                startActivity(new Intent(this,RecipiesActivity.class));
                break;
            case R.id.invitations:
                startActivity(new Intent(this,InvitationActivity.class));
                break;
            case R.id.stores:
                Intent i = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.amazon.com/slp/middle-eastern-grocery/qsvahxrgm38u4s6"));
                startActivity(i);                break;
        }

    }
}
