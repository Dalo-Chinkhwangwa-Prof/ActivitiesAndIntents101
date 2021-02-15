package com.britishbroadcast.activitiesandintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private EditText playerNameEditText;
    private Button finishButton;
    private TextView scoreTextView;


    public static final String PROFILE_KEY = "profile_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        playerNameEditText = findViewById(R.id.player_name_edittext);
        finishButton = findViewById(R.id.finish_button);
        scoreTextView = findViewById(R.id.score_textview);

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                String name = playerNameEditText.getText().toString().trim();
                StringBuilder s = new StringBuilder();

                resultIntent.putExtra(PROFILE_KEY, s.append(name).append("'s").toString());
                setResult(707, resultIntent);
                finish();//OnDestroy will be called without onStop - onStop is skipped
                //this will destroy the activity
            }
        });

        int currentScore= getIntent().getIntExtra(PROFILE_KEY, -1);
        scoreTextView.setText(getString(R.string.score_text, "", currentScore));
    }
}