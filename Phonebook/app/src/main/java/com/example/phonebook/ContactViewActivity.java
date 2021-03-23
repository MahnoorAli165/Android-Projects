package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ContactViewActivity extends AppCompatActivity implements View.OnClickListener {
    Intent i;
    TextView name,phone;
    String contactName;
    String contactNum;
    Button btnCall, btnMsg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_view);
        i = getIntent();
        contactName = i.getStringExtra("name");
        contactNum = i.getStringExtra("phone");
        name = findViewById(R.id.lblName);
        phone = findViewById(R.id.lblName2);
        name.setText(contactName);
        phone.setText(contactNum);
        btnCall = (Button) findViewById(R.id.btnCall);
        btnMsg = (Button) findViewById(R.id.btnMsg);
        btnCall.setOnClickListener(this);
        btnMsg.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btnCall) {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + this.contactNum));
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 0);
            }
            startActivity(intent);

        }
        if (v.getId() == R.id.btnMsg) {
            Intent i = new Intent(this, MessageView.class);
            i.putExtra("name", contactName);
            i.putExtra("phone", contactNum);
            startActivity(i);

        }
    }
}
