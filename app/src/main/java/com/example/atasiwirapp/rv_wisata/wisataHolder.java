package com.example.atasiwirapp.rv_wisata;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atasiwirapp.R;

public class wisataHolder extends RecyclerView.ViewHolder {

    ImageView _imgWisataPic;
    TextView _wisataTitle, _wisataRating, _wisataDesc;

    public wisataHolder(@NonNull View itemView) {
        super(itemView);

        this._imgWisataPic = itemView.findViewById(R.id.imgMenuWisataPic);
        this._wisataTitle = itemView.findViewById(R.id.wisataTitle);
        this._wisataRating = itemView.findViewById(R.id.wisataRating);
        this._wisataDesc = itemView.findViewById(R.id.wisataDesc);
    }
}
