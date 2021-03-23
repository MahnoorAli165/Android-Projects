package com.example.chhotay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.FutureTask;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<String> titles;
    List<Integer> images;
    Context context;
    LayoutInflater inflater;
    TextView txt1;
    ImageView img1;
    Intent intent;
    private FutureTask info;
    public String Uid = "";

    public Adapter(Context ctx, List<String> titles, List<Integer> images){
        this.titles = titles;
        this.images = images;
        this.inflater = LayoutInflater.from(ctx);


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view  = inflater.inflate(R.layout.custom_grid_layout,parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_grid_layout, parent, false);
        txt1 = (TextView) view.findViewById(R.id.pname);
        img1 = (ImageView) view.findViewById(R.id.imageView2);
//        txt1.setOnClickListener((View.OnClickListener) this);
//        img1.setOnClickListener((View.OnClickListener) this);


        return new ViewHolder(view);
    }






    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.title.setText(titles.get(position));
        holder.gridIcon.setImageResource(images.get(position));
        context = holder.itemView.getContext();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passingdata = txt1.getText().toString();
                Intent i = new Intent(context, ItemDetailActivity.class);
                Bundle b = new Bundle();
                b.putString("pname", passingdata);
                i.putExtras(b);
                context.startActivity(i);


            }
        });


    }


    @Override
    public int getItemCount() {
        return titles.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView gridIcon;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            title = itemView.findViewById(R.id.pname);
            gridIcon = itemView.findViewById(R.id.imageView2);
        }
    }

}
