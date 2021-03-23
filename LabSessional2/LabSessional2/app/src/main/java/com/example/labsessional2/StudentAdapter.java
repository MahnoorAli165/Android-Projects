package com.example.labsessional2;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class StudentAdapter extends ArrayAdapter<Student> {

    private final Activity context;
    private final ArrayList<Student> students;

    public StudentAdapter(Activity context, ArrayList<Student> students) {
        super(context, R.layout.view_student, students);
        this.context = context;
        this.students = students;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View view, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        final View rowView = inflater.inflate(R.layout.view_student, null, true);
        TextView textName = rowView.findViewById(R.id.textName);
        TextView textRegNo = rowView.findViewById(R.id.textRegNo);
        TextView textPhone = rowView.findViewById(R.id.textPhone);
        textName.setText(students.get(position).getName() + "");
        textRegNo.setText(students.get(position).getReg_no() + "");
        textPhone.setText(students.get(position).getPhone() + "");


        return rowView;
    }
}