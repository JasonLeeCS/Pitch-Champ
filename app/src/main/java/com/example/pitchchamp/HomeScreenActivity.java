package com.example.pitchchamp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        // Initialize buttons
        Button buttonPracticeGoal = findViewById(R.id.button_practice_goal);
        Button buttonLessons = findViewById(R.id.button_lessons);
        Button buttonSongs = findViewById(R.id.button_songs);
        Button buttonRecordings = findViewById(R.id.button_recordings);
        Button buttonKaraoke = findViewById(R.id.button_karaoke);
        Button buttonProfile = findViewById(R.id.button_profile);

        // Set onClick listeners
        buttonPracticeGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to PracticeGoalActivity
                Intent intent = new Intent(HomeScreenActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buttonLessons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to LessonsActivity
                Intent intent = new Intent(HomeScreenActivity.this, SongLessonLibrary.class);
                startActivity(intent);
            }
        });

        buttonSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to SongsActivity
                Intent intent = new Intent(HomeScreenActivity.this, SongLibrary.class);
                startActivity(intent);
            }
        });

        buttonRecordings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to RecordingPlayback
                Intent intent = new Intent(HomeScreenActivity.this, RecordingPlayback.class);
                startActivity(intent);
            }
        });

        buttonKaraoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to SongsActivity
                Intent intent = new Intent(HomeScreenActivity.this, SongLibrary.class);
                startActivity(intent);
            }
        });

        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to SongsActivity
                Intent intent = new Intent(HomeScreenActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Continue with other buttons...
    }
}
