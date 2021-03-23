package com.example.labsessional1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class displayImg extends AppCompatActivity {
    ImageView im;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_img);
        im = (ImageView) findViewById(R.id.imageView3);
        Intent i = getIntent();
        i.getStringExtra("stone");
        im.setImageResource(R.drawable.stone);


        i.getStringExtra("leave");
        im.setImageResource(R.drawable.leave);

        i.getStringExtra("waterfall");
        im.setImageResource(R.drawable.waterfall);

        i.getStringExtra("hut");
        im.setImageResource(R.drawable.hut);
        }

    }

