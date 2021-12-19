package com.example.atasiwirapp.rv_home_wp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atasiwirapp.R;

import java.util.ArrayList;

public class wpAdapter extends RecyclerView.Adapter<wpHolder>{

    Context context;
    ArrayList<wpModel> models;

    public wpAdapter(Context context, ArrayList<wpModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public wpHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_wisata_populer, null);
        return new wpHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull wpHolder holder, int position) {
        holder._imgWp.setImageResource(models.get(position).getImg());
        holder._namaWp.setText(models.get(position).getNama());
        holder._ratingWp.setText(models.get(position).getRating());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
