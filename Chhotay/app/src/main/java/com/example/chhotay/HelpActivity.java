package com.example.chhotay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.AppBarLayout;

public class HelpActivity extends AppCompatActivity {
    private AppBarLayout toolBar;
    private static final String TAG = "Help Activity";
    Intent intent;
    TextView q1,a1,q2,a2;
    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        toolBar = (AppBarLayout) findViewById(R.id.order_received_toolbar);
        q1 = (TextView) findViewById(R.id.q1);
        a1 = (TextView) findViewById(R.id.a1);
        q2 = (TextView) findViewById(R.id.q2);
        a2 =(TextView) findViewById(R.id.a2);
        q1.setText("How can I check  my current order(s)?");
        q2.setText("How can I change password?");
        a1.setText("fdjsfdfjhfjfgh");
        a2.setText("fgjdgbdfjghdfjghdfjghdfjgh");
        Log.d(TAG, "onCreateView: started");
        ImageView iClose = (ImageView) findViewById(R.id.iCloseArrow);
        iClose.setOnClickListener(onClickListener);


        TextView fragname = (TextView) findViewById(R.id.fragname);
        fragname.setText("Help");


    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @SuppressLint("LongLogTag")
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iCloseArrow:
                    Log.d(TAG, "onClick: clicked back arrow.");
                    intent = new Intent(getApplicationContext(), MainScreenActivity.class);
                    startActivity(intent);
            }
        }
    };
    }

