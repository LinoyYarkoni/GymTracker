package com.example.gymtracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ExercisesActivity extends AppCompatActivity {
    private ListView listView;
    private List<String> names = new ArrayList<>();
    private List<Integer> pictures = new ArrayList<>();
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    private String chosenName;
    private int chosenWeight;
    private int chosenRepetitions;

    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        initNamesList();
        initPicturesList();

        listView = findViewById(R.id.listView);
        ListViewAdapter listViewAdapter = new ListViewAdapter(ExercisesActivity.this, names, pictures);
        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Integer chosenPicture = pictures.get(i);
            chosenName = names.get(i);
            createExercisesPopup(chosenName, chosenPicture);
        });
    }

    private void createExercisesPopup(String name, Integer picture){
        View popup = getLayoutInflater().inflate(R.layout.popup, null);
        TextView machineName = popup.findViewById(R.id.textViewMachineName);
        ImageView machinePicture = popup.findViewById(R.id.imageViewMachinePicture);
        Button addButton = popup.findViewById(R.id.buttonAdd);
        Button cancelButton = popup.findViewById(R.id.buttonCancel);

        machineName.setText(name);
        machinePicture.setImageResource(picture);

        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setView(popup);
        dialog = dialogBuilder.create();
        dialog.show();

        SeekBar weightSeekBar = popup.findViewById(R.id.seekBarWeight);
        TextView weightTextView = popup.findViewById(R.id.textViewCountWeight);
        seekBarWeightMove(weightSeekBar, weightTextView);

        SeekBar repetitionsSeekBar = popup.findViewById(R.id.seekBarRepetitions);
        TextView repetitionsTextView = popup.findViewById(R.id.textViewCountRepetitions);
        seekBarRepetitionsMove(repetitionsSeekBar, repetitionsTextView);

        layout = MainActivity.sMainActivity.findViewById(R.id.container);

        addButton.setOnClickListener(view -> {
            String title = String.format("%s\n%s: %s\n%s: %s", chosenName, getText(R.string.weight), chosenWeight, getText(R.string.repetitions), chosenRepetitions);
            addCard(title);
            finish();
        });

        cancelButton.setOnClickListener(view -> dialog.dismiss());
    }
    private void addCard(String title){
        View viewCard = getLayoutInflater().inflate(R.layout.card, null);
        TextView textViewTitle = viewCard.findViewById(R.id.name);
        Button buttonDelete = viewCard.findViewById(R.id.delete);

        textViewTitle.setText(title);
        buttonDelete.setOnClickListener(v -> layout.removeView(viewCard));

        if (layout == null){
            layout = MainActivity.sMainActivity.findViewById(R.id.container);
        }

        layout.addView(viewCard);
    }

    private void seekBarWeightMove(SeekBar weightSeekBar, TextView weightTextView){
        weightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int val = (progress * (seekBar.getWidth() - 2 * seekBar.getThumbOffset())) / seekBar.getMax() - 50;
                weightTextView.setText("" + progress*10);
                weightTextView.setX(seekBar.getX() + val + seekBar.getThumbOffset() / 2);
                chosenWeight = progress*10;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void seekBarRepetitionsMove(SeekBar repetitionsSeekBar, TextView repetitionsTextView){
        repetitionsSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int val = (progress * (seekBar.getWidth() - 2 * seekBar.getThumbOffset())) / seekBar.getMax()-15;
                repetitionsTextView.setText("" + progress);
                repetitionsTextView.setX(seekBar.getX() + val + seekBar.getThumbOffset() / 2);
                chosenRepetitions = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void initNamesList(){
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