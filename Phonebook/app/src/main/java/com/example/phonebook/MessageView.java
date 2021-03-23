package com.example.phonebook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MessageView extends AppCompatActivity implements View.OnClickListener {
    TextView txt1, txt2;
    EditText msg;
    Button sendmsg;
    String contactName;
    String contactNum;
    Intent i;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_view);
        i = getIntent();
        contactName = i.getStringExtra("name");
        contactNum = i.getStringExtra("phone");
        txt1 = (TextView) findViewById(R.id.name);
        txt2 = (TextView) findViewById(R.id.phone);
        txt1.setText(contactName);
        txt2.setText(contactNum);
        sendmsg = (Button) findViewById(R.id.sendMsg);
        sendmsg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.sendMsg) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Title");
            builder.setMessage("Message");
            builder.setPositiveButton("Confirm",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String sms = ((EditText) findViewById(R.id.editText)).getText().toString();
                            SmsManager sm = SmsManager.getDefault();
                            sm.sendTextMessage(contactNum, null, sms, null, null);
                            Toast.makeText(MessageView.this, "Message Sent!", Toast.LENGTH_SHORT).show();
                        }
                    });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MessageView.this, "Message Cancelled!", Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }
}
