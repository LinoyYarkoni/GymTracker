package com.example.gymtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    private EditText userName;
    private EditText userEmail;
    private EditText userWeight;
    private EditText userAge;
    private EditText userPassword;
    private Button signUp;
    private ImageButton backBtn;
    private FirebaseAuth mAuthSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userName = findViewById(R.id.editTextName);
        userEmail = findViewById(R.id.editTextEmail);
        userWeight = findViewById(R.id.editTextWeight);
        userAge = findViewById(R.id.editTextAge);
        userPassword = findViewById(R.id.editTextPassword);
        signUp = findViewById(R.id.buttonSignUp);
        backBtn = findViewById(R.id.buttonBack);

        mAuthSignUp = FirebaseAuth.getInstance();

        signUp.setOnClickListener(v -> {
            String email = userEmail.getText().toString().trim();
            String password = userPassword.getText().toString().trim();
            if(email.isEmpty())
            {
                userEmail.setError(getText(R.string.emailEmpty));
                userEmail.requestFocus();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                userEmail.setError(getText(R.string.enterValidEmail));
                userEmail.requestFocus();
                return;
            }
            if(password.isEmpty())
            {
                userPassword.setError(getText(R.string.passwordEmpty));
                userPassword.requestFocus();
                return;
            }
            if(password.length()<6)
            {
                userPassword.setError(getText(R.string.passwordLength));
                userPassword.requestFocus();
                return;
            }
            mAuthSignUp.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful())
                {
                    Toast.makeText(SignUpActivity.this,R.string.registered, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                }
                else
                {
                    Toast.makeText(SignUpActivity.this,R.string.notRegister,Toast.LENGTH_SHORT).show();
                }
            });
        });

        backBtn.setOnClickListener(view -> onBackPressed());
    }
}
