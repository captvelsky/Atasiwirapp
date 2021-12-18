package com.example.atasiwirapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button _btnLandingLogin, _btnLandingRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _btnLandingLogin = findViewById(R.id.btnLandingLogin);
        _btnLandingRegister = findViewById(R.id.btnLandingRegister);

        _btnLandingLogin.setOnClickListener(this);
        _btnLandingRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == _btnLandingLogin.getId()) {
            Intent login = new Intent(this, Login.class);
            startActivity(login);
        } else if (view.getId() == _btnLandingRegister.getId()) {
            Intent register = new Intent(this, Register.class);
            startActivity(register);
        }
    }
}
