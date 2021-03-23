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

public class ContactUsActivity extends AppCompatActivity {
    private AppBarLayout toolBar;
    private static final String TAG = "Help Activity";
    Intent intent;
    TextView statement,twhatsapp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        toolBar = (AppBarLayout) findViewById(R.id.order_received_toolbar);

        statement = (TextView) findViewById(R.id.statement);
        twhatsapp = (TextView) findViewById(R.id.twhatsapp);
        statement.setText("For any Query, you can reach out to us at");
        twhatsapp.setText("0323-6006665");

        ImageView iClose = (ImageView) findViewById(R.id.iCloseArrow);
        iClose.setOnClickListener(onClickListener);


        TextView fragname = (TextView) findViewById(R.id.fragname);
        fragname.setText("Contact Us");


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
