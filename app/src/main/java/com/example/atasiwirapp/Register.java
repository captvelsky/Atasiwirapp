package com.example.atasiwirapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    EditText _txtRegisterName, _txtRegisterEmail, _txtRegisterPassword, _txtRegisterConfirmPassword;
    Button _btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        _txtRegisterName = findViewById(R.id.txtRegisterName);
        _txtRegisterEmail = findViewById(R.id.txtRegisterEmail);
        _txtRegisterPassword = findViewById(R.id.txtRegisterPassword);
        _txtRegisterConfirmPassword = findViewById(R.id.txtRegisterConfirmPassword);

        _btnRegister = findViewById(R.id.btnRegister);
        _btnRegister.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == _btnRegister.getId()) {
            // Cek if password == confirm password
            if (_txtRegisterEmail.getText().toString().equals("") && _txtRegisterPassword.getText().toString().equals("")) {
                Toast.makeText(Register.this, "Email dan password belum diisi", Toast.LENGTH_LONG).show();
            } else if (_txtRegisterEmail.getText().toString().equals("")) {
                Toast.makeText(Register.this, "Email belum diisi", Toast.LENGTH_LONG).show();
            } else if (_txtRegisterPassword.getText().toString().equals("")) {
                Toast.makeText(Register.this, "Password belum diisi", Toast.LENGTH_LONG).show();
            } else if (!_txtRegisterPassword.getText().toString().equals(_txtRegisterConfirmPassword.getText().toString())) {
                Toast.makeText(Register.this, "Kata sandi tidak sama", Toast.LENGTH_SHORT).show();
            } else {
                mAuth.createUserWithEmailAndPassword(_txtRegisterEmail.getText().toString(), _txtRegisterPassword.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's information
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            if (user != null) {
                                                if (!user.isEmailVerified()) {
                                                    final String email = user.getEmail();
                                                    user.sendEmailVerification().addOnCompleteListener(Register.this, new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                                                        .setDisplayName(_txtRegisterName.getText().toString()).build();
                                                                user.updateProfile(profileUpdates);
                                                                Intent login = new Intent(Register.this, Login.class);
                                                                startActivity(login);
                                                                finish();
                                                                Toast.makeText(Register.this, "Email verifikasi dikirim ke " + email, Toast.LENGTH_SHORT).show();
                                                            } else {
                                                                Log.e("Verification error.", "sendEmailVerification", task.getException());
                                                                Toast.makeText(Register.this, "Gagal mengirim email verifikasi", Toast.LENGTH_SHORT).show();
                                                            }
                                                            // [END_EXCLUDE]
                                                        }
                                                    });
                                                }
                                            }
                                        } else {
                                            Toast.makeText(Register.this, "Autentikasi gagal", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                        );
            }
        }
    }

}