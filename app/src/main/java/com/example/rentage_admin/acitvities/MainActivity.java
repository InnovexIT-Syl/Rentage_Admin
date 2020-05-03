package com.example.rentage_admin.acitvities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.rentage_admin.R;

public class MainActivity extends AppCompatActivity {

    CardView userCard, serviceCard, orderCard, categoryCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();

        clickListeners();
    }

    private void clickListeners() {
        userCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ManageUserActivity.class);
                intent.putExtra("user",getString(R.string.manage_users));
                startActivity(intent);
            }
        });
        serviceCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ServicesActivity.class);
                intent.putExtra("service",getString(R.string.manage_service));
                startActivity(intent);
            }
        });
        orderCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OrdersActivity.class);
                intent.putExtra("order",getString(R.string.manage_orders));
                startActivity(intent);
            }
        });
        categoryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CategoriesActivity.class);
                intent.putExtra("category",getString(R.string.manage_categories));
                startActivity(intent);
            }
        });
    }

    private void initialize() {
        userCard = findViewById(R.id.userCard);
        serviceCard = findViewById(R.id.serviceCard);
        orderCard = findViewById(R.id.ordercard);
        categoryCard = findViewById(R.id.categorycard);
    }


}
