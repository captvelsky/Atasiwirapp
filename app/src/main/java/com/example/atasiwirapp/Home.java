package com.example.atasiwirapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.atasiwirapp.rv_home_wp.wpAdapter;
import com.example.atasiwirapp.rv_home_wp.wpModel;
import com.example.atasiwirapp.rv_home_informasi.infoAdapter;
import com.example.atasiwirapp.rv_home_informasi.infoModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity implements View.OnClickListener {

    RecyclerView _rvWp, _rvInfo;
    wpAdapter wpAdapter;
    infoAdapter infoAdapter;
    TextView _profileEdit, _profileName, _profileEmail, _lihatSemuaInfo;
    ImageView _imgProfilePic;
    ImageButton _btnLogout, _btnWisata, _btnHotel, _btnKuliner, _btnSuvenir;

    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        _imgProfilePic = findViewById(R.id.imgProfilePic);
        _profileName = findViewById(R.id.profile_name);
        _profileEmail = findViewById(R.id.profile_email);

        _rvWp = findViewById(R.id.rvWp);
        _rvWp.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        wpAdapter = new wpAdapter(this, getListWp());
        _rvWp.setAdapter(wpAdapter);

        _rvInfo = findViewById(R.id.rvInfo);
        _rvInfo.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        infoAdapter = new infoAdapter(this, getListInfo());
        _rvInfo.setAdapter(infoAdapter);

        _lihatSemuaInfo = findViewById(R.id.lihatSemuaInfo);
        _lihatSemuaInfo.setOnClickListener(this);
        _profileEdit = findViewById(R.id.profile_edit);
        _profileEdit.setOnClickListener(this);

        _btnWisata = findViewById(R.id.btnHomeWisata);
        _btnWisata.setOnClickListener(this);
        _btnHotel = findViewById(R.id.btnHomeHotel);
        _btnHotel.setOnClickListener(this);
        _btnKuliner = findViewById(R.id.btnHomeKuliner);
        _btnKuliner.setOnClickListener(this);
        _btnSuvenir = findViewById(R.id.btnHomeSuvenir);
        _btnSuvenir.setOnClickListener(this);

        _btnLogout = findViewById(R.id.logout_btn);
        _btnLogout.setOnClickListener(this);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            _profileEmail.setText(user.getEmail());
            _profileName.setText(user.getDisplayName());

            mStorageRef = FirebaseStorage.getInstance().getReference();
            StorageReference fotoRef = mStorageRef.child(user.getUid() + "/profilepic");

            Task<ListResult> listPageTask = fotoRef.list(1);
            listPageTask.addOnSuccessListener(new OnSuccessListener<ListResult>() {
                @Override
                public void onSuccess(ListResult listResult) {
                    List<StorageReference> items = listResult.getItems();
                    if (!items.isEmpty()) { // Glide
                        items.get(0).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Glide.with(getApplicationContext()).load(uri).centerCrop().into(_imgProfilePic);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                            }
                        });
                    } else {
                        Toast.makeText(Home.this, "Foto profil belum ada", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Home.this, "Error, " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
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

        return models;
    }

    private ArrayList<infoModel> getListInfo() {
        ArrayList<infoModel> models = new ArrayList<>();
        infoModel m = new infoModel();
        m.setImg(R.drawable.informasi_1);
        m.setDesc("Gunung Bromo tidak menerima turis dari tanggal 21 Desember 2021 hingga 9 Januari 2022. ");
        models.add(m);

        m = new infoModel();
        m.setImg(R.drawable.informasi_2);
        m.setDesc("PPKM Jawa Bali pada akhir tahun 2021 dibatalkan.");
        models.add(m);

        m = new infoModel();
        m.setImg(R.drawable.informasi_3);
        m.setDesc("Berdasarkan peraturan pemerintah, semua pengunjung wajib menggunakan masker.");
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
        } else if (view.getId() == _btnWisata.getId()) {
            Intent menuWisata = new Intent(this, MenuWisata.class);
            startActivity(menuWisata);
        } else if (view.getId() == _lihatSemuaInfo.getId()) {
            Intent comingSoon = new Intent(this, ComingSoon.class);
            startActivity(comingSoon);
        } else if (view.getId() == _btnLogout.getId()) {
            FirebaseAuth.getInstance().signOut();
            Intent logout = new Intent(this, MainActivity.class);
            startActivity(logout);
            finish();
        }
    }
}