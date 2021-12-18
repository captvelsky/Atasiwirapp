package com.example.atasiwirapp.rv_review;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atasiwirapp.R;

public class reviewHolder extends RecyclerView.ViewHolder {

    TextView _reviewName, _reviewDesc, _reviewRating;

    public reviewHolder(@NonNull View itemView) {
        super(itemView);

        this._reviewName = itemView.findViewById(R.id.reviewName);
        this._reviewDesc = itemView.findViewById(R.id.reviewDesc);
        this._reviewRating = itemView.findViewById(R.id.reviewRating);
    }
}
