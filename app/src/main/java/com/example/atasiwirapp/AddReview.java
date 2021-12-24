package com.example.atasiwirapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.atasiwirapp.rv_review.reviewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddReview extends AppCompatActivity implements View.OnClickListener {

    Button _btnSubmitReview;
    EditText _txtReviewDesc, _txtReviewName;
    TextView _tvAddReviewWisataName;
    RatingBar _addRatingBar;
    String title;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        _btnSubmitReview = findViewById(R.id.btnSubmitReview);
        _txtReviewDesc = findViewById(R.id.txtAddReviewDesc);
        _txtReviewName = findViewById(R.id.txtAddReviewName);
        _addRatingBar = findViewById(R.id.rbAddReviewRating);
        _tvAddReviewWisataName = findViewById(R.id.tvAddReviewWisataName);

        _btnSubmitReview.setOnClickListener(this);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            title = extras.getString("title");
            _tvAddReviewWisataName.setText(title);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == _btnSubmitReview.getId()) {
            if (_txtReviewDesc.getText().toString().isEmpty()) {
                Toast.makeText(AddReview.this, "Anda belum mengisi ulasan", Toast.LENGTH_LONG).show();
            } else if (_txtReviewName.getText().toString().isEmpty()) {
                Toast.makeText(AddReview.this, "Anda belum mengisi nama", Toast.LENGTH_LONG).show();
            } else {
                reviewModel baru = new reviewModel(_txtReviewName.getText().toString(), _txtReviewDesc.getText().toString(), String.valueOf(_addRatingBar.getRating()));
                mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("review " + _tvAddReviewWisataName.getText().toString()).push().setValue(baru).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(AddReview.this, "Terima kasih telah memberi ulasan!", Toast.LENGTH_LONG).show();
                            Intent menuWisata = new Intent(AddReview.this, MenuWisata.class);
                            startActivity(menuWisata);
                            finish();
                        } else {
                            Toast.makeText(AddReview.this, "Ulasan gagal diberikan", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }
    }
}