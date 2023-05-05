package com.example.thebinarybrain_quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registerPage extends AppCompatActivity {

    EditText editTextEmail,editTextPassword,editTextConfirmPassword;
    Button signUp;

    TextView logIn;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        editTextEmail= findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        editTextConfirmPassword = findViewById(R.id.confirmPassword);
        signUp = findViewById(R.id.btnSignUp);
        logIn = findViewById(R.id.btnLogIn);

        //when the user will click on Login it will take the user to Signup activity
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registerPage.this,Login.class);
                startActivity(intent);
                finish();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email,password,confirmPassword;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());
                confirmPassword = String.valueOf(editTextConfirmPassword.getText());
                
                if (email.isEmpty()||password.isEmpty()||confirmPassword.isEmpty()) {
                    Toast.makeText(registerPage.this, "Email or Password can not be blank", Toast.LENGTH_SHORT).show();
                    return;
                } else if (password.length()<6) {
                    Toast.makeText(registerPage.this, "Password should be atleast 6 character", Toast.LENGTH_SHORT).show();
                    return;
                }  else if (!password.equals(confirmPassword)) {
                    Toast.makeText(registerPage.this, "Password did not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                firebaseAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(registerPage.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(registerPage.this,Login.class);
                                    startActivity(intent);
                                    finish();

                                }
                                else {
                                    Toast.makeText(registerPage.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


    }
}