package com.example.sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class insertRecord extends AppCompatActivity{
    EditText nameT, semT, regT;
//    TextView displayContent;
    Button insertB, viewB;
    SQLiteDatabase db;
    public static final String DATABASE_NAME = "DB";
    public static String TABLE_NAME = "mahnoor";
    public static final String COL_1 = "id";
    public static final String COL_2 = "name";
    public static final String COL_3 = "reg_no";
    public static final String COL_4 = "semester";
    Intent i = getIntent();

    private String name = "Mahnoor";
    private String reg_no = "FA16-BCS-008";
    private String semester = "7TH";

    String[] namegot = new String[4];
    String[] reggot = new String[4];
    String[] semestergot = new String[4];
    DatabaseHelper helper = new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_record);
        nameT = (EditText) findViewById(R.id.editText4);
        regT = (EditText) findViewById(R.id.editText5);
        semT = (EditText) findViewById(R.id.editText6);

//        displayContent = (TextView)findViewById(R.id.displayContentTextView);
//        displayContent.setVisibility(View.INVISIBLE);

        insertB = (Button) findViewById(R.id.button7);
        viewB = (Button) findViewById(R.id.button8);


        db = helper.getDB();

        insertB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId()==insertB.getId()) {
                    name = nameT.getText().toString();
                    reg_no = regT.getText().toString();
                    semester = semT.getText().toString();

                    insertRecord();
                }
                if(view.getId()==viewB.getId()){

                    //record();
                }

            }

        });

        viewB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewRecord();
            }
        });


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
            semestergot[row] = cursor.getString(cursor.getColumnIndexOrThrow("semester"));
            row++;

        }while(cursor.moveToNext() && row<count);

        String line = "\n-----------------------\n";
        String content = "Name: "+namegot[0]+"\nReg No: "+reggot[0]+"\nSemester: "+semestergot[0];
        for (int i=1; i<count; i++) {
            content = content +  line + "Name: "+namegot[i]+"\nReg No: "+reggot[i]+"\nSemester: "+semestergot[i];
        }
//        displayContent.setVisibility(View.VISIBLE);
//        displayContent.setText(content);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(insertRecord.this);
        alertDialog.setTitle("Records:"+TABLE_NAME);
        alertDialog.setMessage(content);
        alertDialog.setPositiveButton("OK",null);
        alertDialog.show();
    }

    public Cursor getRecords(){
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor res = db.rawQuery(selectQuery, null);
        return res;
    }

    public boolean insertRecord() {

//        db.execSQL("INSERT INTO car VALUES ('"+make+"','"+model+"','"+engine+"') ");
//        db.execSQL("INSERT INTO car(" + COL_2 + ","+ COL_3 +","+ COL_4 + ") VALUES ('"+make+"','"+model+"','"+engine+"') ;");

        TABLE_NAME = createTable.TABLE_NAME;

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, reg_no);
        contentValues.put(COL_4, semester);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else {
            Toast.makeText(insertRecord.this, "Record added: \n" + name + "\n" + reg_no + "\n" + semester, Toast.LENGTH_SHORT).show();
            return true;
        }
    }
//    public void record() {
//
////        db.execSQL("INSERT INTO car VALUES ('"+make+"','"+model+"','"+engine+"') ");
////        db.execSQL("INSERT INTO car(" + COL_2 + ","+ COL_3 +","+ COL_4 + ") VALUES ('"+make+"','"+model+"','"+engine+"') ;");
//
//
//
//        TABLE_NAME = i.getStringExtra("name");
//        Intent in = new Intent(this, viewRecord.class);
//        in.putExtra("name", TABLE_NAME);
//
//        startActivity(in);
//    }
}
