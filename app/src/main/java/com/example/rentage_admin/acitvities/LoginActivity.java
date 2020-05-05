package com.example.rentage_admin.acitvities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rentage_admin.R;

public class LoginActivity extends AppCompatActivity {
    EditText email;
    EditText password;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {@Override
            public void onClick(View v) {
                String e = email.getText().toString();
                String p = password.getText().toString();

                if (e.equals("admin") && p.equals("123456")) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(LoginActivity.this, "Credential is invalid",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
