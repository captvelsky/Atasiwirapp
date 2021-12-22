package com.example.atasiwirapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button _btnLandingLogin, _btnLandingRegister;
    FirebaseUser currentUser;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _btnLandingLogin = findViewById(R.id.btnLandingLogin);
        _btnLandingRegister = findViewById(R.id.btnLandingRegister);

        _btnLandingLogin.setOnClickListener(this);
        _btnLandingRegister.setOnClickListener(this);

        // If user already login, redirect user to home
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            if (currentUser.isEmailVerified()) {
                Intent home = new Intent(this, Home.class);
                startActivity(home);
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == _btnLandingLogin.getId()) {
            Intent login = new Intent(this, Home.class);
            startActivity(login);
        } else if (view.getId() == _btnLandingRegister.getId()) {
            Intent register = new Intent(this, Register.class);
            startActivity(register);
        }
    }
}
