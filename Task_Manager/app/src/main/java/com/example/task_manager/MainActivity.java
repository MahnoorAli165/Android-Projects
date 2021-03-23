package com.example.task_manager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    public static ArrayList<Task> tasks ;
    private ListView listView;
    private TaskAdapter adapter;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeWidgets();

    }

    private void initializeWidgets(){
        listView = (ListView) findViewById(R.id.listview);
        button = (Button) findViewById(R.id.buttonAdd);
        button.setOnClickListener(this);


        tasks = new ArrayList<>();
        adapter = new TaskAdapter(this, R.layout.task_layout , tasks);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == button.getId()) {
            Intent intent = new Intent(this, TaskActivity.class);
            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            if(resultCode == Activity.RESULT_OK) {
                Task task = new Task();
                task.setId(data.getIntExtra("id", -1));
                task.setName(data.getStringExtra("name"));
                task.setDesc(data.getStringExtra("desc"));
                task.setDate(data.getStringExtra("date"));
                tasks.add(task);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, ShowTaskActivity.class);
        intent.putExtra("task", tasks.get(i));
        startActivity(intent);
    }
}
