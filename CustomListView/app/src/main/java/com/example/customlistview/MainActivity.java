package com.example.customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bitmap[] icons = new Bitmap[5];
        icons[0] = BitmapFactory.decodeResource(getResources(), R.drawable.amazon);
        icons[1] = BitmapFactory.decodeResource(getResources(), R.drawable.zipstawk);
        icons[2] = BitmapFactory.decodeResource(getResources(), R.drawable.walmart);
        icons[3] = BitmapFactory.decodeResource(getResources(), R.drawable.ellure);
        String[] names = {"Amazon", "Zip Stawk", "Walmart", "Ellure Lifestyle"};
        String[] dists = {"Seattle, Washington", "Mobile Phone Shop", "Bentonville, Arkansas", "Kanpur"};
        list = (ListView) findViewById(R.id.listView);
        CustomList customList = new CustomList(this, names, dists, icons);
        list.setAdapter(customList);

    }

    public class CustomList extends ArrayAdapter<String> {
        private String[] names;
        private String[] distance;
        private Bitmap[] bitmaps;
        private Activity context;

        public CustomList(Activity context, String[] names, String[] distance, Bitmap[] bitmaps) {
            super(context, R.layout.row, names);//sending R.layout file and one array is necessary here
            this.context = context;
            this.names = names;
            this.distance = distance;
            this.bitmaps = bitmaps;
        }

            @Override public View getView(int position, View convertView, ViewGroup parent){
                LayoutInflater inflater = context.getLayoutInflater();
                View listViewItem = inflater.inflate(R.layout.row, null, true);
                TextView textViewnames = (TextView) listViewItem.findViewById(R.id.name);
                TextView textViewgenre = (TextView) listViewItem.findViewById(R.id.distance);
                ImageView image = (ImageView) listViewItem.findViewById(R.id.imageDownloaded2);
                textViewnames.setText(names[position]);
                textViewgenre.setText(distance[position]);
                image.setImageBitmap(bitmaps[position]);
                return listViewItem;
            }

    }
}
