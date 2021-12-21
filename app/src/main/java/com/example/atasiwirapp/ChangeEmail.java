package com.example.atasiwirapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangeEmail extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    EditText _txtOldEmail, _txtNewEmail, _txtEmPassword;
    Button _btnChangeEmailSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);

        _txtOldEmail = findViewById(R.id.txtOldEmail);
        _txtNewEmail = findViewById(R.id.txtNewEmail);
        _txtEmPassword = findViewById(R.id.txtEmPassword);
        _btnChangeEmailSubmit = findViewById(R.id.btnChangeEmailSubmit);

        _btnChangeEmailSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == _btnChangeEmailSubmit.getId()) {
            String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            if (_txtEmPassword.getText().toString().equals("") && _txtNewEmail.getText().toString().equals("") && _txtOldEmail.getText().toString().equals("")) {
                Toast.makeText(ChangeEmail.this, "Email belum diisi", Toast.LENGTH_LONG).show();
            } else if (_txtOldEmail.getText().toString().equals("")) {
                Toast.makeText(ChangeEmail.this, "Email lama belum diisi", Toast.LENGTH_LONG).show();
            } else if (_txtEmPassword.getText().toString().equals("")) {
                Toast.makeText(ChangeEmail.this, "Kata sandi belum diisi", Toast.LENGTH_LONG).show();
            } else if (!_txtOldEmail.getText().toString().equals(email)) {
                Toast.makeText(ChangeEmail.this, "Email salah", Toast.LENGTH_LONG).show();
            } else {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                // Get auth credentials from the user for re-authentication
                AuthCredential credential = EmailAuthProvider
                        .getCredential(_txtOldEmail.getText().toString(), _txtEmPassword.getText().toString()); // Current Login Credentials \\
                // Prompt the user to re-provide their sign-in credentials
                user.reauthenticate(credential)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                // Success re-authenticating user
                                // Change email address
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                user.updateEmail(_txtNewEmail.getText().toString())
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    FirebaseAuth auth = FirebaseAuth.getInstance();
                                                    FirebaseUser user = auth.getCurrentUser();
                                                    user.sendEmailVerification()
                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                    if (task.isSuccessful()) {
                                                                        Toast.makeText(ChangeEmail.this, "Email verifikasi dikirim", Toast.LENGTH_LONG).show();
                                                                        finish();
                                                                    } else {
                                                                        Toast.makeText(ChangeEmail.this, "Gagal mengirim email verifikasi", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                }
                                                            });
                                                    Toast.makeText(ChangeEmail.this, "Cek email anda untuk verifikasi", Toast.LENGTH_LONG).show();
                                                    finish();
                                                }
                                            }
                                        });
                            }
                        });
            }
        }
    }
}