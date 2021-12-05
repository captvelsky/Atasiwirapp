package com.example.atasiwirapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class listAdapter extends RecyclerView.Adapter<listHolder> {

    Context c;
    ArrayList<listModel> models;

    public listAdapter(Context c, ArrayList<listModel> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public listHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_wisata, null);
        return new listHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull listHolder holder, int position) {
        holder.listImgView.setImageResource(models.get(position).getImg());
        holder.listTitle.setText(models.get(position).getTitle());
        holder.listRating.setText(models.get(position).getRating());
        holder.listDesc.setText(models.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
