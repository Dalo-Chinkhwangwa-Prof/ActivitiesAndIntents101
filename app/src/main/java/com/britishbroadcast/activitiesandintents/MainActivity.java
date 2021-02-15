package com.britishbroadcast.activitiesandintents;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.britishbroadcast.activitiesandintents.ProfileActivity.PROFILE_KEY;

public class MainActivity extends AppCompatActivity {

    private int score = 0;
    private TextView scoreBoard;
    private Button clickAwayButton;
    private Button openSecondActivityButton;
    private String playerName = "";

    private final String BUNDLE_KEY = "SCORE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("TAG_X", "onCreate: ");

        scoreBoard = findViewById(R.id.main_textview);
        clickAwayButton = findViewById(R.id.click_away_button);

        openSecondActivityButton = findViewById(R.id.second_activity_button);
        openSecondActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent -> an action to be performed
                //2 types of intents ->
                //A) Explicit Intent
                //B) Implicit Intent
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra(PROFILE_KEY, score);

                startActivityForResult(intent, 707);

            }
        });

        updateScoreBoard();

        //lambda
        clickAwayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score++;
                updateScoreBoard();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("TAG_X", "RequestCode: " + requestCode);
//        if(requestCode == 707)//you know its from the profile activity
        playerName = data.getStringExtra(PROFILE_KEY);
        scoreBoard.setText(getString(R.string.score_text, playerName, score));
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("TAG_X", "Orientation " + newConfig.orientation);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.d("TAG_X", "might break");
        score = savedInstanceState.getInt(BUNDLE_KEY);
        updateScoreBoard();
    }

    protected void updateScoreBoard() {
        scoreBoard.setText(getString(R.string.score_text, playerName, score));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TAG_X", "onStart: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("TAG_X", "onRestart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TAG_X", "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TAG_X", "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TAG_X", "onStop: ");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(BUNDLE_KEY, score);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG_X", "onDestroy: ");
    }

}