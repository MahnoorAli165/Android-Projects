package com.example.texttospeech;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class ContactsAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final ArrayList<String> name;
    private final ArrayList<String> phone;
    public ContactsAdapter(Activity context, ArrayList<String> name, ArrayList<String> phone){
        super(context,R.layout.contact_single,name);
        this.context=context;
        this.name=name;
        this.phone=phone;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.contact_single,null,true);
        TextView txtName=(TextView)rowView.findViewById(R.id.name);
        TextView txtPhone=(TextView)rowView.findViewById(R.id.phone);
        txtName.setText(this.name.get(position)+"");
        txtPhone.setText(this.phone.get(position)+"");
        return rowView;
    }
}
