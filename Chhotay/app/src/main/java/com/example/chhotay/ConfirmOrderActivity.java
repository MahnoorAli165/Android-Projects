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

public class ConfirmOrderActivity extends AppCompatActivity {
    private AppBarLayout toolBar;
    private static final String TAG = "Cash On Delivery Activity";
    Intent intent;
    Button continue_shopping;

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        toolBar = (AppBarLayout) findViewById(R.id.order_received_toolbar);

        Log.d(TAG, "onCreateView: started");
        continue_shopping = findViewById(R.id.continueshopping);
        continue_shopping.setOnClickListener(onClickListener);

        ImageView iClose = (ImageView) findViewById(R.id.iCloseArrow);
        iClose.setOnClickListener(onClickListener);


        TextView fragname = (TextView) findViewById(R.id.fragname);
        fragname.setText("Order Received");


    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @SuppressLint("LongLogTag")
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.continueshopping:
                    startActivity(new Intent(getApplicationContext(), HomeScreenActivity.class));
                    overridePendingTransition(0, 0);
                    break;
                    case R.id.iCloseArrow:
                        Log.d(TAG, "onClick: clicked back arrow.");
                        intent = new Intent(getApplicationContext(), HomeScreenActivity.class);
                        startActivity(intent);
            }
        }
    };
}