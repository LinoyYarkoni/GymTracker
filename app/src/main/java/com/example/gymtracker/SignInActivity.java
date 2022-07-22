package com.example.gymtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignInActivity extends AppCompatActivity {
    private List<EditText> inputEditText = new ArrayList<EditText>();
    private Map<String,String> signInUserInfo = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initEditTextList();
    }

    private void initEditTextList() {
        inputEditText.clear();
        inputEditText.add(findViewById(R.id.editTextName));
        inputEditText.add(findViewById(R.id.editTextEmail));
        inputEditText.add(findViewById(R.id.editTextWeight));
        inputEditText.add(findViewById(R.id.editTextAge));
        initUserInputStringList();
    }

    private void initUserInputStringList() {
        signInUserInfo.clear();

        ArrayList<String> titles = new ArrayList<>(Arrays.asList("UserName", "Gender", "Weight", "Age"));
        int index=0;

        for (EditText editText: inputEditText){
            String userInput = editText.getText().toString();
            signInUserInfo.put(titles.get(index), userInput);
            index++;
        }
    }

    public void enableGetStartedButton(View view) {
        Switch switchView = findViewById(R.id.switchHealth);
        initEditTextList();
        boolean isUserData = checkUserData();
        boolean isPasswordsMatching = isPasswordMatch();

        if(isUserData && isPasswordsMatching && switchView.isChecked()){

        }
    }

    private boolean checkUserData() {
        boolean isDataValid = true;

        for (Map.Entry<String, String> input: signInUserInfo.entrySet()) {
            if(input.getValue().matches("")){
                isDataValid = false;
                Toast toast = Toast.makeText(this, "One Or More Fields Are Missing", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP,0,0);
                toast.show();
                break;
            }
        }

        return isDataValid;
    }

    private boolean isPasswordMatch() {
        EditText password = findViewById(R.id.editTextPassword);
        EditText passwordVerify = findViewById(R.id.editTextVerifyPassword);
        String passwordString = password.getText().toString();
        String passwordVerifyString = passwordVerify.getText().toString();
        boolean isPasswordMatch = passwordString.matches(passwordVerifyString);

        if(!isPasswordMatch) {
            Toast toast = Toast.makeText(this, "Password Not Matching", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
        }
        else{
            signInUserInfo.put("Password", passwordString);
        }

        return isPasswordMatch;
    }
}