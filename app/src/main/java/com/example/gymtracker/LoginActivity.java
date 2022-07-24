package com.example.gymtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuthLogin;
    private EditText userEmailLogin;
    private EditText userPasswordLogin;
    private Button loginBtn;
    private Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userEmailLogin = findViewById(R.id.editTextEmailLogin);
        userPasswordLogin = findViewById(R.id.editTextPasswordLogin);
        loginBtn = findViewById(R.id.buttonLogInLogin);
        signUpBtn=findViewById(R.id.buttonSignUpLogin);

        mAuthLogin = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(v -> {
            String email = userEmailLogin.getText().toString().trim();
            String password = userPasswordLogin.getText().toString().trim();
            if(email.isEmpty())
            {
                userEmailLogin.setError("Email is empty");
                userEmailLogin.requestFocus();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                userEmailLogin.setError("Enter the valid email");
                userEmailLogin.requestFocus();
                return;
            }
            if(password.isEmpty())
            {
                userPasswordLogin.setError("Password is empty");
                userPasswordLogin.requestFocus();
                return;
            }
            if(password.length()<6)
            {
                userPasswordLogin.setError("Length of password is more than 6");
                userPasswordLogin.requestFocus();
                return;
            }
            mAuthLogin.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
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