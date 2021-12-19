package com.example.atasiwirapp.rv_wisata;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atasiwirapp.R;
import com.example.atasiwirapp.Wisata;

import java.io.ByteArrayOutputStream;
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

        holder.setWisataClickListener(new wisataClickListener() {
            @Override
            public void onWisataClickListener(View v, int position) {
                String gTitle = models.get(position).getTitle();
                String gRating = models.get(position).getRating();
                String gDesc = models.get(position).getDesc();

                Intent intent = new Intent(c, Wisata.class);
                intent.putExtra("wTitle", gTitle);
                intent.putExtra("wRating", gRating);
                intent.putExtra("wDesc", gDesc);
                c.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
