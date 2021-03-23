package com.example.chhotay;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.List;


public class cartFragment extends Fragment {
    private AppBarLayout toolBar;
    private static final String TAG = "cartFragment";
    Intent intent;

    List<String> titles;
    List<Integer> images;
    List<String> digits;
    List<String> item_price;
    Button checkout, continue_shopping;

    public cartFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);



//        ((ItemDetailActivity) getActivity()).getSupportActionBar().hide();
        toolBar = (AppBarLayout) view.findViewById(R.id.item_toolbar);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.cList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // this is data fro recycler view
        titles= new ArrayList<>();
        images = new ArrayList<>();
        digits = new ArrayList<>();
        item_price= new ArrayList<>();



//
        titles.add("perfume");
        digits.add(1+"");

        item_price.add(2000+"");
        images.add(R.drawable.itemone);
        titles.add("perfume");
        digits.add(1+"");

        item_price.add(2000+"");
        images.add(R.drawable.itemone);
//        titles.add("Health and Beauty");
//        titles.add("Sports and Outdoors");
//        titles.add("Home and Lifestyle");
//        titles.add("Watches, Bags and Jewelry");



//        images.add(R.drawable.itemone);
//        images.add(R.drawable.itemone);
//        images.add(R.drawable.itemone);
//        images.add(R.drawable.itemone);
//        images.add(R.drawable.itemone);
//        images.add(R.drawable.itemone);

        CartAdapter adapter = new CartAdapter(getActivity(), titles,digits,item_price,images);

        // 4. set adapter
        recyclerView.setAdapter(adapter);
        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Log.d(TAG, "onCreateView: started");

        ImageView ivBackArrow = (ImageView) view.findViewById(R.id.logo);
        ivBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked back arrow.");
                intent = new Intent(getActivity(),HomeScreenActivity.class);
                startActivity(intent);

            }
        });
        TextView fragname = (TextView) view.findViewById(R.id.fragname);
        fragname.setText("Cart");
        checkout = view.findViewById(R.id.checkout);
        checkout.setOnClickListener(onClickListener);

        continue_shopping = view.findViewById(R.id.continueshopping);
        continue_shopping.setOnClickListener(onClickListener);
        return view;
    }
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.checkout:
                    intent = new Intent(getActivity(), CheckoutActivity.class);
                    startActivity(intent);
                    break;
                case R.id.continueshopping:
                    intent = new Intent(getActivity(), HomeScreenActivity.class);
                    startActivity(intent);
                    break;
            }

        }
    };


}
