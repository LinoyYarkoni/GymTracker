package com.example.gymtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;

public class SignUpActivity extends AppCompatActivity {
    private EditText userName;
    private EditText emailEditText;
    private EditText weight;
    private EditText age;
    private EditText userPassword;
    private FirebaseAuth mAuth;
    private AuthResult authResult;
    private MaterialButton signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userName = findViewById(R.id.editTextName);
        emailEditText = findViewById(R.id.editTextEmail);
        weight = findViewById(R.id.editTextWeight);
        age = findViewById(R.id.editTextAge);
        userPassword = findViewById(R.id.editTextPassword);
        signUp = findViewById(R.id.signUp);

        mAuth = FirebaseAuth.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = userPassword.getText().toString().trim();
                String name = userName.getText().toString().trim();

                if (email.isEmpty()) {
                    emailEditText.setError("Email is empty");
                    emailEditText.requestFocus();
                    return;
                }
                if (name.isEmpty()) {
                    userName.setError("Name is empty");
                    userName.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailEditText.setError("Enter the valid email address");
                    emailEditText.requestFocus();
                    return;
                }
                if (password.isEmpty()) {
                    userPassword.setError("Enter the password");
                    userPassword.requestFocus();
                    return;
                }
                if (password.length() < 6) {
                    userPassword.setError("Length of the password should be more than 6");
                    userPassword.requestFocus();
                    return;
                }
                // add weight and age if() and switch
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "You are successfully Registered", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                        } else {
                            Toast.makeText(SignUpActivity.this, "You are not Registered! Try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}