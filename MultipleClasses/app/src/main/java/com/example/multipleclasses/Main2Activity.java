package com.example.multipleclasses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    Button bck;
    TextView txt;
    EditText t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bck = (Button) findViewById(R.id.button6);
        txt = (TextView) findViewById(R.id.textView);
        t1 = (EditText) findViewById(R.id.editText4);

        Intent in = getIntent();
        int a =in.getIntExtra("result",0);
        t1.setText(a+"");


        bck.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()== bck.getId()){
            Intent in = new Intent();
            in.putExtra("Calculation",Integer.parseInt(t1.getText().toString()));
            setResult(RESULT_OK,in);
            finish();

        }

    }
}
