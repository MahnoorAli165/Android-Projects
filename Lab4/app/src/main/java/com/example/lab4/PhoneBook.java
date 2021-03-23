package com.example.lab4;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class PhoneBook extends AppCompatActivity {

    private ListView listView = null;
   /* String[] nam = {"one", "two"};
    String[] num = {"23423", "234234", "345634","32534","234523","234532","2345234","234523","243532"};
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_book);


        /**
         * Cursor is an interface which represents a 2 dimensional table of any database.
         * When you try to retrieve some data using SELECT statement, then the database
         * will first create a Cursor object and return its reference to you.
         *
         * The pointer of this returned reference is pointing to the 0th location which
         * is otherwise called as before first location of the Cursor, so when you want
         * to retrieve data from the cursor, you have to first move to the first record so we have to use moveToFirst
         *
         * When you invokes moveToFirst() method on the Cursor, it takes the cursor
         * pointer to the first location. Now you can access the data present in the first record
         */

        listView = findViewById(R.id.list_view1);
        // String [] my_array = getResources().getStringArray(R.array.list_items);

        ArrayList<String> phonelist = new ArrayList<>();
        ArrayList<String> namelist = new ArrayList<>();


        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, phonelist);

        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

       // int contact_count = 100;
        while (cur.moveToNext()) {
            String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));

            //---------------------------------------- Contact Names
            //------------------------------------------------------
            String DisplayName = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));


            namelist.add(DisplayName);  // add names to arraylist

            /*Cursor names = cr.query(ContactsContract.Data.CONTENT_URI, null,ContactsContract.Data.CONTACT_ID + " = " + id, null, null);
            names.moveToNext();
            String GivenName = names.getString(names.getColumnIndex(ContactsContract.Data.DATA2));
            String FamilyName = names.getString(names.getColumnIndex(ContactsContract.Data.DATA3));
            String MiddleName = names.getString(names.getColumnIndex(ContactsContract.Data.DATA5));
            names.close();*/


            //----------------------------------------- Phone Numbers
            //-------------------------------------------------------

            String hasPhoneNumber = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
            if (hasPhoneNumber.equals("1")) {
                Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null);
                phones.moveToNext();

                String number = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                phonelist.add(number);   // adding numbers to list

                //contact_count--;
            }
            else if(hasPhoneNumber.equals("0")){
                phonelist.add("default-number");    // adding a default message instead,if number not present
            }
            else{
                namelist.add("default-name");       // adding a default message instead,if name not present
            }
        }

        String s_phoneList[] = phonelist.toArray(new String[phonelist.size()]);
        String s_nameList[] = namelist.toArray(new String[namelist.size()]);

        MyAdapter real_adapter = new MyAdapter(this, s_phoneList, s_nameList);
        listView.setAdapter(real_adapter);

    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String names[];
        String numbers[];


        MyAdapter(Context c, String numbers[], String names[]) {
            super(c, R.layout.list_item, R.id.tv1, names);
            this.context = c;
            this.names = names;
            this.numbers = numbers;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.list_item, parent, false);
            ImageView image = row.findViewById(R.id.image1);
            TextView disp_name = row.findViewById(R.id.tv1);
            TextView disp_num = row.findViewById(R.id.tv2);

            //now set our resource on views
            image.setImageResource(R.drawable.ic_account_box_black_24dp);

            ArrayList<String> nnname = new ArrayList<>(Arrays.asList(names));
            ArrayList<String> nnumber = new ArrayList<>(Arrays.asList(numbers));

            // checking for the difference between the no of contacts with names and without names.
            // missing places will be filled with default value


            disp_name.setText(names[position]);
            disp_num.setText(numbers[position]);
            return row;


        }


    }
}
