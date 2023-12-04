package com.example.mybird;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList bird_id, bird_img_title, img; //opzich hebben we overal alleen maar de opgeslagen value img uit database nodig maar da maakt niet uit
    private List<String> uniqueTitles;



    CustomAdapter(Context context, ArrayList bird_id, ArrayList bird_img_title, ArrayList img) {
        this.context = context;
        this.bird_id = bird_id;
        this.bird_img_title = bird_img_title;
        this.img = img;
        this.uniqueTitles = new ArrayList<>();


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        holder.img_iv.setText(String.valueOf(bird_id.get(position)));
        holder.bird_id_txt.setText(String.valueOf(bird_id.get(position)));
        //maak een legen array die alle titles stored want die zijn toch het zelfde
        //make a arraylist that stores the bird_img_title
        //and then make a array that checks of the bird_img_title is more then one's in the array.
        String currentTitle = String.valueOf(bird_img_title.get(position));
        // Check if the title is already in the uniqueTitles list
        if (!uniqueTitles.contains(currentTitle)) {
            // If not, add it to the list and perform any desired action
            uniqueTitles.add(currentTitle);

//            holder.bird_id_txt.setText(String.valueOf(bird_id.get(position)));
            holder.bird_id_txt.setText(String.valueOf(uniqueTitles.size()));

            String imageName = String.valueOf(img.get(position));
            int imageResourceId = context.getResources().getIdentifier(
                    imageName, "drawable", context.getPackageName());
            holder.img_iv.setImageResource(imageResourceId);
        } else {
            // If the title is a duplicate, you can handle it here (e.g., hide the view)
//            holder.bird_id_txt.setVisibility(View.GONE);
//            holder.img_iv.setVisibility(View.GONE);

            //make sure that in this else you delete the empty holder

            // If the title is a duplicate, remove the item from the dataset
//            bird_id.remove(position);
//            bird_img_title.remove(position);
//            img.remove(position);

            // Notify the adapter of the removal
//            notifyItemRemoved(position);

            // Update the uniqueTitles list accordingly
//            uniqueTitles.remove(currentTitle);
        }
    }


    @Override
    public int getItemCount() {
        return bird_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView img_iv;
        TextView bird_id_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bird_id_txt = itemView.findViewById(R.id.bird_id_txt);
            img_iv = itemView.findViewById(R.id.img_iv);


        }
    }
}
