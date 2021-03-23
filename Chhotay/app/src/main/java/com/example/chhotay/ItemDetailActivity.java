package com.example.chhotay;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ItemDetailActivity extends AppCompatActivity {
    private static final String SELECTED_ITEM = "selected_item";
    private static final String TAG = "";

    private BottomNavigationView navView;

    private MenuItem menuItemSelected;
    private int mMenuItemSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        navView = findViewById(R.id.item_nav);


        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return true;
            }
        });

        //Always load first fragment as default
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.itemDetailFragment, new ItemDetailFragment());
        fragmentTransaction.commit();

        if (savedInstanceState != null) {
            mMenuItemSelected = savedInstanceState.getInt(SELECTED_ITEM, 0);
            menuItemSelected = navView.getMenu().findItem(mMenuItemSelected);
        } else {
            menuItemSelected = navView.getMenu().getItem(0);
        }

        selectFragment(menuItemSelected);
    }

    private boolean selectFragment(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemDetailFragment:
                getSupportFragmentManager().beginTransaction()
                        .replace(android.R.id.content, new ItemDetailFragment()).commit();
                return true;
            case R.id.buy_now:
                startActivity(new Intent(getApplicationContext(), CheckoutActivity.class));
                overridePendingTransition(0, 0);
                return true;
            case R.id.cart:
                startActivity(new Intent(getApplicationContext(), Cart.class));
                overridePendingTransition(0, 0);
                return true;
            default:
                navView.getMenu().findItem(R.id.uncheckedItem).setChecked(true);

        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(SELECTED_ITEM, mMenuItemSelected);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onBackPressed() {
        MenuItem homeItem = navView.getMenu().getItem(0);
        if (mMenuItemSelected != homeItem.getItemId()) {
            selectFragment(homeItem);
            navView.getMenu().findItem(R.id.uncheckedItem).setChecked(true);
        } else {
            super.onBackPressed();
        }
    }


        public void printFragmentStack () {
            FragmentManager fm = getFragmentManager();
            for (int entry = 0; entry < fm.getBackStackEntryCount(); entry++) {
                Log.e(TAG, "Found fragment: " + fm.getBackStackEntryAt(entry).getId());
            }
        }
    }

