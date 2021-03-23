package com.example.lab4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class list_view extends AppCompatActivity {

    private ListView listView;

    String mtitle[] = {"Facebook","Twitter","Instagram","Youtube"};
    String mdescription[] = {"Facebook Description","Twitter Description","Instagram Description","Youtube Description"};
    int images[] = {R.drawable.facebook, R.drawable.twitter, R.drawable.instagram, R.drawable.youtube};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        listView = findViewById(R.id.list_view1);

        MyAdapter adapter = new MyAdapter(this,mtitle,mdescription,images);
        listView.setAdapter(adapter);

        // String [] my_array = getResources().getStringArray(R.array.list_items);

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, phonelist);

    }
}
