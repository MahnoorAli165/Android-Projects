package com.example.webcrawler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class MainActivity extends AppCompatActivity  {
    TextView txt1;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt1 = (TextView) findViewById(R.id.textView);
        btn = (Button) findViewById(R.id.button);
        getData(btn);
    }




    String specific="";

    public void getData(View view) {

            if (view.getId() == btn.getId()) {
                new myTask().execute();

            }
        }


public class myTask extends AsyncTask<String, Integer, String>{
        @Override
        protected String doInBackground(String... params) {
            try{
                Document doc= Jsoup.connect("https://www.imdb.com/chart/top?ref_=nv_mv_250_6").get();
                for (Element row : doc.select("table.chart.full-width tr")) {
                    final String title = row.select(".titleColumn").text();
                    final String rating = row.select(".ratingColumn.imdbRating").text();
                    specific = specific + title + "==> Rating: " + rating + "\n";
                }
                return specific;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
          return "";
        }
        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            txt1.setText(s);
        }

            }
        }


