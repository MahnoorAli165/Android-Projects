package com.example.storage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SQLite extends AppCompatActivity {

    TextView contentT;
    EditText dbnameT, makeT, modelT, engineT, deleteT, tableT;
    Button enterB, createB, insertB, viewB, deleteB;
    SQLiteDatabase db;

    LinearLayout layout0, layout1, layout2, layout3;

    String[] makegot = new String[4];
    String[] modelgot = new String[4];
    String[] enginegot = new String[4];

    private String make = "Ferrari";
    private String model = "450e";
    private String engine = "1800cc";

    public static final String DATABASE_NAME = "DB";
    public static String TABLE_NAME = "usama";
    public static final String COL_1 = "id";
    public static final String COL_2 = "make";
    public static final String COL_3 = "model";
    public static final String COL_4 = "engine";

    String lastPressed = "create";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        contentT = (TextView)findViewById(R.id.contentT);
        dbnameT = (EditText)findViewById(R.id.value);
        makeT = (EditText)findViewById(R.id.makeT);
        modelT = (EditText)findViewById(R.id.modelT);
        engineT = (EditText)findViewById(R.id.engineT);
        deleteT = (EditText)findViewById(R.id.deleteT);
        tableT = (EditText)findViewById(R.id.tableT);
        enterB = (Button)findViewById(R.id.enterB);
        insertB = (Button)findViewById(R.id.insertB);
        viewB = (Button)findViewById(R.id.viewB);
        createB = (Button)findViewById(R.id.createB);
        deleteB = (Button)findViewById(R.id.deleteB);
        layout0 = (LinearLayout)findViewById(R.id.layout0);
        layout1 = (LinearLayout)findViewById(R.id.layout1);
        layout2 = (LinearLayout)findViewById(R.id.layout2);
        layout3 = (LinearLayout)findViewById(R.id.layout3);

        layout0.setVisibility(View.INVISIBLE);
        layout1.setVisibility(View.INVISIBLE);
        layout2.setVisibility(View.INVISIBLE);
        layout3.setVisibility(View.INVISIBLE);
        enterB.setVisibility(View.INVISIBLE);
        deleteB.setVisibility(View.INVISIBLE);
        deleteT.setVisibility(View.INVISIBLE);
        tableT.setVisibility(View.INVISIBLE);

        DatabaseHelper helper = new DatabaseHelper(this);
        db = helper.getDB();

        createB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout0.setVisibility(View.VISIBLE);
                layout1.setVisibility(View.INVISIBLE);
                layout2.setVisibility(View.INVISIBLE);
                layout3.setVisibility(View.INVISIBLE);
                enterB.setVisibility(View.VISIBLE);
                contentT.setVisibility(View.INVISIBLE);
                lastPressed = "create";
            }
        });

        insertB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout0.setVisibility(View.INVISIBLE);
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.VISIBLE);
                layout3.setVisibility(View.VISIBLE);
                enterB.setVisibility(View.VISIBLE);
                contentT.setVisibility(View.INVISIBLE);
                tableT.setVisibility(View.VISIBLE);
                lastPressed = "insert";
            }
        });

        viewB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout0.setVisibility(View.INVISIBLE);
                layout1.setVisibility(View.INVISIBLE);
                layout2.setVisibility(View.INVISIBLE);
                layout3.setVisibility(View.INVISIBLE);
                enterB.setVisibility(View.INVISIBLE);
                contentT.setVisibility(View.VISIBLE);
//                deleteB.setVisibility(View.VISIBLE);
//                deleteT.setVisibility(View.VISIBLE);
                lastPressed = "view";
                viewRecord();
            }
        });

        enterB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lastPressed.equalsIgnoreCase("create")){
                    createDB();
                    TABLE_NAME = dbnameT.getText().toString();
                    createTable();
                }
                else if (lastPressed.equalsIgnoreCase("insert")){
                    make = makeT.getText().toString();
                    model = modelT.getText().toString();
                    engine = engineT.getText().toString();
                    insertRecord();
                }

            }
        });

        deleteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteRecord(deleteT.getText().toString());
            }
        });
    }

    public void createDB(){
        db = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Toast.makeText(SQLite.this, "DB created: "+DATABASE_NAME, Toast.LENGTH_SHORT).show();

    }

    public void createTable(){
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, make TEXT, model TEXT, engine TEXT)");
        Toast.makeText(SQLite.this, "Table created: "+TABLE_NAME, Toast.LENGTH_SHORT).show();
    }

    public boolean insertRecord(){

//        db.execSQL("INSERT INTO car VALUES ('"+make+"','"+model+"','"+engine+"') ");
//        db.execSQL("INSERT INTO car(" + COL_2 + ","+ COL_3 +","+ COL_4 + ") VALUES ('"+make+"','"+model+"','"+engine+"') ;");

        TABLE_NAME = tableT.getText().toString();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, make);
        contentValues.put(COL_3, model);
        contentValues.put(COL_4, engine);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;
        else {
            Toast.makeText(SQLite.this, "Record added: \n"+make+"\n"+model+"\n"+engine, Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    public void viewRecord(){
        Cursor cursor = getRecords();
//        Cursor cursor = getCertainRecord("aedjwq");
        int count = cursor.getColumnCount();

        int row = 0;
        cursor.moveToFirst();

        do{

            makegot[row] = cursor.getString(cursor.getColumnIndexOrThrow("make"));
            modelgot[row] = cursor.getString(cursor.getColumnIndexOrThrow("model"));
            enginegot[row] = cursor.getString(cursor.getColumnIndexOrThrow("engine"));
            row++;

        }while(cursor.moveToNext() && row<count);

        String line = "\n-----------------------\n";
        String content = "Make: "+makegot[0]+"\nModel: "+modelgot[0]+"\nEngine: "+enginegot[0];
        for (int i=1; i<count; i++) {
            content = content +  line + "Make: " + makegot[i] + "\nModel: " + modelgot[i] + "\nEngine: " + enginegot[i];
        }
        contentT.setText(content);
    }

    public Cursor getCertainRecord(String name){
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " where model like '" + name + "'";
        Cursor res = db.rawQuery(selectQuery, null);
        return res;
    }

    public Cursor getRecords(){
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor res = db.rawQuery(selectQuery, null);
        return res;
    }

    public Cursor deleteRecord(String modelValue){
        String selectQuery = "DELETE FROM " + TABLE_NAME + " WHERE model like "+ modelValue;
        Cursor res = db.rawQuery(selectQuery, null);
        viewRecord();
        return res;
    }

}
