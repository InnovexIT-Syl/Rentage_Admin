package com.example.rentage_admin.acitvities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentage_admin.R;
import com.example.rentage_admin.models.CategoryModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddCategory extends AppCompatActivity {
    EditText titleInput, descInput;
    DatabaseReference databaseReference;
    String title, description;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        titleInput = findViewById(R.id.titleInput);
        descInput = findViewById(R.id.descInput);

        submitButton = findViewById(R.id.submitBtn);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = titleInput.getText().toString();
                description = descInput.getText().toString();

                DatabaseReference dbRef = databaseReference.child("Categories").push();
                String key = dbRef.getKey();

                CategoryModel categoryModel = new CategoryModel(key, description, title,
                        "categories");


                dbRef.setValue(categoryModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Intent intent = new Intent(getApplicationContext(),
                                CategoriesActivity.class);
                        intent.putExtra("category","Manage Category");
                        startActivity(intent);
                        Toast.makeText(AddCategory.this, "Data uploaded successfully",
                                Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddCategory.this, "Data upload failed!" + e.toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });



    }
}
