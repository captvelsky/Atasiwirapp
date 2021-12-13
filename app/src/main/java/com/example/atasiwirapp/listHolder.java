package com.example.atasiwirapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class listHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView listImgView;
    TextView listTitle, listRating, listDesc;
    ListClickListener listClickListener;

    listHolder(@NonNull View itemView) {
        super(itemView);

        this.listImgView = itemView.findViewById(R.id.cardImg);
        this.listTitle = itemView.findViewById(R.id.cardTitle);
        this.listRating = itemView.findViewById(R.id.cardRating);
        this.listDesc = itemView.findViewById(R.id.cardDesc);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.listClickListener.onListClickListener(v, getLayoutPosition());
    }

    public void setListClickListener(ListClickListener lc) {
        this.listClickListener = lc;
    }
}
