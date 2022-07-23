package com.example.gymtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView welcomeTextView = findViewById(R.id.textViewWelcome);
        welcomeTextView.setText("Welcome ***'s Practice"); // get username from DB
    }

    public void startNewPractice(View view) {
        Intent exercisesIntent = new Intent(this, ExercisesActivity.class);
        startActivity(exercisesIntent);
    }
}