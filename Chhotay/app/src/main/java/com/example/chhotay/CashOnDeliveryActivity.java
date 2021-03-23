package com.example.chhotay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.AppBarLayout;

public class CashOnDeliveryActivity extends AppCompatActivity {
    private AppBarLayout toolBar;
    private static final String TAG = "Cash On Delivery Activity";
    Intent intent;
    Button confirmorder;


    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_on_delivery);
        toolBar = (AppBarLayout) findViewById(R.id.item_toolbar);

        Log.d(TAG, "onCreateView: started");

        ImageView ivBackArrow = (ImageView) findViewById(R.id.logo);
        ivBackArrow.setOnClickListener(onClickListener);

        TextView fragname = (TextView) findViewById(R.id.fragname);
        fragname.setText("Cash On Delivery");

        confirmorder = (Button) findViewById(R.id.confirmorder);
        confirmorder.setOnClickListener(onClickListener);
    }
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @SuppressLint("LongLogTag")
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.confirmorder:
                    intent = new Intent(getApplicationContext(),ConfirmOrderActivity.class);
                    startActivity(intent);
                    break;
                case R.id.logo:
                    Log.d(TAG, "onClick: clicked back arrow.");
                    intent = new Intent(getApplicationContext(),PaymentMethodSelectionActivity.class);
                    startActivity(intent);
                    break;

            }

        }
    };
}
