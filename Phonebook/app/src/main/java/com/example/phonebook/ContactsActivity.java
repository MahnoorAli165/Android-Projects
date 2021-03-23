package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    Cursor conts;
    ArrayList<String> names, phones;
    ContactsAdapter contactsAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        names = new ArrayList<>();
        phones = new ArrayList<>();
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(this, ContactViewActivity.class);
        i.putExtra("name", names.get(position));
        i.putExtra("phone", phones.get(position));
        startActivity(i);
    }



}
