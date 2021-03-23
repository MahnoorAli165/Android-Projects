package com.example.chhotay;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    List<String> titles;
    List<Integer> images;
    List<String> digits;
    List<String> item_price;
    Context context;
    LayoutInflater inflater;

    Intent intent;
    public TextView pname, price,count;
    public ImageView pimage;
    public ImageButton plus, minus;
    public int c=1;
    public int p;
    public int init_price;

    public String Uid = "";
    public CartAdapter(Context ctx, List<String> titles,List<String> digits,List<String> item_price, List<Integer> images){
        this.titles = titles;
        this.digits = digits;
        this.item_price = item_price;
        this.images = images;
        this.inflater = LayoutInflater.from(ctx);


    }

    public class ViewHolder extends RecyclerView.ViewHolder {
         TextView pname,count,price;
         ImageView pimage;
         ImageButton plus, minus;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            pname = itemView.findViewById(R.id.pname);
            count = itemView.findViewById(R.id.number);
            price = itemView.findViewById(R.id.price);
            pimage = itemView.findViewById(R.id.pimage);
            plus = itemView.findViewById(R.id.plus);
            minus = itemView.findViewById(R.id.minus);
            price = itemView.findViewById(R.id.price);


        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_card_layout, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.pname.setText(titles.get(position));
        holder.count.setText(digits.get(position));
        holder.price.setText(item_price.get(position));
        holder.pimage.setImageResource(images.get(position));
        init_price=Integer.parseInt(holder.price.getText().toString());
        context = holder.itemView.getContext();

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                c++;
                holder.count.setText(""+c);
                p = init_price;
                p=p*c;
                holder.price.setText(""+p);

            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c <= 1) {
                    c = 1;
                    p = init_price;
                    holder.count.setText("" + c);
                    holder.price.setText("" + p);
                } else {
                    c--;
                    holder.count.setText("" + c);
                    p = init_price;
                    p = p * c;
                    holder.price.setText("" + p);

                }
            }

        });




    }



    @Override
    public int getItemCount() {
        return titles.size();
    }
}






