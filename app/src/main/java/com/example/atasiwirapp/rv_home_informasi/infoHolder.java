package com.example.atasiwirapp.rv_home_informasi;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atasiwirapp.R;

public class infoHolder extends RecyclerView.ViewHolder {

    ImageView _imgInfo;
    TextView _descInfo;

    infoHolder(@NonNull View itemView) {
        super(itemView);

        this._imgInfo = itemView.findViewById(R.id.imgInfo);
        this._descInfo = itemView.findViewById(R.id.descInfo);
    }
}