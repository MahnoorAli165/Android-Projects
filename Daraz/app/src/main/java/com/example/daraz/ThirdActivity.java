package com.example.daraz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity implements TopFragment.OnFragmentInteractionListener, ThirdFragment.OnFragmentInteractionListener {

    private TopFragment topFragment;
    private EditText editTextName;
    private EditText editTextPhone;
    private Button buttonCheckOrders;
    private ThirdFragment thirdFragment;
    private ListView listView;
    private OrderAdapter adapter;
    private ArrayList<Order> orders;
    private String ordersRaw;
    private Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        topFragment = (TopFragment) getSupportFragmentManager().findFragmentById(R.id.fragment3);
        editTextName = (EditText) topFragment.getView().findViewById(R.id.editTextName);
        editTextPhone = (EditText) topFragment.getView().findViewById(R.id.editTextPhone);
        buttonCheckOrders = (Button) topFragment.getView().findViewById(R.id.buttonCheckOrders);
        buttonCheckOrders.setEnabled(false);
        buttonCheckOrders.requestFocus();

        Intent intent = getIntent();
        editTextName.setText(intent.getStringExtra("name"));
        editTextPhone.setText(intent.getStringExtra("phone"));
        customer = new Customer(intent.getStringExtra("name"), intent.getStringExtra("phone"));

        thirdFragment = (ThirdFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentThird);
        listView = (ListView) thirdFragment.getView().findViewById(R.id.listViewOrders);
        orders = new ArrayList<>();
        orders.add(new Order("Oid", "Date", "Pid", "QT", "Price", "Amount", "Name"));

        new GetOrdersTask().execute();
    }

    public class GetOrdersTask extends AsyncTask<String, String, String> {

        String Complete_line;

        @Override
        protected void onPreExecute() {
            Toast.makeText(ThirdActivity.this, "Fetching orders.", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                String data = URLEncoder.encode("cust_phone", "UTF-8") + "=" + URLEncoder.encode(customer.getPhone(), "UTF-8");
                URL url = new URL("https://mahnooralicheema.000webhostapp.com/getOrders.php");
                URLConnection con = (URLConnection) url.openConnection();
                con.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                wr.write(data);
                wr.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line = "";
                ordersRaw = "";
                while((line = reader.readLine()) != null) {
                    ordersRaw = ordersRaw + line;
                }
                
                if(ordersRaw.equals("")) {
                    return "NO";
                }
                
                String ordersRaw_[] = ordersRaw.split("[$]");
                for(String s : ordersRaw_) {
                    String s_[] = s.split("[|]");
                    if(s_.length > 1) {
                        orders.add(new Order(s));
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return "OK";
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("OK")) {
                updateListView();
            } else {
                Toast.makeText(ThirdActivity.this, "No orders.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void updateListView() {
        adapter = new OrderAdapter(this, orders);
        listView.setAdapter(adapter);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
