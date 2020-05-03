package com.example.rentage_admin.acitvities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.rentage_admin.R;

public class ManageUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_user);

        Intent intent = getIntent();
        String name = intent.getStringExtra("user");

        getSupportActionBar().setTitle(name);
    }
}
