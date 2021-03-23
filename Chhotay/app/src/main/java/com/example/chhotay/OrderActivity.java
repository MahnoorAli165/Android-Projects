package com.example.chhotay;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class OrderActivity extends AppCompatActivity {
    TabLayout tabLayout;
    TabItem tabItem1, tabItem2;
    ViewPager viewPager;
    OrderAdapter orderAdapter;
    private AppBarLayout toolBar;
    private static final String TAG = "cartFragment";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        toolBar = (AppBarLayout) findViewById(R.id.order_toolbar);
        tabLayout=(TabLayout) findViewById(R.id.tablayout1);
        tabItem1 = (TabItem) findViewById(R.id.tab1);
        tabItem2 = (TabItem) findViewById(R.id.tab2);
        viewPager = (ViewPager) findViewById(R.id.vpager);

        orderAdapter = new OrderAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(orderAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                if(tab.getPosition()==0 || tab.getPosition()==1)
                    orderAdapter.notifyDataSetChanged();
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        ImageView ivBackArrow = (ImageView) findViewById(R.id.logo);
        ivBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: clicked back arrow.");
                intent = new Intent(getApplicationContext(),MainScreenActivity.class);
                startActivity(intent);

            }
        });
        TextView fragname = (TextView) findViewById(R.id.fragname);


    }
}
