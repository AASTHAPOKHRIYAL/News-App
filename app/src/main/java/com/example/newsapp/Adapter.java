package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<ModelClass> modelClassArrayList;

    public Adapter(Context context, ArrayList<ModelClass> modelClassArrayList) {
        this.context = context;
        this.modelClassArrayList = modelClassArrayList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.layout, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, webView.class);
                intent.putExtra("url", modelClassArrayList.get(position).getUrl());
                context.startActivity(intent);
            }
        });

        holder.date_n_time.setText("Published at: "+modelClassArrayList.get(position).getPublishedAt());
        holder.author.setText(modelClassArrayList.get(position).getAuthor());
        holder.heading.setText(modelClassArrayList.get(position).getTitle());
        Glide.with(context).load(modelClassArrayList.get(position).getUrlToImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return modelClassArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView heading, author, date_n_time;
        CardView cardView;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            heading=itemView.findViewById(R.id.heading);
            author=itemView.findViewById(R.id.authorName);
            date_n_time=itemView.findViewById(R.id.date_n_time);
            image=itemView.findViewById(R.id.image);
            cardView=itemView.findViewById(R.id.cardView);

        }
    }
}
