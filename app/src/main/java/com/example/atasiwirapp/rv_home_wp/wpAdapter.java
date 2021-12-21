package com.example.atasiwirapp.rv_home_wp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atasiwirapp.R;
import com.example.atasiwirapp.Wisata;
import com.example.atasiwirapp.rv_wisata.wisataClickListener;

import java.io.ByteArrayOutputStream;
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

        holder.setWpClickListener(new wpClickListener() {
            @Override
            public void onWpClickListener(View v, int position) {
                String gNama = models.get(position).getNama();
                String gRating = models.get(position).getRating();
                String gDesc = models.get(position).getDesc();
                BitmapDrawable bitmapDrawable = (BitmapDrawable)holder._imgWp.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] bytes = stream.toByteArray();

                Intent intent = new Intent(context, Wisata.class);
                intent.putExtra("wTitle", gNama);
                intent.putExtra("wRating", gRating);
                intent.putExtra("wDesc", gDesc);
                intent.putExtra("wImg", bytes);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
