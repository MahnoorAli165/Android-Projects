package com.example.chhotay;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CategoryActivity extends AppCompatActivity {

    public String Uid = "";
    int backButtonCount = 0;
    static final String TAG = "Home Activity";
    //    RecyclerView pList;
//    List<String> titles;
//    List<Integer> images;
//    Adapter adapter;
    BottomNavigationView navView;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        navView = findViewById(R.id.bottomNavigationView);

        navView.setSelectedItemId(R.id.categoryActivity);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new CategoryFragment()).commit();


        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homeFragment:
                        intent = new Intent(getApplicationContext(), HomeScreenActivity.class);
                        startActivity(intent);
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
                return  false;
            }



        });
    }





    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        if (backButtonCount >= 1) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }

    public void printFragmentStack() {
        FragmentManager fm = getFragmentManager();
        for (int entry = 0; entry < fm.getBackStackEntryCount(); entry++) {
            Log.e(TAG, "Found fragment: " + fm.getBackStackEntryAt(entry).getId());
        }
    }
}
