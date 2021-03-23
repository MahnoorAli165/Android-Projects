package com.example.chhotay;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.AppBarLayout;


public class SelectPaymentMethodFragment extends Fragment {
    private AppBarLayout toolBar;
    private static final String TAG = "Payment Method Selection Fragment";
    Intent intent;
    RadioButton cod;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("LongLogTag")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_payment_method, container, false);
        toolBar = (AppBarLayout) view.findViewById(R.id.item_toolbar);
        cod = (RadioButton) view.findViewById(R.id.cod);

        View.OnClickListener radio_listener = new View.OnClickListener(){
            public void onClick(View v) {
                //perform your action here
                if(v==cod){
                    intent = new Intent(getActivity(),CashOnDeliveryActivity.class);
                    startActivity(intent);
                }
            }
        };
        cod.setOnClickListener(radio_listener);

        Log.d(TAG, "onCreateView: started");
        ImageView ivBackArrow = (ImageView) view.findViewById(R.id.logo);
        ivBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked back arrow.");
                intent = new Intent(getActivity(),CheckoutActivity.class);
                startActivity(intent);
            }
        });
        TextView fragname = (TextView) view.findViewById(R.id.fragname);
        fragname.setText("Select Payment Method");
        return view;
    }


}
