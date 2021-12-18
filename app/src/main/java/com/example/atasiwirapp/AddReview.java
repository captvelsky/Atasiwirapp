package com.example.atasiwirapp;

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
        _txtReviewName.setMovementMethod(null);

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
                Toast.makeText(AddReview.this, "Please fill in feedback text box", Toast.LENGTH_LONG).show();
            } else if (_txtReviewName.getText().toString().isEmpty()) {
                Toast.makeText(AddReview.this, "Please fill in your name", Toast.LENGTH_LONG).show();
            } else {
                reviewModel baru = new reviewModel(_txtReviewName.getText().toString(), _txtReviewDesc.getText().toString(), String.valueOf(_addRatingBar.getRating()));
                mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("review " + _tvAddReviewWisataName.getText().toString()).push().setValue(baru);

                Toast.makeText(AddReview.this, "Thank you for sharing your feedback", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
}