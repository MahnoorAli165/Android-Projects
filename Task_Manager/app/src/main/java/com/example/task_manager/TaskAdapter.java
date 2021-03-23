package com.example.task_manager;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {

    private ArrayList<Task> tasks;
    private Context mContext;
    private int mResource;


    public TaskAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Task> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        tasks = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View rowView = inflater.inflate(mResource, parent, false);
        TextView textViewName = (TextView) rowView.findViewById(R.id.textViewName1);
        TextView textViewDate = (TextView) rowView.findViewById(R.id.textViewDate1);
        textViewName.setText(this.tasks.get(position).getName());
        textViewDate.setText(this.tasks.get(position).getDate());
        return rowView;
    }
}
