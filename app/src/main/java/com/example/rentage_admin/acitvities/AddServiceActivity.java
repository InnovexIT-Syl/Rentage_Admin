package com.example.rentage_admin.acitvities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.rentage_admin.R;
import com.example.rentage_admin.models.CarServiceModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AddServiceActivity extends AppCompatActivity {
    private static final int STORAGE_REQUEST_CODE = 400;
    private static final int IMAGE_PICK_GALLERY_CODE = 1000;
    EditText titleService, descService, priceService;
    DatabaseReference databaseReference;
    String title, description, price;
    Button submitButton, chooseImage;
    String[] storagePermission;
    ImageView showImage;
    // storage
    private StorageReference storageReference;
    private Uri image_uri;

    private ProgressDialog progressDialog;

    private CarServiceModel serviceModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        titleService = findViewById(R.id.titleService);
        descService = findViewById(R.id.descService);
        priceService = findViewById(R.id.priceService);

        submitButton = findViewById(R.id.serviceBtn);
        chooseImage = findViewById(R.id.chooseImageButton);
        showImage = findViewById(R.id.showImage);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        storageReference = FirebaseStorage.getInstance().getReference(); // firebase storage reference

        progressDialog = new ProgressDialog(this);


        Intent intent = getIntent();
        serviceModel = (CarServiceModel) intent.getSerializableExtra("edit");

        if (serviceModel != null) {
            titleService.setText(serviceModel.getTitle());
            descService.setText(serviceModel.getDescription());
            priceService.setText(serviceModel.getPrice());

            Picasso.get().load(serviceModel.getImageUrl()).into(showImage);
        }

        //storage permission
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkPermissionGranted();
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DatabaseReference dbRef = serviceModel == null ? databaseReference.child(
                        "Services").push() :
                        databaseReference.child("Services").child(serviceModel.getId());

                final String key = serviceModel == null ? dbRef.getKey() : serviceModel.getId();
                title = titleService.getText().toString();
                description = descService.getText().toString();
                price = priceService.getText().toString();

                progressDialog.setMessage(serviceModel ==null? "Uploading data...":"Updating data" +
                        "..." );
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();



                if (image_uri != null && serviceModel == null) {
                    StorageReference reference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(image_uri));
                    reference.putFile(image_uri)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    // Get a URL to the uploaded content
                                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                                    while (!uriTask.isSuccessful()) ;
                                    Uri downloadUri = uriTask.getResult();

                                    //getting image url
                                    CarServiceModel carServiceModel = new CarServiceModel(key, description, title,
                                            downloadUri.toString(), price);

                                    dbRef.setValue(carServiceModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Intent intent = new Intent(getApplicationContext(),
                                                    ServicesActivity.class);
                                            intent.putExtra("service", "Manage Service");
                                            startActivity(intent);
                                            progressDialog.dismiss();
                                            Toast.makeText(AddServiceActivity.this, "Data uploaded successfully",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(AddServiceActivity.this, "Data upload failed!" + e.toString(),
                                                    Toast.LENGTH_SHORT).show();
                                            progressDialog.dismiss();
                                        }
                                    });


                                    Toast.makeText(AddServiceActivity.this, "Profile updated successfully..", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    // Handle unsuccessful uploads
                                    Toast.makeText(AddServiceActivity.this, "" + exception.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    CarServiceModel carServiceModel = new CarServiceModel(key, description, title,
                            serviceModel.getImageUrl(), price);

                    dbRef.setValue(carServiceModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Intent intent = new Intent(getApplicationContext(),
                                    ServicesActivity.class);
                            intent.putExtra("service", "Manage Service");
                            startActivity(intent);
                            progressDialog.dismiss();
                            Toast.makeText(AddServiceActivity.this, "Data updated successfully",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddServiceActivity.this,
                                    "Data update failed!" + e.toString(),
                                    Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
                }


            }
        });

    }

    private void checkPermissionGranted() {
        if (!checkStoragePermission()) {
            //Storage permission not allowed
            requestStoragePermission();
        } else {
            //permission allowed,take picture
            pickGallery();
        }
    }

    private void pickGallery() {
        //intent to pick image from gallery
        Intent intent = new Intent(Intent.ACTION_PICK);

        //set intent type to image
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_GALLERY_CODE);
    }

    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(this, storagePermission, STORAGE_REQUEST_CODE);
    }

    private boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    //Handle Permission result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case STORAGE_REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean writeStorageAccepted = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    if (writeStorageAccepted) {
                        pickGallery();
                    } else {
                        Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    //Handle Image result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            if (requestCode == IMAGE_PICK_GALLERY_CODE) {
                image_uri = data.getData();
                showImage.setImageURI(image_uri);
                showImage.setVisibility(View.VISIBLE);
                showImage.invalidate();
            }
        }
    }

    //get image extension
    public String getFileExtension(Uri imageUrl) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageUrl));
    }

}
