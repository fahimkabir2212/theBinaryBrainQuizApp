package com.example.thebinarybrain_quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class registerPage extends AppCompatActivity {

    EditText editTextEmail,editTextPassword,EditTextConfirmPassword;
    Button signUp;

    TextView logIn;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        logIn = findViewById(R.id.btnLogIn);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registerPage.this,Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}