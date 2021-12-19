package com.example.atasiwirapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChangeEmail extends AppCompatActivity implements View.OnClickListener {

    EditText _txtOldEmail, _txtNewEmail;
    Button _btnChangeEmailSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);

        _txtOldEmail = findViewById(R.id.txtOldEmail);
        _txtNewEmail = findViewById(R.id.txtNewEmail);
        _btnChangeEmailSubmit = findViewById(R.id.btnChangeEmailSubmit);

        _btnChangeEmailSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}