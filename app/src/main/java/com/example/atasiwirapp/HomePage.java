package com.example.atasiwirapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atasiwirapp.rv_home_wp.wpAdapter;
import com.example.atasiwirapp.rv_home_wp.wpModel;
import com.example.atasiwirapp.rv_wisata.wisataAdapter;
import com.example.atasiwirapp.rv_wisata.wisataModel;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity implements View.OnClickListener {

    RecyclerView _rvWp;
    wpAdapter wpAdapter;
    TextView _profileEdit;
    ImageButton _btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        _rvWp = findViewById(R.id.rvWp);
        _rvWp.setLayoutManager(new LinearLayoutManager(this));
        wpAdapter = new wpAdapter(this, getList());
        _rvWp.setAdapter(wpAdapter);

        _profileEdit = findViewById(R.id.profile_edit);

        _btnLogout = findViewById(R.id.logout_btn);
        _btnLogout.setOnClickListener(this);
    }

    private ArrayList<wpModel> getList() {
        ArrayList<wpModel> models = new ArrayList<>();
        wpModel m = new wpModel();
        m.setImg(R.drawable.wisata1);
        m.setNama("Gunung Bromo");
        m.setRating("4.7");
        models.add(m);

        m = new wpModel();
        m.setImg(R.drawable.wisata2);
        m.setNama("Pantai Tiga Warnah");
        m.setRating("4.3");
        models.add(m);

        m = new wpModel();
        m.setImg(R.drawable.wisata3);
        m.setNama("Candi Badut");
        m.setRating("4.3");
        models.add(m);

        m = new wpModel();
        m.setImg(R.drawable.wisata1);
        m.setNama("Gunung Bromo");
        m.setRating("4.7");
        models.add(m);

        m = new wpModel();
        m.setImg(R.drawable.wisata2);
        m.setNama("Pantai Tiga Warnah");
        m.setRating("4.3");
        models.add(m);

        m = new wpModel();
        m.setImg(R.drawable.wisata3);
        m.setNama("Candi Badut");
        m.setRating("4.3");
        models.add(m);

        m = new wpModel();
        m.setImg(R.drawable.wisata1);
        m.setNama("Gunung Bromo");
        m.setRating("4.7");
        models.add(m);

        m = new wpModel();
        m.setImg(R.drawable.wisata2);
        m.setNama("Pantai Tiga Warnah");
        m.setRating("4.3");
        models.add(m);

        m = new wpModel();
        m.setImg(R.drawable.wisata3);
        m.setNama("Candi Badut");
        m.setRating("4.3");
        models.add(m);

        return models;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == _btnAddReview.getId()) {
            Intent addReview = new Intent(this, AddReview.class);
            startActivity(addReview);
        } else if (view.getId() == _btnLogout.getId()) {
            finish();
        }
    }
}
