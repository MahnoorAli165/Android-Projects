package com.example.daraz;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class OrderAdapter extends ArrayAdapter<Order> {

    private Activity context;
    private ArrayList<Order> orders;

    public OrderAdapter(Activity context, ArrayList<Order> orders) {
        super(context, R.layout.order_list_item, orders);
        this.context = context;
        this.orders = orders;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        final View rowView = inflater.inflate(R.layout.order_list_item, null, true);
        TextView textViewId = rowView.findViewById(R.id.textviewOrderID);
        textViewId.setText(orders.get(position).getId());

        TextView textViewName = rowView.findViewById(R.id.textViewOrderProductName);
        textViewName.setText(orders.get(position).getPrName());

        TextView textViewProdId = rowView.findViewById(R.id.textViewOrderProductID);
        textViewProdId.setText(orders.get(position).getPrId());

        TextView textViewDate = rowView.findViewById(R.id.textViewOrderDate);
        textViewDate.setText(orders.get(position).getDate());

        TextView textViewPrice = rowView.findViewById(R.id.textViewOrderPrice);
        textViewPrice.setText(orders.get(position).getPrice());

        TextView textViewQty = rowView.findViewById(R.id.textViewOrderQty);
        textViewQty.setText(orders.get(position).getQty());

        TextView textViewAmount = rowView.findViewById(R.id.textViewOrderAmount);
        textViewAmount.setText(orders.get(position).getAmount());

        return rowView;
    }
}
