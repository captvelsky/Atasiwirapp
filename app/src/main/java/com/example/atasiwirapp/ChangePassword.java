package com.example.atasiwirapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChangePassword extends AppCompatActivity implements View.OnClickListener {

    EditText _txtOldPassword, _txtNewPassword, _txtConfirmNewPassword;
    Button _btnChangePasswordSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        _txtOldPassword = findViewById(R.id.txtOldPassword);
        _txtNewPassword = findViewById(R.id.txtNewPassword);
        _txtConfirmNewPassword = findViewById(R.id.txtConfirmNewPassword);
        _btnChangePasswordSubmit = findViewById(R.id.btnChangePasswordSubmit);

        _btnChangePasswordSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}