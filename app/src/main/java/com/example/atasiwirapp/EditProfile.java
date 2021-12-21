package com.example.atasiwirapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EditProfile extends AppCompatActivity implements View.OnClickListener {

    private static final int RC_Take_Photo = 0;
    private static final int RC_Take_From_Gallery = 1;

    String currentPhotoPath;
    ImageButton _btnEditProfilePic;
    Button _btnEditProfileSubmit, _btnChangeEmail, _btnChangePassword;
    EditText _txtEditProfileName;
    private ImageView _imgEditProfilePic;
    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        _imgEditProfilePic = findViewById(R.id.imgEditProfilePic);
        _txtEditProfileName = findViewById(R.id.txtEditProfileName);
        _btnEditProfilePic = findViewById(R.id.btnEditProfilePic);
        _btnChangeEmail = findViewById(R.id.btnChangeEmail);
        _btnChangePassword = findViewById(R.id.btnChangePassword);
        _btnEditProfileSubmit = findViewById(R.id.btnEditProfileSubmit);

        _btnEditProfilePic.setOnClickListener(this);
        _btnChangeEmail.setOnClickListener(this);
        _btnChangePassword.setOnClickListener(this);
        _btnEditProfileSubmit.setOnClickListener(this);

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            _txtEditProfileName.setText(user.getDisplayName());

            mStorageRef = FirebaseStorage.getInstance().getReference();
            StorageReference fotoRef = mStorageRef.child(user.getUid() + "/profilepic");

            Task<ListResult> listPageTask = fotoRef.list(1);
            listPageTask.addOnSuccessListener(new OnSuccessListener<ListResult>() {
                @Override
                public void onSuccess(ListResult listResult) {
                    List<StorageReference> items = listResult.getItems();
                    if (!items.isEmpty()) { // Glide
                        items.get(0).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Glide.with(getApplicationContext()).load(uri).centerCrop().into(_imgEditProfilePic);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                            }
                        });
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(EditProfile.this, "Error, " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void selectImage(final Context context) {
        final CharSequence[] options = {"Ambil Foto", "Pilih dari Galeri", "Batal"};

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Pilih Foto Profil");

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Ambil Foto")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePicture.resolveActivity(getPackageManager()) != null) {
                        File photoFile = null;
                        try {
                            photoFile = createImageFile();
                        } catch (IOException ex) {
                            // Error occurred while creating the File
                        }
                        // Continue only if the file was successfully created
                        if (photoFile != null) {
                            Uri photoURI = FileProvider.getUriForFile(EditProfile.this, "com.example.atasiwirapp", photoFile);
                            takePicture.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                            startActivityForResult(takePicture, RC_Take_Photo);
                        }
                    }
                } else if (options[item].equals("Pilih dari Galeri")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, RC_Take_From_Gallery);
                } else if (options[item].equals("Batal")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case RC_Take_Photo:
                    if (resultCode == RESULT_OK && currentPhotoPath != null) {
                        Glide.with(this).load(new File(currentPhotoPath)).centerCrop().into(_imgEditProfilePic);

                        // Scanning to gallery
                        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                        File f = new File(currentPhotoPath);
                        Uri contentUri = Uri.fromFile(f);
                        mediaScanIntent.setData(contentUri);
                        this.sendBroadcast(mediaScanIntent);

                        uploadToStorage(contentUri);
                    }
                    break;

                case RC_Take_From_Gallery:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();
                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                Glide.with(this).load(new File(picturePath)).centerCrop().into(_imgEditProfilePic);
                                cursor.close();
                            }
                            uploadToStorage(selectedImage);
                        }
                    }
                    break;
            }
        }
    }

    public void uploadToStorage(Uri file) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UploadTask uploadTask;
        mStorageRef = FirebaseStorage.getInstance().getReference();
        StorageReference fotoRef = mStorageRef.child(user.getUid() + "/profilepic/" + user.getUid() + ".jpg");
        uploadTask = fotoRef.putFile(file);
        Toast.makeText(EditProfile.this, "Mengunggah foto", Toast.LENGTH_SHORT).show();

        // Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(EditProfile.this, "Error, " + exception.getMessage(), Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(EditProfile.this, "Foto berhasil diunggah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            selectImage(EditProfile.this);
        } else {
            Toast.makeText(this, "Akses ditolak", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == _btnEditProfilePic.getId()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    String[] Permisions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    requestPermissions(Permisions, 100);
                } else {
                    selectImage(EditProfile.this);
                }
            } else {
                selectImage(EditProfile.this);
            }
        } else if (view.getId() == _btnChangeEmail.getId()) {
            Intent changeEmail = new Intent(this, ChangeEmail.class);
            startActivity(changeEmail);
        } else if (view.getId() == _btnChangePassword.getId()) {
            Intent changePassword = new Intent(this, ChangePassword.class);
            startActivity(changePassword);
        } else if (view.getId() == _btnEditProfileSubmit.getId()) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(_txtEditProfileName.getText().toString()).build();
            user.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(EditProfile.this, "Perubahan berhasil disimpan", Toast.LENGTH_LONG).show();
                        Intent home = new Intent(EditProfile.this, Home.class);
                        startActivity(home);
                        finish();
                    } else {
                        Toast.makeText(EditProfile.this, "Perubahan gagal disimpan", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

}