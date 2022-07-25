package com.example.gymtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Patterns;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuthLogin;
    private EditText userEmailLogin;
    private EditText userPasswordLogin;
    private Button loginBtn;
    private Button signUpBtn;
    private Button languageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userEmailLogin = findViewById(R.id.editTextEmailLogin);
        userPasswordLogin = findViewById(R.id.editTextPasswordLogin);
        loginBtn = findViewById(R.id.buttonLogInLogin);
        signUpBtn=findViewById(R.id.buttonSignUpLogin);
        languageBtn=findViewById(R.id.buttonLanguageLogin);

        mAuthLogin = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(v -> {
            String email = userEmailLogin.getText().toString().trim();
            String password = userPasswordLogin.getText().toString().trim();
            if(email.isEmpty())
            {
                userEmailLogin.setError(getText(R.string.emailEmpty));
                userEmailLogin.requestFocus();
                return;
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                userEmailLogin.setError(getText(R.string.enterValidEmail));
                userEmailLogin.requestFocus();
                return;
            }
            if(password.isEmpty())
            {
                userPasswordLogin.setError(getText(R.string.passwordEmpty));
                userPasswordLogin.requestFocus();
                return;
            }
            if(password.length()<6)
            {
                userPasswordLogin.setError(getText(R.string.passwordLength));
                userPasswordLogin.requestFocus();
                return;
            }
            mAuthLogin.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful())
                {
                    Toast.makeText(LoginActivity.this, R.string.connected, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
                else
                {
                    Toast.makeText(LoginActivity.this, R.string.checkCredentials, Toast.LENGTH_SHORT).show();
                }

            });
        });
        signUpBtn.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this,SignUpActivity.class)));

        languageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Locale.getDefault().getLanguage() == "en"){
                    setLocal("iw");
                }
                else{
                    setLocal("en");
                }
                recreate();
            }
        });
    }

    private void setLocal(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }
}