package com.example.gymtracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SignInActivity extends AppCompatActivity {
    private List<String> signInUserInfo;
    private List<String> userPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initList();
    }

    private void initList() {
        int numberOfFields = 4;

        for(int i=0; i<numberOfFields; i++){
            signInUserInfo.add("");
        }
    }


    public void enableGetStartedButton(View view) {
        Switch switchView = findViewById(R.id.switchHealth);
        Button getStartedButton = findViewById(R.id.buttonGetStarted);

        if(switchView.isChecked())
        {
            getStartedButton.setEnabled(true);
        }
        else {
            getStartedButton.setEnabled(false);
        }
    }

    public void checkUserData(View view) {
        boolean isDataValid = true;

        for (String input: signInUserInfo) {
            if(input.matches("")){
                isDataValid = false;
                break;
            }
        }

        if(!isDataValid){
            Toast.makeText(this, "One Or More Fields Are Missing", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isPasswordMatch()
    {
        return true;
    }
}