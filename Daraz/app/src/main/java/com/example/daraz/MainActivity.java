package com.example.daraz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements AdapterView.OnItemClickListener, TopFragment.OnFragmentInteractionListener, FirstFragment.OnFragmentInteractionListener {

    private FirstFragment firstFragment;
    private TopFragment topFragment;
    private EditText editTextName;
    private EditText editTextPhone;
    private String productsRaw;
    private ArrayList<Product> products;
    private ProductAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topFragment = (TopFragment) getSupportFragmentManager().findFragmentById(R.id.fragment1);
        editTextName = (EditText) topFragment.getView().findViewById(R.id.editTextName);
        editTextPhone =(EditText) topFragment.getView().findViewById(R.id.editTextPhone);
        firstFragment = (FirstFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMain);
        listView = (ListView) firstFragment.getView().findViewById(R.id.listViewProducts);
        listView.setOnItemClickListener(this);
        products = new ArrayList<>();

        new GetProductsTask().execute();

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("name", editTextName.getText().toString());
        intent.putExtra("phone", editTextPhone.getText().toString());
        intent.putExtra("product", products.get(i));
        startActivity(intent);
    }

    public class GetProductsTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            Toast.makeText(MainActivity.this, "Fetching products.", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                Log.e("CHECKING : ", "Entered getProducts()");
                URL url = new URL("https://mahnooralicheema.000webhostapp.com/getProducts.php");
                URLConnection con = (URLConnection) url.openConnection();
                con.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                wr.flush();
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line = "";
                productsRaw = "";
                while((line = reader.readLine()) != null) {
                    productsRaw = productsRaw + line;
                }

                String productsRaw_[] = productsRaw.split("[$]");
                for(String s : productsRaw_) {
                    String s_[] = s.split("[|]");
                    if(s_.length > 1) {
                        products.add(new Product(s));
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            updateListView();
        }
    }

    public void updateListView() {
        adapter = new ProductAdapter(this, products);
        listView.setAdapter(adapter);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
