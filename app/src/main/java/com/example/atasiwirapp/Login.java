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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    EditText _txtLoginEmail, _txtLoginPassword;
    TextView _txtLoginRegister, _txtLoginForgotPassword;
    Button _btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _txtLoginEmail = findViewById(R.id.txtLoginEmail);
        _txtLoginPassword = findViewById(R.id.txtLoginPassword);
        _txtLoginRegister = findViewById(R.id.txtLoginRegister);
        _txtLoginForgotPassword = findViewById(R.id.txtLoginForgotPassword);
        _btnLogin = findViewById(R.id.btnLogin);

        _btnLogin.setOnClickListener(this);
        _txtLoginRegister.setOnClickListener(this);
        _txtLoginForgotPassword.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == _btnLogin.getId()) {
            if (_txtLoginEmail.getText().toString().equals("") && _txtLoginPassword.getText().toString().equals("")) {
                Toast.makeText(Login.this, "Email dan password belum diisi", Toast.LENGTH_LONG).show();
            } else if (_txtLoginEmail.getText().toString().equals("")) {
                Toast.makeText(Login.this, "Email belum diisi", Toast.LENGTH_LONG).show();
            } else if (_txtLoginPassword.getText().toString().equals("")) {
                Toast.makeText(Login.this, "Password belum diisi", Toast.LENGTH_LONG).show();
            } else {
                mAuth.signInWithEmailAndPassword(_txtLoginEmail.getText().toString(), _txtLoginPassword.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            if (user != null) {
                                                if (user.isEmailVerified()) {
                                                    Intent home = new Intent(Login.this, Home.class);
                                                    startActivity(home);
                                                } else {
                                                    Toast.makeText(Login.this, "Akun belum diverifikasi", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        } else {
                                            Toast.makeText(Login.this, "Autentikasi gagal", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                        );
            }
        } else if (view.getId() == _txtLoginRegister.getId()) {
            Intent register = new Intent(this, Register.class);
            startActivity(register);
        } else if (view.getId() == _txtLoginForgotPassword.getId()) {
            FirebaseAuth auth = FirebaseAuth.getInstance();
            String email = _txtLoginEmail.getText().toString();
            if (email.equals("")) {
                Toast.makeText(Login.this, "Isi email terlebih dahulu untuk mengganti password", Toast.LENGTH_SHORT).show();
            } else if (!isEmailValid(email)) {
                Toast.makeText(Login.this, "Email salah", Toast.LENGTH_SHORT).show();
            } else {
                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Login.this, "Email untuk mengganti password berhasil dikirim", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }
    }

}