package com.example.lab4;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_sensor = null;
    private Button btn_listView = null;
    private Button btn_phoneBook = null;

    private TextView tv_acc = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_listView = findViewById(R.id.btn_listView);
        btn_listView.setOnClickListener(this);
        btn_sensor = findViewById(R.id.btn_sensors);
        btn_sensor.setOnClickListener(this);
        btn_phoneBook = findViewById(R.id.btn_phoneBook);
        btn_phoneBook.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        if( v == btn_sensor){
            Intent intent = new Intent(this, sensors.class);
            startActivity(intent);

        }
        if( v == btn_phoneBook ){
            Intent intent = new Intent(this, PhoneBook.class);
            startActivity(intent);
        }
        if( v == btn_listView ){
            Intent intent = new Intent(this, list_view.class);
            startActivity(intent);
        }

    }

}