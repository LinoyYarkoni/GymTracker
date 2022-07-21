package com.example.gymtracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExercisesActivity extends AppCompatActivity {
    private ListView listView;
    private List<String> names = new ArrayList<>();
    private List<Integer> pictures = new ArrayList<>();
    private Map<String,Integer> chosenExercises = new HashMap<>();
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        initNamesList();
        initPicturesList();

        listView = findViewById(R.id.listView);
        ListViewAdapter listViewAdapter = new ListViewAdapter(ExercisesActivity.this, names, pictures);
        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Integer chosenPicture = pictures.get(i);
                String chosenName = names.get(i);
                createExercisesPopup(chosenName, chosenPicture);
            }
        });
    }

    private void createExercisesPopup(String name, Integer picture){
        View popup = getLayoutInflater().inflate(R.layout.popup, null);
        TextView machineName = popup.findViewById(R.id.textViewMachineName);
        ImageView machinePicture = popup.findViewById(R.id.imageViewMachinePicture);

        machineName.setText(name);
        machinePicture.setImageResource(picture);

        dialogBuilder = new AlertDialog.Builder(this);

        dialogBuilder.setView(popup);
        dialog = dialogBuilder.create();
        dialog.show();

        SeekBar weightSeekBar = popup.findViewById(R.id.seekBarWeight);
        TextView weightTextView = popup.findViewById(R.id.textViewCountWeight);

        weightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int val = (progress * (seekBar.getWidth() - 2 * seekBar.getThumbOffset())) / seekBar.getMax();
                weightTextView.setText("" + progress*10);
                weightTextView.setX(seekBar.getX() + val + seekBar.getThumbOffset() / 2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        SeekBar repetitionsSeekBar = popup.findViewById(R.id.seekBarRepetitions);
        TextView repetitionsTextView = popup.findViewById(R.id.textViewCountRepetitions);

        repetitionsSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int val = (progress * (seekBar.getWidth() - 2 * seekBar.getThumbOffset())) / seekBar.getMax();
                repetitionsTextView.setText("" + progress);
                repetitionsTextView.setX(seekBar.getX() + val + seekBar.getThumbOffset() / 2);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

    }

    private void initNamesList(){
        names.add("Treadmill");
        names.add("Leg Press");
        names.add("Leg Curl");
        names.add("Leg Extension");
        names.add("Hip Abduction");
        names.add("Crunch");
        names.add("Pulldown");
        names.add("Arm Adduction");
        names.add("Arm Curl");
        names.add("Arm Extension");
    }

    private void initPicturesList(){
        pictures.add(R.drawable.treadmill);
        pictures.add(R.drawable.leg_press);
        pictures.add(R.drawable.leg_curl);
        pictures.add(R.drawable.leg_extension);
        pictures.add(R.drawable.hip_abduction);
        pictures.add(R.drawable.crunch);
        pictures.add(R.drawable.pulldown);
        pictures.add(R.drawable.arm_adduction);
        pictures.add(R.drawable.arm_curl);
        pictures.add(R.drawable.arm_extension);
    }
}