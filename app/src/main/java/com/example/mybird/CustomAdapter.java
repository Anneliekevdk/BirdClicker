package com.example.mybird;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList bird_id, bird_img_title, img; //opzich hebben we overal alleen maar de opgeslagen value img uit database nodig maar da maakt niet uit

    CustomAdapter(Context context, ArrayList bird_id, ArrayList bird_img_title, ArrayList img){
        this.context = context;
        this.bird_id = bird_id;
        this.bird_img_title = bird_img_title;
        this.img = img;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view  = inflater.inflate(R.layout.my_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        holder.img_iv.setText(String.valueOf(bird_id.get(position)));
        holder.bird_id_txt.setText(String.valueOf(bird_id.get(position)));
    }


    @Override
    public int getItemCount() {
        return bird_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

         ImageView img_iv;
         TextView bird_id_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bird_id_txt = itemView.findViewById(R.id.bird_id_txt);
            img_iv = itemView.findViewById(R.id.img_iv);

        }
    }
}
