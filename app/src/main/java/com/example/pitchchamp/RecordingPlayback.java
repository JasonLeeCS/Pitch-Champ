package com.example.pitchchamp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;

public class RecordingPlayback extends AppCompatActivity {

    private static final int REQUEST_PERMISSION_CODE = 100;
    private MediaRecorder mediaRecorder;
    private String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording_playback);

        // Find the record button by its ID
        Button recordButton = findViewById(R.id.btnRecord);

        // Set a click listener for the record button
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check for runtime permissions
                if (checkPermissions()) {
                    // Permissions are granted, start recording
                    startRecording();
                }
            }
        });
    }

    // Method to check and request runtime permissions
    private boolean checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) !=
                    PackageManager.PERMISSION_GRANTED) {
                // Permission not granted, request it
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        REQUEST_PERMISSION_CODE);
                return false;
            }
        }
        // Permission already granted
        return true;
    }

    // Handle permission request result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, start recording
                startRecording();
            } else {
                // Permission denied, inform the user
                Toast.makeText(this, "Permission denied. Cannot start recording.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Example method for starting the recording
    private void startRecording() {
        // Initialize MediaRecorder
        mediaRecorder = new MediaRecorder();

        // Set the audio source (in this case, the microphone)
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);

        // Set the output format and encoder
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        // Set the output file path
        filePath = Environment.getExternalStorageDirectory().getAbsolutePath() +
                "/recording.3gp";
        mediaRecorder.setOutputFile(filePath);

        try {
            // Prepare and start recording
            mediaRecorder.prepare();
            mediaRecorder.start();
            // Inform the user or update UI as needed
            Toast.makeText(this, "Recording started", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            // Handle exceptions appropriately (e.g., show an error message)
            e.printStackTrace();
        }
    }

    // Ensure to release resources when the activity is destroyed
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaRecorder != null) {
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }
}
