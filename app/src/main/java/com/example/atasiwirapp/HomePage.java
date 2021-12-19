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
import com.example.atasiwirapp.rv_home_informasi.infoAdapter;
import com.example.atasiwirapp.rv_home_informasi.infoModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity implements View.OnClickListener {

    RecyclerView _rvWp, _rvInfo;
    wpAdapter wpAdapter;
    infoAdapter infoAdapter;
    TextView _profileEdit;
    ImageButton _btnLogout, _btnWisata, _btnHotel, _btnKuliner, _btnSuvenir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        _rvWp = findViewById(R.id.rvWp);
        _rvWp.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        wpAdapter = new wpAdapter(this, getListWp());
        _rvWp.setAdapter(wpAdapter);

        _rvInfo = findViewById(R.id.rvInfo);
        _rvInfo.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        infoAdapter = new infoAdapter(this, getListInfo());
        _rvInfo.setAdapter(infoAdapter);

        _profileEdit = findViewById(R.id.profile_edit);
        _profileEdit.setOnClickListener(this);

        _btnWisata = findViewById(R.id.btnWisata);
        _btnWisata.setOnClickListener(this);
        _btnHotel = findViewById(R.id.btnHotel);
        _btnHotel.setOnClickListener(this);
        _btnKuliner = findViewById(R.id.btnKuliner);
        _btnKuliner.setOnClickListener(this);
        _btnSuvenir = findViewById(R.id.btnSuvenir);
        _btnSuvenir.setOnClickListener(this);

        _btnLogout = findViewById(R.id.logout_btn);
        _btnLogout.setOnClickListener(this);

    }

    private ArrayList<wpModel> getListWp() {
        ArrayList<wpModel> models = new ArrayList<>();
        wpModel m = new wpModel();
        m.setImg(R.drawable.wisata_bromo);
        m.setNama("Gunung Bromo");
        m.setRating("4.7");
        m.setDesc("Gunung Bromo adalah sebuah gunung berapi aktif yang  terkenal sebagai objek wisata utama di Jawa Timur.");
        models.add(m);

        m = new wpModel();
        m.setImg(R.drawable.wisata_pantai_tiga_warna);
        m.setNama("Pantai Tiga Warnah");
        m.setRating("4.3");
        m.setDesc("Pantai yang memiliki gradasi tiga warna yang disebabkan oleh perbedaan kedalaman permukaannya.");
        models.add(m);

        m = new wpModel();
        m.setImg(R.drawable.wisata_candi_badut);
        m.setNama("Candi Badut");
        m.setRating("4.3");
        m.setDesc("Candi Badut adalah sebuah candi yang terletak di kawasan Tidar, di bagian barat kota Malang.");
        models.add(m);

        m = new wpModel();
        m.setImg(R.drawable.wisata_bromo);
        m.setNama("Gunung Bromo");
        m.setRating("4.7");
        m.setDesc("Gunung Bromo adalah sebuah gunung berapi aktif yang  terkenal sebagai objek wisata utama di Jawa Timur.");
        models.add(m);

        m = new wpModel();
        m.setImg(R.drawable.wisata_pantai_tiga_warna);
        m.setNama("Pantai Tiga Warnah");
        m.setRating("4.3");
        m.setDesc("Pantai yang memiliki gradasi tiga warna yang disebabkan oleh perbedaan kedalaman permukaannya.");
        models.add(m);

        m = new wpModel();
        m.setImg(R.drawable.wisata_candi_badut);
        m.setNama("Candi Badut");
        m.setRating("4.3");
        m.setDesc("Candi Badut adalah sebuah candi yang terletak di kawasan Tidar, di bagian barat kota Malang.");
        models.add(m);

        m = new wpModel();
        m.setImg(R.drawable.wisata_bromo);
        m.setNama("Gunung Bromo");
        m.setRating("4.7");
        m.setDesc("Gunung Bromo adalah sebuah gunung berapi aktif yang  terkenal sebagai objek wisata utama di Jawa Timur.");
        models.add(m);

        m = new wpModel();
        m.setImg(R.drawable.wisata_pantai_tiga_warna);
        m.setNama("Pantai Tiga Warnah");
        m.setRating("4.3");
        m.setDesc("Pantai yang memiliki gradasi tiga warna yang disebabkan oleh perbedaan kedalaman permukaannya.");
        models.add(m);

        m = new wpModel();
        m.setImg(R.drawable.wisata_candi_badut);
        m.setNama("Candi Badut");
        m.setRating("4.3");
        m.setDesc("Candi Badut adalah sebuah candi yang terletak di kawasan Tidar, di bagian barat kota Malang.");
        models.add(m);

        return models;
    }

    private ArrayList<infoModel> getListInfo() {
        ArrayList<infoModel> models = new ArrayList<>();
        infoModel m = new infoModel();
        m.setImg(R.drawable.wisata_bromo);
        m.setDesc("Gunung Bromo ada sebuah gunung berapi aktif di Jawa Timur");
        models.add(m);

        m = new infoModel();
        m.setImg(R.drawable.wisata_pantai_tiga_warna);
        m.setDesc("qwertyuiop[asdfghjklzxcvbnm,qwertydfgcvtyghjdjincsonsaodcn");
        models.add(m);

        m = new infoModel();
        m.setImg(R.drawable.wisata_candi_badut);
        m.setDesc("Lorem ipsum dolor sit amet.");
        models.add(m);

        return models;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == _profileEdit.getId()) {
            Intent editProfile = new Intent(this, EditProfile.class);
            startActivity(editProfile);
        } else if (view.getId() == _btnHotel.getId()) {
            Intent comingSoon = new Intent(this, ComingSoon.class);
            startActivity(comingSoon);
        } else if (view.getId() == _btnKuliner.getId()) {
            Intent comingSoon = new Intent(this, ComingSoon.class);
            startActivity(comingSoon);
        } else if (view.getId() == _btnSuvenir.getId()) {
            Intent comingSoon = new Intent(this, ComingSoon.class);
            startActivity(comingSoon);
        }else if (view.getId() == _btnWisata.getId()) {
            Intent menuWisata = new Intent(this, MenuWisata.class);
            startActivity(menuWisata);
        } else if (view.getId() == _btnLogout.getId()) {
            FirebaseAuth.getInstance().signOut();
            Intent logout = new Intent(this, MainActivity.class);
            startActivity(logout);
            finish();
        }
    }
}

/* */