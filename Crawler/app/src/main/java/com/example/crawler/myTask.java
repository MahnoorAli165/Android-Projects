package com.example.crawler;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import java.io.IOException;
public class myTask extends AsyncTask<String, Integer, String> {

    String specific = "";
    @Override
    protected String doInBackground(String... params){
        try{
            Document doc = Jsoup.connect("https://www.imdb.com/chart/top?ref_=nv_mv_250_6").get();

            for (Element row : doc.select("table.chart.full-width tr")) {
                final String title = row.select(".titleColumn").text();
                final String rating = row.select(".imdbRating").text();
                specific = specific + title + "==> Rating: " + rating + "\n";
            }
            return specific;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }@Override
    protected void onPostExecute(String s){
        super.onPostExecute(s);
        MainActivity.text.setText(s);
    }
}