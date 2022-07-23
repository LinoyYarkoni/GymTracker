package com.example.gymtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText userName;
    private EditText userPassword;
    private Button loginBtn;
    private Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.editTextTextPersonName);
        userPassword = findViewById(R.id.editTextPassword);
        loginBtn = findViewById(R.id.buttonLogIn);
        signUpBtn=findViewById(R.id.buttonSignIn);

        mAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(v -> {
            String email = userName.getText().toString().trim();
            String password = userPassword.getText().toString().trim();
            if(email.isEmpty())
            {
                userName.setError("Email is empty");
                userName.requestFocus();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                userName.setError("Enter the valid email");
                userName.requestFocus();
                return;
            }
            if(password.isEmpty())
            {
                userPassword.setError("Password is empty");
                userPassword.requestFocus();
                return;
            }
            if(password.length()<6)
            {
                userPassword.setError("Length of password is more than 6");
                userPassword.requestFocus();
                return;
            }
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful())
                {
                    Toast.makeText(LoginActivity.this, "Connected!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Please Check Your login Credentials", Toast.LENGTH_SHORT).show();
                }

            });
        });
        signUpBtn.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this,SignUpActivity.class)));
    }

}