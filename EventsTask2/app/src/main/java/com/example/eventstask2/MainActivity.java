package com.example.eventstask2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {
    RadioButton rbtn1, rbtn2, rbtn3, rbtn4;
    Spinner spn;
    ImageView img;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rbtn1 = (RadioButton) findViewById(R.id.rbtn);
        rbtn2 = (RadioButton) findViewById(R.id.rbtn1);
        rbtn3 = (RadioButton) findViewById(R.id.rbtn2);
        rbtn4 = (RadioButton) findViewById(R.id.rbtn3);
        spn = (Spinner) findViewById(R.id.spinner);
        img = (ImageView) findViewById(R.id.imageView);

//        rbtn1.setOnClickListener(this);
//        rbtn2.setOnClickListener(this);
//        rbtn3.setOnClickListener(this);
//        rbtn4.setOnClickListener(this);
        spn.setOnItemSelectedListener(this);






    }

//    @Override
//    public void onClick(View view) {
//        if(view.getId()==rbtn1.getId()){
//            img.setImageResource(R.drawable.mushroom);
//            rbtn1.setChecked(true);
//            rbtn2.setChecked(false);
//            rbtn3.setChecked(false);
//            rbtn4.setChecked(false);
//
//
//        }
//        if(view.getId()==rbtn2.getId()) {
//            img.setImageResource(R.drawable.tree);
//            rbtn1.setChecked(false);
//            rbtn2.setChecked(true);
//            rbtn3.setChecked(false);
//            rbtn4.setChecked(false);
//        }
//        if(view.getId()==rbtn3.getId()) {
//            img.setImageResource(R.drawable.winter);
//            rbtn1.setChecked(false);
//            rbtn2.setChecked(false);
//            rbtn3.setChecked(true);
//            rbtn4.setChecked(false);
//        }
//        if(view.getId()==rbtn4.getId()) {
//            img.setImageResource(R.drawable.stone);
//            rbtn1.setChecked(false);
//            rbtn2.setChecked(false);
//            rbtn3.setChecked(false);
//            rbtn4.setChecked(true);
//        }



    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position,long id) {
        String a =spn.getSelectedItem().toString();
        switch(a){
            case "Mushroom":
                img.setImageResource(R.drawable.mushroom);
                rbtn1.setChecked(true);
                rbtn2.setChecked(false);
                rbtn3.setChecked(false);
                rbtn4.setChecked(false);
                break;
            case "Tree":
                img.setImageResource(R.drawable.tree);
                rbtn1.setChecked(false);
                rbtn2.setChecked(true);
                rbtn3.setChecked(false);
                rbtn4.setChecked(false);
                break;
            case "Winter":
                img.setImageResource(R.drawable.winter);
                rbtn1.setChecked(false);
                rbtn2.setChecked(false);
                rbtn3.setChecked(true);
                rbtn4.setChecked(false);
                break;
            case "Stone":
                img.setImageResource(R.drawable.stone);
                rbtn1.setChecked(false);
                rbtn2.setChecked(false);
                rbtn3.setChecked(false);
                rbtn4.setChecked(true);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}

