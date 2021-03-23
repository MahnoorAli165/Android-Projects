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
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ChangePasswordActivity extends AppCompatActivity {
    private AppBarLayout toolBar;
    private static final String TAG = "Change Password Activity";
    Intent intent;
    Button change_password;
    BottomNavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        navView = findViewById(R.id.password_change_nav);
        ImageView ivBackArrow = (ImageView) findViewById(R.id.logo);
        ivBackArrow.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked back arrow.");
                intent = new Intent(getApplicationContext(), MainScreenActivity.class);
                startActivity(intent);
            }
        });
        TextView fragname = (TextView) findViewById(R.id.fragname);
        fragname.setText("Change Password");
        change_password = findViewById(R.id.change_password);
        change_password.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(),"Password Changed Successfully", Toast.LENGTH_LONG);
                toast.show();
                intent = new Intent(getApplicationContext(), MainScreenActivity.class);
                startActivity(intent);
            }
        });
    }
}
