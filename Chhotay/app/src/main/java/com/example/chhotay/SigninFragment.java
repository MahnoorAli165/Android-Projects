package com.example.chhotay;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class SigninFragment extends Fragment {
    Button signin;
    TextView login;
    Intent intent;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signin, container, false);
        signin = view.findViewById(R.id.signin);
        login = view.findViewById(R.id.login);
        signin.setOnClickListener(onClickListener);
        login.setOnClickListener(onClickListener);
        return view;
    }
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.signin:
                    intent = new Intent(getActivity(),MainScreenActivity.class);
                    startActivity(intent);
                    break;
                case R.id.login:
                    intent = new Intent(getActivity(),LoginActivity.class);
                    startActivity(intent);
                    break;
            }

        }
    };
}
