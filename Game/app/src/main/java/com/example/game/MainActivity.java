package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btn0, btn1, btn2, btn3;
    private TextView txt0,txt1,txt2;
    private EditText num;
    private int count=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn0 = (Button) findViewById(R.id.button0);
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        txt0 = (TextView) findViewById(R.id.txt1);
        txt1 = (TextView) findViewById(R.id.txt2);
        txt2 = (TextView) findViewById(R.id.textView);
        num = (EditText) findViewById(R.id.editText);

        btn0.setText(odd_random()+"");
        btn1.setText(odd_random()+"");
        btn2.setText(odd_random()+"");

        num.setText("0");



        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }
         private int odd_random()
         {
            int x = (int) (Math.random() * 100);
            x = x * 2 + 1;
            return x;


          }

          private boolean Prime_Number(int n) {
              boolean isPrime = true;
              int temp;
              for (int i = 2; i <= n/2 ; i++) {
                  temp = n % i;
                  if (temp == 0) {
                      isPrime = false;
                      return isPrime;
                  }
              }
              return isPrime;
          }

        public void onClick (View view){
            if (view.getId() == btn0.getId()) {
                if (Prime_Number(Integer.parseInt(btn0.getText().toString()))) {
                        count= count+1;
                        num.setText(count+"");
                    } else {
                        count= count-1;
                        num.setText(count+"");
                    }
                    btn0.setText(odd_random() + "");
                    btn1.setText(odd_random() + "");
                    btn2.setText(odd_random() + "");

                }
            if (view.getId() == btn1.getId()) {

                    if (Prime_Number(Integer.parseInt(btn1.getText().toString()))) {
                        count++;
                        num.setText(count+"");
                    } else {
                        count--;
                        num.setText(count+"");
                    }
                    btn0.setText(odd_random() + "");
                    btn1.setText(odd_random() + "");
                    btn2.setText(odd_random() + "");


                }

            if (view.getId() == btn2.getId()) {

                    if (Prime_Number(Integer.parseInt(btn2.getText().toString()))) {

                            count++;
                            num.setText(count+"");
                        } else {
                            count--;
                            num.setText(count+"");
                        }
                        btn0.setText(odd_random() + "");
                        btn1.setText(odd_random() + "");
                        btn2.setText(odd_random() +"");


            }
            if(view.getId()==btn3.getId()){

                btn0.setText(odd_random()+"");
                btn1.setText(odd_random()+"");
                btn2.setText(odd_random()+"");


            }


        }
    }



