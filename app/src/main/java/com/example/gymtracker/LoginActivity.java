package com.example.gymtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText userEmail;
    private EditText userPassword;
    private MaterialButton loginBtn;
    private MaterialButton signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userEmail = findViewById(R.id.editTextEmailLogin);
        userPassword = findViewById(R.id.editTextPasswordLogin);
        loginBtn = findViewById(R.id.buttonLogIn);
        signUpBtn = findViewById(R.id.buttonSignUp);

        mAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(v -> {
            String email = userEmail.getText().toString().trim();
            String password = userPassword.getText().toString().trim();
            if(email.isEmpty())
            {
                userEmail.setError("Email is empty");
                userEmail.requestFocus();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                userEmail.setError("Enter the valid email");
                userEmail.requestFocus();
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