package com.example.atasiwirapp.rv_home_wp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atasiwirapp.R;

public class wpHolder extends RecyclerView.ViewHolder{
    ImageView _imgWp;
    TextView _namaWp, _ratingWp;

    wpHolder(@NonNull View itemView) {
        super(itemView);

        this._imgWp = itemView.findViewById(R.id.imgWp);
        this._namaWp = itemView.findViewById(R.id.namaWp);
        this._ratingWp = itemView.findViewById(R.id.ratingWp);

    }
}
