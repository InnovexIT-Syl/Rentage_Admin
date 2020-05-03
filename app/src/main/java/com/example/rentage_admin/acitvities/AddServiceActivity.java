package com.example.rentage_admin.acitvities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rentage_admin.R;
import com.example.rentage_admin.models.CategoryModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddServiceActivity extends AppCompatActivity {

    EditText titleService, descService, priceService;
    DatabaseReference databaseReference;
    String title, description, price;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        titleService = findViewById(R.id.titleService);
        descService = findViewById(R.id.descService);
        priceService = findViewById(R.id.priceService);

        submitButton = findViewById(R.id.serviceBtn);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = titleService.getText().toString();
                description = descService.getText().toString();
                price = priceService.getText().toString();

                DatabaseReference dbRef = databaseReference.child("Services").push();
                String key = dbRef.getKey();

                CategoryModel categoryModel = new CategoryModel(key, description, title,
                        "categories");


                dbRef.setValue(categoryModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Intent intent = new Intent(getApplicationContext(),
                                ServicesActivity.class);
                        intent.putExtra("category","Manage Category");
                        startActivity(intent);
                        Toast.makeText(AddServiceActivity.this, "Data uploaded successfully",
                                Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddServiceActivity.this, "Data upload failed!" + e.toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });



    }
}
