package com.example.nehaniphadkar.contacts;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import ooo.oxo.library.widget.TouchImageView;

public class ImageShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_image_show);
        Bundle extras = getIntent().getExtras();

        byte[] bytes=extras.getByteArray("bytes");
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        TouchImageView imageView=(TouchImageView) findViewById(R.id.image);
        imageView.setImageBitmap(bitmap);


    }
}
