package com.example.chhotay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.AppBarLayout;

public class ForgotPasswordActivity extends AppCompatActivity {
    private AppBarLayout toolBar;
    private static final String TAG = "Forgot Password Activitiy";
    Intent intent;
    Button send;


    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        toolBar = (AppBarLayout) findViewById(R.id.item_toolbar);
        send = (Button) findViewById(R.id.send);
        Log.d(TAG, "onCreateView: started");
        ImageView ivBackArrow = (ImageView) findViewById(R.id.logo);
        ivBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked back arrow.");
                intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        });
        TextView fragname = (TextView) findViewById(R.id.fragname);
        fragname.setText("Forgot Password");
        send.setOnClickListener(onClickListener);

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.send:
                    Toast toast = Toast.makeText(getApplicationContext(),"An email has been send to you to reset password", Toast.LENGTH_LONG);
                    toast.show();

//                    startActivity(new Intent(getApplicationContext(), PaymentMethodSelectionActivity.class));
//                    overridePendingTransition(0, 0);
                    break;
            }
        }
    };

}
