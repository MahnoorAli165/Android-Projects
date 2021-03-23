package com.example.crawler;

import android.os.AsyncTask;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class MyServerTask extends AsyncTask<String, String, String> {
    @Override
    protected String doInBackground(String... params) {
        String Complete_line="";
        String response = "";
    try {

        String name = "Jawad Ahmad";
        String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");
        URL url= new URL("http://testkaro.epizy.com/server.php");
        URLConnection conn = (URLConnection) url.openConnection();
        conn.setDoOutput(true);//if POST or PUT is used.

        OutputStreamWriter wr= new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();
        //Data is Username: Jawad Ahmad. This code connects to Server and finds a file. Then, it writes data to the file


        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));


        String line = null;
        Complete_line= null;// Read Server Response
        while ((line = reader.readLine()) != null) {
            Complete_line = Complete_line + "\n" + line + "\n";
            break;

        }
        JSONObject responseJSON = new JSONObject(Complete_line);
        response = responseJSON.getString("name");

        //Read that file line by line
    } catch (Exception e) {
        e.printStackTrace();
    }
        return response + "\n" + Complete_line;



    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
        Server.text.setText(result);
    }
}
