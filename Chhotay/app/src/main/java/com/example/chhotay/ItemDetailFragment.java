package com.example.chhotay;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.AppBarLayout;


public class ItemDetailFragment extends Fragment {

    ImageView productimg;
    TextView price,pname, spec,specd,service,serviced, highlight, highlightd,description, descriptiond,delivery, deliveryd;
    Intent intent;

    private AppBarLayout toolBar;
    private static final String TAG = "itemDetailFragment";




    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_detail, container, false);
//        ((ItemDetailActivity) getActivity()).getSupportActionBar().hide();
        toolBar = (AppBarLayout) view.findViewById(R.id.item_toolbar);

        Log.d(TAG, "onCreateView: started");



        productimg = (ImageView) view.findViewById(R.id.productimg);
        productimg.setImageResource(R.drawable.itemone);
        price = (TextView)view.findViewById(R.id.price);
        price.setText("Rs. 2000");
        pname  =(TextView)view.findViewById(R.id.pname);
        pname.setText("Perfume");
        spec =(TextView) view.findViewById(R.id.spec);
        spec.setText("Specification");
        specd = (TextView)view.findViewById(R.id.specd);
        service= (TextView)view.findViewById(R.id.service);
        service.setText("Service");
        serviced  = (TextView)view.findViewById(R.id.serviced);
        highlight = (TextView)view.findViewById(R.id.highlight);
        highlight.setText("Highlight");
        highlightd = (TextView)view.findViewById(R.id.highlightd);
        description = (TextView)view.findViewById(R.id.description);
        description.setText("Description");
        descriptiond = (TextView)view.findViewById(R.id.descriptiond);
        delivery = (TextView)view.findViewById(R.id.delivery);
        delivery.setText("Delivery");
        deliveryd = (TextView)view.findViewById(R.id.deliveryd);

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
        fragname.setText(""+"");


//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //open navigation drawer when click navigation back button
//                intent = new Intent(getActivity(),HomeScreenActivity.class);
//                startActivity(intent);
////                DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
////                drawer.openDrawer(Gravity.START);
//            }
//        });

        // Inflate the layout for this fragment
        return view;
    }



    // Sets the appbar state for either search mode or standard mode.




    @Override
    public void onResume() {
        super.onResume();


    }


}
