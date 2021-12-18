package com.example.atasiwirapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    EditText _txtLoginEmail, _txtLoginPassword;
    TextView _txtLoginRegister;
    Button _btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _txtLoginEmail = findViewById(R.id.txtLoginEmail);
        _txtLoginPassword = findViewById(R.id.txtLoginPassword);
        _txtLoginRegister = findViewById(R.id.txtLoginRegister);
        _btnLogin = findViewById(R.id.btnLogin);

        _btnLogin.setOnClickListener(this);
        _txtLoginRegister.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == _btnLogin.getId()) {
            mAuth.signInWithEmailAndPassword(_txtLoginEmail.getText().toString(), _txtLoginPassword.getText().toString())
                    .addOnCompleteListener(this,
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        if (user != null) {
                                            if (user.isEmailVerified()) {
                                                Intent home = new Intent(Login.this, MenuWisata.class);
                                                startActivity(home);
                                            } else {
                                                Toast.makeText(Login.this, "Not verified", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    } else {
                                        Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                    );
        } else if (view.getId() == _txtLoginRegister.getId()) {
            Intent register = new Intent(this, Register.class);
            startActivity(register);
        }
    }

}