package com.example.daraz;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {

    private final Activity context;
    private final ArrayList<Product> products;

    public ProductAdapter(Activity context, ArrayList<Product> products) {
        super(context, R.layout.product_list_item, products);
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View view, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        final View rowView = inflater.inflate(R.layout.product_list_item, null, true);
        TextView textViewID = rowView.findViewById(R.id.textViewID);
        TextView textViewName = rowView.findViewById(R.id.textViewName);
        TextView textViewPrice = rowView.findViewById(R.id.textViewPrice);
        textViewID.setText(products.get(position).getID() +"");
        textViewName.setText(products.get(position).getName() +"");
        textViewPrice.setText(products.get(position).getPrice() +"");
        final Bitmap[] bm = new Bitmap[1];
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try{
                    InputStream in = new URL(products.get(position).getImageLink()).openStream();
                    bm[0] = BitmapFactory.decodeStream(in);
                } catch (Exception e) {

                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                if(bm[0] != null) {
                    ((ImageView)rowView.findViewById(R.id.imageView)).setImageBitmap(bm[0]);
                }
            }
        }.execute();
        return rowView;
    }



}
