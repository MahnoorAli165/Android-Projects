package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class createTable extends AppCompatActivity {
    EditText createT;
    Button enterB;
    public static final String DATABASE_NAME = "DB";
    public static String TABLE_NAME = "mahnoor";
    SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_table);
        createT = (EditText) findViewById(R.id.editText3);

        enterB = (Button) findViewById(R.id.button5);
        DatabaseHelper helper = new DatabaseHelper(this);
        db = helper.getDB();


        enterB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == enterB.getId()) {
                    createDB();
                    TABLE_NAME = createT.getText().toString();

                    createTable();



                }



            }
        });
    }
        public void createDB() {
            db = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
            Toast.makeText(createTable.this, "DB created: " + DATABASE_NAME, Toast.LENGTH_SHORT).show();
        }

        public void createTable () {
            db.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, reg_no TEXT, semester TEXT)");
            Toast.makeText(createTable.this, "Table created: " + TABLE_NAME, Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, insertRecord.class);

            startActivity(i);
        }

    }


