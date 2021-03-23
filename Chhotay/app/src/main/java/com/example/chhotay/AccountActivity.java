package com.example.chhotay;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AccountActivity extends AppCompatActivity {

    private AppBarLayout toolBar;
    private static final String TAG = "Account Activity";
    Intent intent;
    BottomNavigationView navView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        toolBar = (AppBarLayout) findViewById(R.id.account_toolbar);

        ImageView logo = (ImageView) findViewById(R.id.logo);
        navView = findViewById(R.id.bottomNavigationView);

        navView.setSelectedItemId(R.id.account);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SigninFragment()).commit();

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homeFragment:
                        getSupportFragmentManager().beginTransaction()
                                .replace(android.R.id.content, new HomeFragment()).commit();
                        break;
                    case R.id.categoryActivity:
                        intent = new Intent(getApplicationContext(), CategoryActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.cart:
                        intent = new Intent(getApplicationContext(), Cart.class);
                        startActivity(intent);
//                        overridePendingTransition(0, 0);
                        break;
                    case R.id.account:
                        intent = new Intent(getApplicationContext(), AccountActivity.class);
                        startActivity(intent);
//                        overridePendingTransition(0, 0);
                        break;

                }
                return  true;


        }
    });
    }
//
}
