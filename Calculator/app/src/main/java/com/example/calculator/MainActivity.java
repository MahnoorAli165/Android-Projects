package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    private Button button0, button1, button2,button3, button4, button5,button6,
            button7, button8, button9, Add, Sub,
            Multiply,  buttonC, Equal, Divide,backspace;
    private EditText output;

    private boolean bAdd, bSub, bMul, bDivide;

    float value1, value2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        backspace = (Button) findViewById(R.id.backspace);
        Add = (Button) findViewById(R.id.Add);
        Sub = (Button) findViewById(R.id.Sub);
        Multiply = (Button) findViewById(R.id.Mul);
        Divide = (Button) findViewById(R.id.Div);
        buttonC = (Button) findViewById(R.id.buttonC);
        Equal = (Button) findViewById(R.id.Equal);
        output = (EditText) findViewById(R.id.editText);

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                output.setText(output.getText()+"0");
            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                output.setText(output.getText() + "1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                output.setText(output.getText() + "2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                output.setText(output.getText() + "3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                output.setText(output.getText() + "4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                output.setText(output.getText() + "5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                output.setText(output.getText() + "6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                output.setText(output.getText() + "7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                output.setText(output.getText() + "8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                output.setText(output.getText() + "9");
            }
        });



        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    value1 = Float.parseFloat(output.getText() + "");
                    bAdd = true;
                    output.setText(null);
                }
                catch (Exception e) {
                    // Handle the error/exceptio
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                }

            }
        });

        Sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    value1 = Float.parseFloat(output.getText() + "");
                    bSub = true;
                    output.setText(null);
                }
                catch (Exception e) {
                    // Handle the error/exceptio
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            }
        });

        Multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    value1 = Float.parseFloat(output.getText() + "");
                    bMul = true;
                    output.setText(null);
                }
                catch (Exception e) {
                    // Handle the error/exceptio
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            }
        });

        Divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    value1 = Float.parseFloat(output.getText() + "");
                    bDivide = true;
                    output.setText(null);
                }
                catch (Exception e) {
                    // Handle the error/exceptio
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            }
        });

        Equal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {


                    value2 = Float.parseFloat(output.getText() + "");


                    if (bAdd == true) {
                        output.setText(value1 + value2 + "");
                        bAdd = false;
                    }

                    if (bSub == true) {
                        output.setText(value1 - value2 + "");
                        bSub = false;
                    }

                    if (bMul == true) {
                        output.setText(value1 * value2 + "");
                        bMul = false;
                    }

                    if (bDivide == true) {
                        output.setText(value1 / value2 + "");
                        bDivide = false;
                    }
                }
                catch (Exception e)
                {
                        // Handle the error/exception
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                }

            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                output.setText("");
            }
        });

        backspace.setOnClickListener(new             View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str = output.getText().toString();
                if (str.length() >= 1) {
                    str = str.substring(0, str.length() - 1);
                    output.setText(str);
                } else if (str.length() <= 1) {
                    output.setText("0");
                }
            }
            });

        }
    }

