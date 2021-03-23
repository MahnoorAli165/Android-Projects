package com.example.task_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class TaskActivity extends AppCompatActivity implements View.OnClickListener {
    EditText e1, e2, e3;
    RadioGroup radioGroup;
    RadioButton b1, b2, b3,b4,b5,b6,b7;
    Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        initializeWidgets();

    }

    private void initializeWidgets() {
        b1 = (RadioButton) findViewById(R.id.radioButtonSat);
        b2 = (RadioButton) findViewById(R.id.radioButtonSun);
        b3 = (RadioButton) findViewById(R.id.radioButtonMon);
        b4 = (RadioButton) findViewById(R.id.radioButtonTue);
        b5 = (RadioButton) findViewById(R.id.radioButtonWed);
        b6 = (RadioButton) findViewById(R.id.radioButtonThur);
        b7 = (RadioButton) findViewById(R.id.radioButtonFri);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        e1 = (EditText) findViewById(R.id.editTextId);
        e2 = (EditText) findViewById(R.id.editTextName);
        e3 = (EditText) findViewById(R.id.editTextDesc);

        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == buttonSave.getId()) {
            Intent intent = new Intent();
            intent.putExtra("id", Integer.parseInt(e1.getText().toString()));
            intent.putExtra("name", e2.getText().toString());
            intent.putExtra("desc", e3.getText().toString());
            int id = radioGroup.getCheckedRadioButtonId();
            if(id == b1.getId()) {
                intent.putExtra("date", b1.getText().toString());
            } else if(id == b2.getId()) {
                intent.putExtra("date", b2.getText().toString());
            } else if(id == b3.getId()) {
                intent.putExtra("date", b3.getText().toString());
            }
            else if(id == b4.getId()) {
                intent.putExtra("date", b4.getText().toString());
            }
            else if(id == b5.getId()) {
                intent.putExtra("date", b5.getText().toString());
            }
            else if(id == b6.getId()) {
                intent.putExtra("date", b6.getText().toString());
            }
            else if(id == b7.getId()) {
                intent.putExtra("date", b7.getText().toString());
            }
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }
}
