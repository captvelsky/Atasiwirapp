package com.example.atasiwirapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView listRecyclerView;
    listAdapter listAdapter;
    ImageView asu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_wisata);

        asu = findViewById(R.id.imageView);
        listRecyclerView = findViewById(R.id.recyclerView);
        listRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        listAdapter = new listAdapter(this, getList());
        listRecyclerView.setAdapter(listAdapter);
    }

    private ArrayList<listModel> getList() {
        ArrayList<listModel> models = new ArrayList<>();
        listModel m = new listModel();
        m.setImg(R.drawable.wisata1);
        m.setTitle("Gunung Bromo");
        m.setRating("4.7");
        m.setDesc("Gunung Bromo adalah sebuah gunung berapi aktif yang  terkenal sebagai objek wisata utama di Jawa Timur.");
        models.add(m);

        m = new listModel();
        m.setImg(R.drawable.wisata2);
        m.setTitle("Pantai Tiga Warnah");
        m.setRating("4.3");
        m.setDesc("Pantai Tiga Warna adalah pantai yang memiliki gradasi tiga warna yang disebabkan oleh perbedaan kedalaman permukaannya.");
        models.add(m);

        m = new listModel();
        m.setImg(R.drawable.wisata3);
        m.setTitle("Candi Badut");
        m.setRating("4.3");
        m.setDesc("Candi Badut adalah sebuah candi yang terletak di kawasan Tidar, di bagian barat kota Malang.");
        models.add(m);

        return models;
    }
}