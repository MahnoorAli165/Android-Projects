package com.example.chhotay;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CurrentOrderAdapter extends RecyclerView.Adapter<CurrentOrderAdapter.ViewHolder> {

    List<String> order_number;
    List<Integer> images;
    List<String> order_date;
    List<String> pstatus;
    Context context;
    LayoutInflater inflater;

    Intent intent;
    public TextView status,order_no, orderDate;


    public String Uid = "";
    public CurrentOrderAdapter(Context ctx, List<String> order_number,List<Integer> images,List<String> order_date,List<String> pstatus){
        this.order_number = order_number;
        this.images = images;
        this.order_date = order_date;
        this.pstatus = pstatus;
        this.inflater = LayoutInflater.from(ctx);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView order_no, orderDate, status;
        ImageView pimage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            order_no = itemView.findViewById(R.id.order_no);
            orderDate = itemView.findViewById(R.id.orderDate);
            status = itemView.findViewById(R.id.status);
            pimage = itemView.findViewById(R.id.pimage);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_card_layout, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.order_no.setText(order_number.get(position));
        holder.pimage.setImageResource(images.get(position));
        holder.orderDate.setText(order_date.get(position));
        holder.status.setText(pstatus.get(position));

//        init_price=Integer.parseInt(holder.price.getText().toString());
        context = holder.itemView.getContext();

    }
    @Override
    public int getItemCount() {
        return order_number.size();
    }
}
