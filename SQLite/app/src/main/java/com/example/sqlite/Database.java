package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Database extends AppCompatActivity implements View.OnClickListener{
    Intent i;

    public static final String DATABASE_NAME = "DB";
    Button createB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);


        createB = (Button) findViewById(R.id.button4);


        createB.setOnClickListener(this);

    }




    @Override
    public void onClick(View view) {



        if(view.getId()==createB.getId()){
            i= new Intent(this,createTable.class);
            startActivity(i);

        }
}
}
