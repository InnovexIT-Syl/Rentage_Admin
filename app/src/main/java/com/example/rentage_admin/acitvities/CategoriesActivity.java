package com.example.rentage_admin.acitvities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.rentage_admin.R;
import com.example.rentage_admin.adapters.CategoryAdapter;
import com.example.rentage_admin.models.CategoryModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoriesActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;

    RecyclerView categoryRecycler;
    List<CategoryModel> categoryModelList;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        Intent intent = getIntent();
        String name = intent.getStringExtra("category");
        getSupportActionBar().setTitle(name);

        floatingActionButton = findViewById(R.id.addBtn);
        categoryRecycler = findViewById(R.id.categoryRecycler);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        categoryModelList = new ArrayList<>();


        databaseReference.child("Categories").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data_snapshot : dataSnapshot.getChildren()){
                    CategoryModel categoryModel = data_snapshot.getValue(CategoryModel.class);

                    categoryModelList.add(categoryModel);
                }
                LinearLayoutManager layoutManager =
                        new LinearLayoutManager(getApplicationContext());
                categoryRecycler.setLayoutManager(layoutManager);
                CategoryAdapter adapter = new CategoryAdapter(getApplicationContext(), categoryModelList);
                categoryRecycler.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(CategoriesActivity.this, "Something is wrong" + databaseError.toString(),
                        Toast.LENGTH_SHORT).show();
            }

        });




        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddCategory.class));
            }
        });
    }
}
