package com.example.multipleclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQ_CODE = 123;

    Button btn1,btn2,btn3,btn4;
    EditText t1,t2,t3;
    TextView txt0,txt1,txt2,txt3,txt4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt0 = (TextView) findViewById(R.id.textView3);
        txt1 = (TextView) findViewById(R.id.textView4);
        txt2 = (TextView) findViewById(R.id.textView5);
        txt3 = (TextView) findViewById(R.id.textView6);
        txt4 = (TextView) findViewById(R.id.textView7);




        btn1 = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button4);
        btn4 = (Button) findViewById(R.id.button5);

        t1 = (EditText) findViewById(R.id.editText);
        t2 = (EditText) findViewById(R.id.editText2);
        t3 = (EditText) findViewById(R.id.editText3);


        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        Intent in = new Intent(this, Main2Activity.class);
        int val1 = Integer.parseInt(t1.getText().toString());
        int val2 = Integer.parseInt(t2.getText().toString());
        int cal = 0;
        if (view.getId() == btn1.getId()) {
            cal = val1 + val2;
            in.putExtra("result", cal);
            startActivityForResult(in, REQ_CODE);
        }
        else if (view.getId() == btn2.getId()) {
            cal = val1 - val2;
            in.putExtra("result", cal);
            startActivityForResult(in, REQ_CODE);
        }
        else if (view.getId() == btn3.getId()) {
            cal = val1 * val2;
            in.putExtra("result", cal);
            startActivityForResult(in, REQ_CODE);
        }
        else if (view.getId() == btn4.getId()) {
            cal = val1 / val2;
            in.putExtra("result", cal);
            startActivityForResult(in, REQ_CODE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode,intent);
        if (requestCode == REQ_CODE) {
                    int data = intent.getIntExtra("Calculation", 0);
                    t3.setText(data+"");


        }
    }

}
