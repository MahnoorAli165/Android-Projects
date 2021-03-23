package com.example.chhotay;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.List;


public class CurrentOrderFragment extends Fragment {
    private AppBarLayout toolBar;
    private static final String TAG = "current order Fragment";
    Intent intent;
    List<String> order_no;
    List<Integer> images;
    List<String> orderDate;
    List<String> status;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_current_order, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.oList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // this is data fro recycler view
        order_no= new ArrayList<>();
        images = new ArrayList<>();
        orderDate = new ArrayList<>();
        status= new ArrayList<>();

        order_no.add("12345");
        images.add(R.drawable.itemone);
        orderDate.add("26-12-2020");
        status.add("In Process");
        order_no.add("12334435");
        images.add(R.drawable.itemone);
        orderDate.add("26-12-2020");
        status.add("In Process");
        CurrentOrderAdapter currentOrderAdapter = new CurrentOrderAdapter(getActivity(), order_no,images,orderDate,status);

        // 4. set adapter
        recyclerView.setAdapter(currentOrderAdapter);
        // 5. set item animator to DefaultAnimator
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        TextView fragname = (TextView) getActivity().findViewById(R.id.fragname);
//        fragname.setText("Current Order");

        return view;
    }

}
