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

public class ChangePassword extends AppCompatActivity implements View.OnClickListener {

    EditText _txtOldPassword, _txtNewPassword, _txtPwEmail, _txtConfirmNewPassword;
    Button _btnChangePasswordSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        _txtPwEmail = findViewById(R.id.txtPwEmail);
        _txtOldPassword = findViewById(R.id.txtOldPassword);
        _txtNewPassword = findViewById(R.id.txtNewPassword);
        _txtConfirmNewPassword = findViewById(R.id.txtConfirmNewPassword);
        _txtConfirmNewPassword = findViewById(R.id.txtConfirmNewPassword);
        _btnChangePasswordSubmit = findViewById(R.id.btnChangePasswordSubmit);

        _btnChangePasswordSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == _btnChangePasswordSubmit.getId()) {
            String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            if (_txtNewPassword.getText().toString().equals("") && _txtOldPassword.getText().toString().equals("") && _txtConfirmNewPassword.getText().toString().equals("")) {
                Toast.makeText(ChangePassword.this, "Kata sandi belum diisi", Toast.LENGTH_LONG).show();
            } else if (_txtPwEmail.getText().toString().equals("")) {
                Toast.makeText(ChangePassword.this, "Email belum diisi", Toast.LENGTH_LONG).show();
            } else if (_txtOldPassword.getText().toString().equals("")) {
                Toast.makeText(ChangePassword.this, "Kata sandi lama belum diisi", Toast.LENGTH_LONG).show();
            } else if (_txtNewPassword.getText().toString().equals("")) {
                Toast.makeText(ChangePassword.this, "Kata sandi baru belum diisi", Toast.LENGTH_LONG).show();
            } else if (_txtConfirmNewPassword.getText().toString().equals("")) {
                Toast.makeText(ChangePassword.this, "Kata sandi konfirmasi belum diisi", Toast.LENGTH_LONG).show();
            } else if (!_txtPwEmail.getText().toString().equals(email)) {
                Toast.makeText(ChangePassword.this, "Email salah", Toast.LENGTH_LONG).show();
            } else if (!_txtNewPassword.getText().toString().equals(_txtConfirmNewPassword.getText().toString())) {
                Toast.makeText(ChangePassword.this, "Kata sandi tidak sama", Toast.LENGTH_SHORT).show();
            } else {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                // Get auth credentials from the user for re-authentication
                AuthCredential credential = EmailAuthProvider
                        .getCredential(_txtPwEmail.getText().toString(), _txtOldPassword.getText().toString()); // Current Login Credentials \\
                // Prompt the user to re-provide their sign-in credentials
                user.reauthenticate(credential)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                // Success re-authenticating user
                                // Change email address
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                user.updatePassword(_txtNewPassword.getText().toString())
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(ChangePassword.this, "Password berhasil diganti", Toast.LENGTH_LONG).show();
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