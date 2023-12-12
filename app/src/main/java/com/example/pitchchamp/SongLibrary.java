package com.example.pitchchamp;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class SongLibrary extends AppCompatActivity {

    private RelativeLayout roundedBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_library);

        // Initialize the RelativeLayout
        roundedBox = findViewById(R.id.roundedBox);

        // Set an OnClickListener on the RelativeLayout
        roundedBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the SongDetailsActivity
                Intent intent = new Intent(SongLibrary.this, SingGameScreen.class);
                // You can also put extra data in the Intent if needed
                // intent.putExtra("key", "value");
                startActivity(intent);
            }
        });
    }
}
