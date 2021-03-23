package com.example.texttospeech;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SendActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    Cursor conts = null;
    ArrayList<String> names, phones;
    ContactsAdapter contactsAdapter = null;
    ListView listView = null;
    String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        names = new ArrayList<>();
        phones = new ArrayList<>();
        message = getIntent().getStringExtra("message");
        conts = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        conts.moveToFirst();

        while(!conts.isAfterLast()) {
            String name = conts.getString(conts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phone = conts.getString(conts.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            names.add(name);
            phones.add(phone);
            conts.moveToNext();
        }
        contactsAdapter = new ContactsAdapter(this, names, phones);
        listView = (ListView) findViewById(R.id.contactList);
        listView.setAdapter(contactsAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Confirm?");
        builder.setMessage("Do you wish to send message to "+names.get(i)+"?");
        builder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SmsManager sm = SmsManager.getDefault();
                        sm.sendTextMessage(phones.get(i), null, message, null, null);
                        Toast.makeText(SendActivity.this, "Message Sent!", Toast.LENGTH_SHORT).show();
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(SendActivity.this, "Message Cancelled!", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
