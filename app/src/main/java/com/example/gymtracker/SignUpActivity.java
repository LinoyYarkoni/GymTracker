package com.example.gymtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    private EditText userName;
    private EditText userEmail;
    private EditText userWeight;
    private EditText userAge;
    private EditText userPassword;
    private Button signUp;
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
        mAuthSignUp = FirebaseAuth.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    userEmail.setError("Enter the valid email address");
                    userEmail.requestFocus();
                    return;
                }
                if(password.isEmpty())
                {
                    userPassword.setError("Enter the password");
                    userPassword.requestFocus();
                    return;
                }
                if(password.length()<6)
                {
                    userPassword.setError("Length of the password should be more than 6");
                    userPassword.requestFocus();
                    return;
                }
                mAuthSignUp.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(SignUpActivity.this,"You are successfully Registered", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                        }
                        else
                        {
                            Toast.makeText(SignUpActivity.this,"You are not Registered! Try again",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });
//        back_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i  = new Intent(Activity_SignUp.this,Activity_Login.class);
//                startActivity(i);
//            }
//        });
    }
}
