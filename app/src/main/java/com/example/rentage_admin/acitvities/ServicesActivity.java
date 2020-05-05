package com.example.rentage_admin.acitvities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.rentage_admin.R;
import com.example.rentage_admin.adapters.CarServiceAdapter;
import com.example.rentage_admin.models.CarServiceModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ServicesActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;

    RecyclerView serviceRecycle;
    List<CarServiceModel> carServiceModels;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        getSupportActionBar().setTitle("Services");


        floatingActionButton = findViewById(R.id.addServiceBtn);

        serviceRecycle = findViewById(R.id.serviceRecycle);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        carServiceModels = new ArrayList<>();


        final CarServiceAdapter adapter = new CarServiceAdapter(this, carServiceModels);
        databaseReference.child("Services").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data_snapshot : dataSnapshot.getChildren()){
                    CarServiceModel carServiceModel = data_snapshot.getValue(CarServiceModel.class);

                    carServiceModels.add(carServiceModel);
                }
                LinearLayoutManager layoutManager =
                        new LinearLayoutManager(getApplicationContext());
                serviceRecycle.setLayoutManager(layoutManager);
                serviceRecycle.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ServicesActivity.this, "Something is wrong" + databaseError.toString(),
                        Toast.LENGTH_SHORT).show();
            }

        });





        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddServiceActivity.class));
            }
        });
    }
}
