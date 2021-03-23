package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    TextView contentT;
    EditText filenameT, inputT;
    Button writeB, readB, nextB;
    String filename = "test.txt";
    String input = "Default input: Hello World";
    String content = "Default content: World Hello";
    //    RelativeLayout layout;
    boolean contentDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentT = (TextView)findViewById(R.id.textView4);
        filenameT = (EditText)findViewById(R.id.editText);
        inputT = (EditText)findViewById(R.id.editText2);
        writeB = (Button)findViewById(R.id.button);
        readB = (Button)findViewById(R.id.button3);
        nextB = (Button)findViewById(R.id.button2);
//        layout = (RelativeLayout) findViewById(R.id.tapCloseLayout);


        writeB.setClickable(true);
        readB.setVisibility(View.INVISIBLE);

        writeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!filenameT.getText().toString().isEmpty())
                    filename = filenameT.getText().toString();
                if (!inputT.getText().toString().isEmpty())
                    input = inputT.getText().toString();
                if (!filenameT.getText().toString().isEmpty() && !inputT.getText().toString().isEmpty())
                    Toast.makeText(MainActivity.this, "Written to file\n"+filename, Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Default file would be read.", Toast.LENGTH_SHORT).show();


                PrintStream writer = null;
                try {
                    writer = new PrintStream(openFileOutput(filename, MODE_PRIVATE));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                writer.println(input);

                writer.close();

                readB.setVisibility(View.VISIBLE);

            }
        });

        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Database.class);
                startActivity(i);
            }
        });

        readB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Scanner reader = null;
                try {

                    reader = new Scanner(openFileInput(filename));

                    //hello.txt is present in res/raw/ directory
                    //getResources().openRawResource(R.raw.hello);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "File not found", Toast.LENGTH_SHORT).show();
                }
                String content  = "";
                while (reader.hasNextLine()){
                    String line = reader.nextLine();
                    content += line;
                }

                if (content.isEmpty()){
                    content = "File is empty";
                }
                contentT.setText("Filename: " + filename + "\nContent: " + content);
                contentT.postDelayed(new Runnable() {
                    public void run() {
                        contentT.setVisibility(View.INVISIBLE);
                    }
                }, 3000);
                reader.close();
                contentDisplayed = true;
            }
        });

//        layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (contentDisplayed) {
//                    contentT.setVisibility(View.INVISIBLE);
//                    contentDisplayed = false;
//                }
//            }
//        });

    }

    private boolean isTXT(File file){
        boolean is = false;
        if(file.getName().endsWith(".txt")){
            is = true;
            Log.e("", "txt files -->"+is);
        }
        return is;
    }
}