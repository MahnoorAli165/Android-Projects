package com.example.labsessional1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SMSView extends AppCompatActivity {
    TextView txt1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smsview);

        txt1 = (TextView) findViewById(R.id.textView2);
        Intent i= getIntent();
        i.getStringExtra("msg");

    }
}
