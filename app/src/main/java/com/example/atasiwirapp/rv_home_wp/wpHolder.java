package com.example.atasiwirapp.rv_home_wp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atasiwirapp.R;
import com.example.atasiwirapp.rv_wisata.wisataClickListener;

public class wpHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView _imgWp;
    TextView _namaWp, _ratingWp, _descWp;
    wpClickListener wpClickListener;

    wpHolder(@NonNull View itemView) {
        super(itemView);

        this._imgWp = itemView.findViewById(R.id.imgWp);
        this._namaWp = itemView.findViewById(R.id.namaWp);
        this._ratingWp = itemView.findViewById(R.id.ratingWp);
        this._descWp = itemView.findViewById(R.id.descWp);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        this.wpClickListener.onWpClickListener(view, getLayoutPosition());
    }

    public void setWpClickListener(wpClickListener wc) {
        this.wpClickListener = wc;
    }
}
