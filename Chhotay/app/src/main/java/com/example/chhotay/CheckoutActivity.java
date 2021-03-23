package com.example.chhotay;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.AppBarLayout;

public class CheckoutActivity extends AppCompatActivity {
    private AppBarLayout toolBar;
    private static final String TAG = "Checkout Activitiy";
    Intent intent;
    Button proceedtopay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        toolBar = (AppBarLayout) findViewById(R.id.item_toolbar);
        proceedtopay = (Button) findViewById(R.id.confirmorder);
        Log.d(TAG, "onCreateView: started");
        ImageView ivBackArrow = (ImageView) findViewById(R.id.logo);
        ivBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked back arrow.");
                intent = new Intent(getApplicationContext(), ItemDetailActivity.class);
                startActivity(intent);

            }
        });
        TextView fragname = (TextView) findViewById(R.id.fragname);
        fragname.setText("Checkout");
        proceedtopay.setOnClickListener(onClickListener);

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.confirmorder:
                    startActivity(new Intent(getApplicationContext(), PaymentMethodSelectionActivity.class));
                    overridePendingTransition(0, 0);
                    break;
            }
        }
    };
}