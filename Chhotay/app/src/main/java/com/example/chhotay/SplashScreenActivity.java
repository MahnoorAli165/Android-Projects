package com.example.chhotay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class SplashScreenActivity extends AppCompatActivity {
    Handler handler;
    String Uid = "";
    final String TAG = "Splash Activity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, HomeScreenActivity.class));
                finish();
            }
        }, secondsDelayed * 1000);










//        handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Log.e("Splash", "Uid: "+Uid);
//                startActivity(new Intent(SplashScreenActivity.this, HomeScreenActivity.class).putExtra("id", Uid));
//                finish();
//            }
//        }, 1500);
    }
}

