package com.example.chhotay;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Cart extends AppCompatActivity {
    public String Uid = "";
    int backButtonCount = 0;
    static final String TAG = "Cart Activity";
    //    RecyclerView pList;
////    List<String> titles;
////    List<Integer> images;
//    Adapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

//
//        navView.setSelectedItemId(R.id.cartFragment);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new cartFragment()).commit();
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.cartFragment, new cartFragment());
//        fragmentTransaction.commit();



    }



    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), HomeScreenActivity.class);
        startActivity(intent);

    }
}