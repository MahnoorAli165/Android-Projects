package com.example.daraz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SecondActivity extends AppCompatActivity implements TopFragment.OnFragmentInteractionListener, SecondFragment.OnFragmentInteractionListener, View.OnClickListener {

    private TopFragment topFragment;
    private SecondFragment secondFragment;
    private Product product;
    private Customer customer;
    private EditText editTextName;
    private EditText editTextPhone;
    private ImageView imageView;
    private TextView textViewID;
    private TextView textViewName;
    private TextView textViewPrice;
    private TextView textViewDesc;
    private TextView textViewQty;
    private String quantity;
    private TextView textViewAmount;
    private String amount;
    private Button buttonPlus;
    private Button buttonMinus;
    private Button buttonCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        topFragment = (TopFragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        secondFragment = (SecondFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentSecond);

        editTextName = (EditText) topFragment.getView().findViewById(R.id.editTextName);
        editTextPhone = (EditText) topFragment.getView().findViewById(R.id.editTextPhone);
        imageView = (ImageView) secondFragment.getView().findViewById(R.id.imageViewProduct);
        textViewID = (TextView) secondFragment.getView().findViewById(R.id.textViewProductID);
        textViewName = (TextView) secondFragment.getView().findViewById(R.id.textViewProductName);
        textViewPrice = (TextView) secondFragment.getView().findViewById(R.id.textViewProductPrice);
        textViewDesc = (TextView) secondFragment.getView().findViewById(R.id.textViewProductDesc);
        textViewQty = (TextView) secondFragment.getView().findViewById(R.id.textViewProductQty);
        textViewQty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(textViewQty.getText().toString().equals("")) {

                } else {
                    int qty = Integer.parseInt(textViewQty.getText().toString());
                    qty *= product.getPrice();
                    textViewAmount.setText("Amount : " + qty);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        textViewAmount = (TextView) secondFragment.getView().findViewById(R.id.textViewOrderAmount);
        buttonPlus = (Button) secondFragment.getView().findViewById(R.id.buttonPlus);
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty = Integer.parseInt(textViewQty.getText().toString());
                qty++;
                textViewQty.setText(qty + "");
            }
        });
        buttonMinus = (Button) secondFragment.getView().findViewById(R.id.buttonMinus);
        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty = Integer.parseInt(textViewQty.getText().toString());
                if(qty > 1) {
                    qty--;
                    textViewQty.setText(qty + "");
                }
            }
        });
        buttonCheckout = (Button) secondFragment.getView().findViewById(R.id.buttonCheckout);
        buttonCheckout.setOnClickListener(this);

        Intent intent = getIntent();
        editTextName.setText(intent.getStringExtra("name"));
        editTextPhone.setText(intent.getStringExtra("phone"));
        customer = new Customer(intent.getStringExtra("name"), intent.getStringExtra("phone"));
        product = (Product) intent.getSerializableExtra("product");
        textViewID.setText("ID : " + product.getID());
        textViewName.setText("Name : " + product.getName());
        textViewPrice.setText("Price : " + product.getPrice() + "");
        textViewDesc.setText("Description : " + product.getDescription());
        textViewQty.setText("1");
        textViewAmount.setText("Amount : " + product.getPrice() + "");
        new DownloadImage().execute();
    }

    public class DownloadImage extends AsyncTask<Void, Void, Void> {

        final Bitmap[] bm = new Bitmap[1];

        @Override
        protected Void doInBackground(Void... voids) {
            try{
                InputStream in = new URL(product.getImageLink()).openStream();
                bm[0] = BitmapFactory.decodeStream(in);
            } catch (Exception e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(bm[0] != null) {
                imageView.setImageBitmap(bm[0]);
            }
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == buttonCheckout.getId()) {
            if(!editTextName.getText().toString().equals("") || !editTextPhone.getText().toString().equals("")) {
                customer.setName(editTextName.getText().toString());
                customer.setPhone(editTextPhone.getText().toString());
                if (Integer.parseInt(textViewQty.getText().toString()) <= product.getStock() && product.getStock() > 0) {
                    quantity = textViewQty.getText().toString();
                    amount = (Integer.parseInt(quantity) * product.getPrice()) + "";
                    buttonCheckout.setEnabled(false);
                    new AsyncTask<Void, Void, Void>() {

                        String Complete_line;

                        @Override
                        protected Void doInBackground(Void... voids) {
                            try {
                                Log.e("CHECKING", "Async Tast");
                                String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(customer.getName(), "UTF-8") +
                                        "&" + URLEncoder.encode("cust_phone", "UTF-8") + "=" + URLEncoder.encode(customer.getPhone(), "UTF-8") +
                                        "&" + URLEncoder.encode("prod_id", "UTF-8") + "=" + URLEncoder.encode(product.getID() + "", "UTF-8") +
                                        "&" + URLEncoder.encode("prod_name", "UTF-8") + "=" + URLEncoder.encode(product.getName(), "UTF-8") +
                                        "&" + URLEncoder.encode("qty", "UTF-8") + "=" + URLEncoder.encode(quantity, "UTF-8") +
                                        "&" + URLEncoder.encode("price", "UTF-8") + "=" + URLEncoder.encode(product.getPrice() + "", "UTF-8") +
                                        "&" + URLEncoder.encode("amount", "UTF-8") + "=" + URLEncoder.encode(amount, "UTF-8");
                                Log.e("CHECKING", data);
                                URL url = new URL("https://mahnooralicheema.000webhostapp.com/checkout.php");
                                Log.e("CHECKING", "Connection");
                                URLConnection con = (URLConnection) url.openConnection();
                                con.setDoOutput(true);
                                OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                                wr.write(data);
                                wr.flush();

                                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                                String line = "";
                                Complete_line = "";
// Read Server Response
                                while ((line = reader.readLine()) != null) {
                                    Complete_line = Complete_line + line;
                                    break;
                                }
                                Complete_line.trim();
                                Log.e("CHECKING", Complete_line);
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e("CHECKING", e.getMessage());
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            if (":".equals(Complete_line.charAt(Complete_line.length() - 1) + "")) {
                                SecondActivity.super.finish();
                                Toast.makeText(getApplicationContext(), "Checkout Successful!", Toast.LENGTH_SHORT).show();
                            } else {
                                SecondActivity.super.finish();
                                Toast.makeText(getApplicationContext(), "There was an error while checkout.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }.execute();
                } else {
                    Toast.makeText(this, "Qty more than stock.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Please enter name and phone.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
