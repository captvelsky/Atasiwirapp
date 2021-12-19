package com.example.atasiwirapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.atasiwirapp.rv_wisata.wisataAdapter;
import com.example.atasiwirapp.rv_wisata.wisataModel;

import java.util.ArrayList;

public class MenuWisata extends AppCompatActivity {

    RecyclerView _rvWisata;
    com.example.atasiwirapp.rv_wisata.wisataAdapter wisataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_wisata);

        _rvWisata = findViewById(R.id.rvWisata);
        _rvWisata.setLayoutManager(new LinearLayoutManager(this));
        wisataAdapter = new wisataAdapter(this, getList());
        _rvWisata.setAdapter(wisataAdapter);
    }

    private ArrayList<wisataModel> getList() {
        ArrayList<wisataModel> models = new ArrayList<>();
        wisataModel m = new wisataModel();
        m.setImg(R.drawable.wisata_bromo);
        m.setTitle("Gunung Bromo");
        m.setRating("4.7");
        m.setDesc("Gunung Bromo adalah sebuah gunung berapi aktif yang  terkenal sebagai objek wisata utama di Jawa Timur.");
        models.add(m);

        m = new wisataModel();
        m.setImg(R.drawable.wisata_pantai_tiga_warna);
        m.setTitle("Pantai Tiga Warnah");
        m.setRating("4.3");
        m.setDesc("Pantai yang memiliki gradasi tiga warna yang disebabkan oleh perbedaan kedalaman permukaannya.");
        models.add(m);

        m = new wisataModel();
        m.setImg(R.drawable.wisata_candi_badut);
        m.setTitle("Candi Badut");
        m.setRating("4.3");
        m.setDesc("Candi Badut adalah sebuah candi yang terletak di kawasan Tidar, di bagian barat kota Malang.");
        models.add(m);

        m = new wisataModel();
        m.setImg(R.drawable.wisata_bromo);
        m.setTitle("Gunung Bromo");
        m.setRating("4.7");
        m.setDesc("Gunung Bromo adalah sebuah gunung berapi aktif yang  terkenal sebagai objek wisata utama di Jawa Timur.");
        models.add(m);

        m = new wisataModel();
        m.setImg(R.drawable.wisata_pantai_tiga_warna);
        m.setTitle("Pantai Tiga Warnah");
        m.setRating("4.3");
        m.setDesc("Pantai yang memiliki gradasi tiga warna yang disebabkan oleh perbedaan kedalaman permukaannya.");
        models.add(m);

        m = new wisataModel();
        m.setImg(R.drawable.wisata_candi_badut);
        m.setTitle("Candi Badut");
        m.setRating("4.3");
        m.setDesc("Candi Badut adalah sebuah candi yang terletak di kawasan Tidar, di bagian barat kota Malang.");
        models.add(m);

        m = new wisataModel();
        m.setImg(R.drawable.wisata_bromo);
        m.setTitle("Gunung Bromo");
        m.setRating("4.7");
        m.setDesc("Gunung Bromo adalah sebuah gunung berapi aktif yang  terkenal sebagai objek wisata utama di Jawa Timur.");
        models.add(m);

        m = new wisataModel();
        m.setImg(R.drawable.wisata_pantai_tiga_warna);
        m.setTitle("Pantai Tiga Warnah");
        m.setRating("4.3");
        m.setDesc("Pantai yang memiliki gradasi tiga warna yang disebabkan oleh perbedaan kedalaman permukaannya.");
        models.add(m);

        m = new wisataModel();
        m.setImg(R.drawable.wisata_candi_badut);
        m.setTitle("Candi Badut");
        m.setRating("4.3");
        m.setDesc("Candi Badut adalah sebuah candi yang terletak di kawasan Tidar, di bagian barat kota Malang.");
        models.add(m);

        return models;
    }
}