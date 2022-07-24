package com.example.gymtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static MainActivity sMainActivity;
    private Button buttonDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sMainActivity = this;
        buttonDone = findViewById(R.id.buttonDone);

        buttonDone.setOnClickListener(view -> {
            finish();
        });
    }

    public void startNewPractice(View view) {
        Intent exercisesIntent = new Intent(this, ExercisesActivity.class);
        startActivity(exercisesIntent);
    }
}