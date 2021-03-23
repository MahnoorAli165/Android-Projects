package com.example.task_manager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowTaskActivity extends AppCompatActivity {

    private TextView textViewId;
    private TextView textViewName;
    private TextView textViewDesc;
    private TextView textViewDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);

        textViewId = (TextView) findViewById(R.id.textViewTaskId);
        textViewName = (TextView) findViewById(R.id.textViewTaskName);
        textViewDesc = (TextView) findViewById(R.id.textViewTaskDesc);
        textViewDay = (TextView) findViewById(R.id.textViewTaskDay);

        Intent intent = getIntent();
        Task task = (Task)intent.getSerializableExtra("task");
        textViewId.setText(task.getId()+"");
        textViewName.setText(task.getName()+"");
        textViewDesc.setText(task.getDesc()+"");
        textViewDay.setText(task.getDate()+"");
    }
}
