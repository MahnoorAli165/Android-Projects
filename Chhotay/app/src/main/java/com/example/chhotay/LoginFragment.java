package com.example.chhotay;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class LoginFragment extends Fragment {
    Button blogin;
    TextView forgotpassword;
    Intent intent;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        blogin = view.findViewById(R.id.blogin);
        forgotpassword = view.findViewById(R.id.forgotpassword);
        blogin.setOnClickListener(onClickListener);
        forgotpassword.setOnClickListener(onClickListener);
        return view;
    }
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.blogin:
                    intent = new Intent(getActivity(),MainScreenActivity.class);
                    startActivity(intent);
                    break;
                case R.id.forgotpassword:
                    intent = new Intent(getActivity(),ForgotPasswordActivity.class);
                    startActivity(intent);
                    break;
            }

        }
    };



}
