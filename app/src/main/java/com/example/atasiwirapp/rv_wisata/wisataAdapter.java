package com.example.atasiwirapp.rv_wisata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atasiwirapp.R;

import java.util.ArrayList;

public class wisataAdapter extends RecyclerView.Adapter<wisataHolder> {

    Context c;
    ArrayList<wisataModel> models;

    public wisataAdapter(Context c, ArrayList<wisataModel> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public wisataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_wisata, null);
        return new wisataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull wisataHolder holder, int position) {
        holder._imgWisataPic.setImageResource(models.get(position).getImg());
        holder._wisataTitle.setText(models.get(position).getTitle());
        holder._wisataRating.setText(models.get(position).getRating());
        holder._wisataDesc.setText(models.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
