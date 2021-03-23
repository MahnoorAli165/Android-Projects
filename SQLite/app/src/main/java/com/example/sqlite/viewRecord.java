package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class viewRecord extends AppCompatActivity {
    TextView contentT;
    SQLiteDatabase db;
    Intent i = getIntent();
    String[] namegot = new String[4];
    String[] reggot = new String[4];
    String[] semgot = new String[4];
    public static final String DATABASE_NAME = "DB";
    public static String TABLE_NAME = "mahnoor";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record);
        contentT = (TextView)findViewById(R.id.textView12);
        DatabaseHelper helper = new DatabaseHelper(this);
        db = helper.getDB();
    }
    public void viewRecord(){
        Cursor cursor = getRecords();
//        Cursor cursor = getCertainRecord("aedjwq");
        int count = cursor.getColumnCount();

        int row = 0;
        cursor.moveToFirst();

        do{

            namegot[row] = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            reggot[row] = cursor.getString(cursor.getColumnIndexOrThrow("reg_no"));
            semgot[row] = cursor.getString(cursor.getColumnIndexOrThrow("semester"));
            row++;

        }while(cursor.moveToNext() && row<count);

        String line = "\n-----------------------\n";
        String content = "Name: "+namegot[0]+"\nRegisteration number: "+reggot[0]+"\nSemester: "+semgot[0];
        for (int i=1; i<count; i++) {
            content = content +  line + "Name: "+namegot[i]+"\nRegisteration number: "+reggot[i]+"\nSemester: "+semgot[i];;
        }
        contentT.setText(content);
    }
    public Cursor getRecords(){
        TABLE_NAME  = i.getStringExtra("name");
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor res = db.rawQuery(selectQuery, null);
        return res;
    }
}
