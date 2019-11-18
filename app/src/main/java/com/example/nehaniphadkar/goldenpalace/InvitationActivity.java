package com.example.nehaniphadkar.goldenpalace;

import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.nehaniphadkar.goldenpalace.navigationdrawer.BaseActivity;

public class InvitationActivity extends BaseActivity implements View.OnClickListener {
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation);
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items); // load
        // titles
        // from
        // strings.xml

        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);// load icons from
        // strings.xml

        set(navMenuTitles, navMenuIcons);
        init();
    }

    private void init() {
        LinearLayout facebook = (LinearLayout) findViewById(R.id.facebook);
        LinearLayout twitter = (LinearLayout) findViewById(R.id.twitter);
        LinearLayout instagram = (LinearLayout) findViewById(R.id.instagram);
        LinearLayout email = (LinearLayout) findViewById(R.id.email);
        facebook.setOnClickListener(this);
        twitter.setOnClickListener(this);
        instagram.setOnClickListener(this);
        email.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.facebook:
                Uri uri = Uri.parse("https://www.facebook.com/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);


                break;
            case R.id.twitter:
                Uri urit = Uri.parse("https://twitter.com/"); // missing 'http://' will cause crashed
                Intent intentt = new Intent(Intent.ACTION_VIEW, urit);
                startActivity(intentt);

                break;
            case R.id.instagram:
                Uri uriff = Uri.parse("https://www.instagram.com/"); // missing 'http://' will cause crashed
                Intent intenhht = new Intent(Intent.ACTION_VIEW, uriff);
                startActivity(intenhht);


                break;
            case R.id.email:
                Uri urim = Uri.parse("https://mail.google.com/"); // missing 'http://' will cause crashed
                Intent intentm = new Intent(Intent.ACTION_VIEW, urim);
                startActivity(intentm);


                break;
        }
    }
}
