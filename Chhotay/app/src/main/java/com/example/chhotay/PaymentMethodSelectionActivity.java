package com.example.chhotay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PaymentMethodSelectionActivity extends AppCompatActivity {
    public String Uid = "";
    int backButtonCount = 0;
    static final String TAG = "Home Activity";

    BottomNavigationView navView;


    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method_selection);
        navView = findViewById(R.id.bottomNavigationView);

        navView.setSelectedItemId(R.id.paymentmethodselection);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SelectPaymentMethodFragment()).commit();

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homeFragment:
                        getSupportFragmentManager().beginTransaction()
                                .replace(android.R.id.content, new HomeFragment()).commit();
                        return true;
                    case R.id.categoryActivity:
                        Intent intent = new Intent(getApplicationContext(), CategoryActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.cart:
                        startActivity(new Intent(getApplicationContext(), Cart.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.account:
                        startActivity(new Intent(getApplicationContext(), AccountActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    default:
                        getSupportFragmentManager().beginTransaction()
                                .replace(android.R.id.content, new HomeFragment()).commit();

                }
//                default:
//                getSupportFragmentManager().beginTransaction()
//                        .add(android.R.id.content, new HomeFragment()).commit();
//                return true;
                return true;
            }


        });
    }
}
