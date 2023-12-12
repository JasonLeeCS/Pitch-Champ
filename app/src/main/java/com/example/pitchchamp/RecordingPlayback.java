package com.example.pitchchamp;

// Import statements
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecordingPlayback extends AppCompatActivity {

    private static final int REQUEST_PERMISSION_CODE = 100;
    private MediaRecorder mediaRecorder;
    private String filePath;
    private List<Recording> recordings = new ArrayList<>();
    private RecordingAdapter adapter;
    private long recordingStartTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording_playback);

        RecyclerView recyclerView = findViewById(R.id.recordingRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecordingAdapter(recordings);
        recyclerView.setAdapter(adapter);

        Button recordButton = findViewById(R.id.btnRecord);
        recordButton.setOnClickListener(view -> {
            if (checkPermissions()) {
                startRecording();
            }
        });

        Button stopButton = findViewById(R.id.btnRecord2);
        stopButton.setOnClickListener(view -> stopRecording());
    }

    private boolean checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_PERMISSION_CODE);
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startRecording();
            } else {
                Toast.makeText(this, "Permission denied. Cannot start recording.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startRecording() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/recording.3gp";
        mediaRecorder.setOutputFile(filePath);

        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
            Toast.makeText(this, "Recording started", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        recordingStartTime = System.currentTimeMillis();
    }

    private void stopRecording() {
        if (mediaRecorder != null) {
            try {
                mediaRecorder.stop();
                mediaRecorder.release();
                mediaRecorder = null;
                Toast.makeText(this, "Recording stopped", Toast.LENGTH_SHORT).show();

                // Add new recording to RecyclerView
//                Recording newRecording = new Recording();
//                newRecording.setTitle("New Recording");
//                newRecording.setDateRecorded("Date: " + /* current date */);
//                newRecording.setRecordingLength("Length: " + /* recording length */);
//                recordings.add(newRecording);
//                adapter.notifyDataSetChanged();

            } catch (Exception e) {
                e.printStackTrace();
            }

            Recording newRecording = new Recording();
            newRecording.setTitle("New Recording");
            newRecording.setDateRecordedToCurrentDate();
            newRecording.setRecordingLengthFromStartTime(recordingStartTime);
            recordings.add(newRecording);
            adapter.notifyDataSetChanged();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaRecorder != null) {
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }
}


//package com.example.pitchchamp;
//
//import android.Manifest;
//import android.content.pm.PackageManager;
//import android.media.MediaRecorder;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Environment;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//
//import java.io.IOException;
//
//public class RecordingPlayback extends AppCompatActivity {
//
//    private static final int REQUEST_PERMISSION_CODE = 100;
//    private MediaRecorder mediaRecorder;
//    private String filePath;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_recording_playback);
//
//        // Find the record button by its ID
//        Button recordButton = findViewById(R.id.btnRecord);
//
//        // Set a click listener for the record button
//        recordButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Check for runtime permissions
//                if (checkPermissions()) {
//                    // Permissions are granted, start recording
//                    startRecording();
//                }
//            }
//        });
//
//        Button stopButton = findViewById(R.id.btnRecord2);
//        stopButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                stopRecording();
//            }
//        });
//    }
//
//    // Method to check and request runtime permissions
//    private boolean checkPermissions() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) !=
//                    PackageManager.PERMISSION_GRANTED) {
//                // Permission not granted, request it
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.RECORD_AUDIO},
//                        REQUEST_PERMISSION_CODE);
//                return false;
//            }
//        }
//        // Permission already granted
//        return true;
//    }
//
//    // Handle permission request result
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        if (requestCode == REQUEST_PERMISSION_CODE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Permission granted, start recording
//                startRecording();
//            } else {
//                // Permission denied, inform the user
//                Toast.makeText(this, "Permission denied. Cannot start recording.",
//                        Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    // Example method for starting the recording
//    private void startRecording() {
//        // Initialize MediaRecorder
//        mediaRecorder = new MediaRecorder();
//
//        // Set the audio source (in this case, the microphone)
//        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//
//        // Set the output format and encoder
//        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//
//        // Set the output file path
//        filePath = Environment.getExternalStorageDirectory().getAbsolutePath() +
//                "/recording.3gp";
//        mediaRecorder.setOutputFile(filePath);
//
//        try {
//            // Prepare and start recording
//            mediaRecorder.prepare();
//            mediaRecorder.start();
//            // Inform the user or update UI as needed
//            Toast.makeText(this, "Recording started", Toast.LENGTH_SHORT).show();
//        } catch (IOException e) {
//            // Handle exceptions appropriately (e.g., show an error message)
//            e.printStackTrace();
//        }
//    }
//
//    private void stopRecording() {
//        if (mediaRecorder != null) {
//            try {
//                // Stop recording and release resources
//                mediaRecorder.stop();
//                mediaRecorder.release();
//                mediaRecorder = null;
//                // Inform the user or update UI as needed
//                Toast.makeText(this, "Recording stopped", Toast.LENGTH_SHORT).show();
//            } catch (Exception e) {
//                // Handle exceptions appropriately
//                e.printStackTrace();
//            }
//        }
//    }
//
//    // Ensure to release resources when the activity is destroyed
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (mediaRecorder != null) {
//            mediaRecorder.release();
//            mediaRecorder = null;
//        }
//    }
//}
