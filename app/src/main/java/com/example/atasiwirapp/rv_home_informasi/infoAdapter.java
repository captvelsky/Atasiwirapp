package com.example.atasiwirapp.rv_home_informasi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atasiwirapp.R;

import java.util.ArrayList;

public class infoAdapter extends RecyclerView.Adapter<infoHolder> {

    Context context;
    ArrayList<infoModel> models;

    public infoAdapter(Context context, ArrayList<infoModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public infoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_informasi, null);
        return new infoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull infoHolder holder, int position) {
        holder._imgInfo.setImageResource(models.get(position).getImg());
        holder._descInfo.setText(models.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}

