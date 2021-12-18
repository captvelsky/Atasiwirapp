package com.example.atasiwirapp.rv_review;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atasiwirapp.R;

import java.util.ArrayList;

public class reviewAdapter extends RecyclerView.Adapter<reviewHolder>{

    Context context;
    ArrayList<reviewModel> models;

    public reviewAdapter(Context context, ArrayList<reviewModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public reviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_review, null);
        return new reviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull reviewHolder holder, int position) {
        holder._reviewName.setText(models.get(position).getName());
        holder._reviewDesc.setText(models.get(position).getReview());
        holder._reviewRating.setText(models.get(position).getRating());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
