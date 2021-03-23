package com.example.lab4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

class MyAdapter extends ArrayAdapter<String> {

    Context context;
    String names[];
    String numbers[];
    int images[];


    MyAdapter(Context c, String numbers[], String names[], int images[]) {
        super(c, R.layout.list_item, R.id.tv1, names);
        this.context = c;
        this.names = names;
        this.numbers = numbers;
        this.images = images;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.list_item, parent, false);
        ImageView image = row.findViewById(R.id.image1);
        TextView disp_name = row.findViewById(R.id.tv1);
        TextView disp_num = row.findViewById(R.id.tv2);

        //now set our resource on views
        image.setImageResource(images[position]);

        ArrayList<String> nnname = new ArrayList<>(Arrays.asList(names));
        ArrayList<String> nnumber = new ArrayList<>(Arrays.asList(numbers));

        // checking for the difference between the no of contacts with names and without names.
        // missing places will be filled with default value


        disp_name.setText(names[position]);
        disp_num.setText(numbers[position]);
        return row;


    }


}