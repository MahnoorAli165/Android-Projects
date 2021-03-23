package com.example.chhotay;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;


public class MainscreenFragment extends Fragment  {
    Toolbar toolbar;
    ImageButton order, help, contactus, showPopup;
    Intent intent;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mainscreen, container, false);
        toolbar = view.findViewById(R.id.mainscreen_toolbar);
        showPopup = view.findViewById(R.id.showPopup);
        showPopup.setOnClickListener(listener);

        order = view.findViewById(R.id.order);
        help = view.findViewById(R.id.help);
        contactus = view.findViewById(R.id.contactus);
        order.setOnClickListener(listener);
        help.setOnClickListener(listener);
        contactus.setOnClickListener(listener);

        return view;

    }
//    public void showPopup(View v){
//        PopupMenu popupMenu = new PopupMenu(getActivity(),v);
//        popupMenu.setOnMenuItemClickListener(this);
//        popupMenu.inflate(R.menu.mainscreen_menu);
//        popupMenu.show();
//    }


    private View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case (R.id.order):
                    intent = new Intent(getActivity(), OrderActivity.class);
                    startActivity(intent);
                    break;
                case (R.id.help):
                    intent = new Intent(getActivity(), HelpActivity.class);
                    startActivity(intent);
                    break;
                case (R.id.contactus):
                    intent = new Intent(getActivity(), ContactUsActivity.class);
                    startActivity(intent);
                    break;
                case (R.id.showPopup):
                    PopupMenu popupMenu = new PopupMenu(getActivity(), v);
                    popupMenu.inflate(R.menu.mainscreen_menu);
                    popupMenu.show();
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case (R.id.changepassword):
                                    intent = new Intent(getActivity(), ChangePasswordActivity.class);
                                    startActivity(intent);
                                    return true;
                                case (R.id.logout):
                                    intent = new Intent(getActivity(), AccountActivity.class);
                                    startActivity(intent);
                                    return true;
                                default:
                                    return false;

                            }
                        }
                    });
            }
        }
    };

}



//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        inflater.inflate(R.menu.mainscreen_menu, menu);


//    public boolean onMenuItemClick(MenuItem menuItem) {
//        switch (menuItem.getItemId()){
//            case (R.id.changepassword):
//                intent = new Intent(getActivity(),ChangePasswordActivity.class);
//                startActivity(intent);
//                return true;
//            case (R.id.logout):
//                intent = new Intent(getActivity(),AccountActivity.class);
//                startActivity(intent);
//                return true;
//                default:
//                    return false;
//
//        }
//    }

