package com.example.atasiwirapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.atasiwirapp.rv_review.reviewAdapter;
import com.example.atasiwirapp.rv_review.reviewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Wisata extends AppCompatActivity implements View.OnClickListener {

    RecyclerView _rvReview;
    reviewAdapter reviewAdapter;
    Button _btnAddReview;
    ImageButton _btnReviewBack;
    DatabaseReference mDatabase;

    ArrayList<reviewModel> models = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wisata);

        _rvReview = findViewById(R.id.rvReview);
        _rvReview.setLayoutManager(new LinearLayoutManager(this));
        _rvReview.setAdapter(reviewAdapter);

        _btnAddReview = findViewById(R.id.btnAddReview);
        _btnAddReview.setOnClickListener(this);

        mDatabase = FirebaseDatabase.getInstance().getReference("review");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    reviewModel post = postSnapshot.getValue(reviewModel.class);
                    models.add(post);
                }
                reviewAdapter = new reviewAdapter(Wisata.this, models);
                _rvReview.setAdapter(reviewAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == _btnAddReview.getId()) {
            Intent addReview = new Intent(this, AddReview.class);
            startActivity(addReview);
        } else if (view.getId() == _btnReviewBack.getId()) {
            finish();
        }
    }
}