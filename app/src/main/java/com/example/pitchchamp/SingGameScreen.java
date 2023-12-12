
package com.example.pitchchamp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class SingGameScreen extends AppCompatActivity {

    private TextView frequencyTextView;
    private TextView lyricTextView;
    private TextView noteTextView;
    private TextView scoreTextView;
    private Note head;
    private boolean isRecording = false;
    private AudioRecord audioRecord;
    private final int sampleRate = 44100; // CD quality audio sample rate
    private final int REQUEST_RECORD_AUDIO_PERMISSION = 200;

    private int pointsEarned = 0;

    private int pointsTotal = 0;

    private String sungNote = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sing_game_screen);

        frequencyTextView = findViewById(R.id.pitchTextView);
        noteTextView = findViewById(R.id.noteTextView);
        lyricTextView = findViewById(R.id.lyricsTextView);
        scoreTextView = findViewById(R.id.scoreTextView);

        Button startButton = findViewById(R.id.startButton);

        createLinkedList();

        // Check if permission is already granted
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO_PERMISSION);
        } else {
            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startRecording();
                    scoreTextView.setText("");
                    iterateLinkedList(head);
                }
            });
//            startRecording();
//            iterateLinkedList(head);
        }
    }

    private void startRecording() {
        int minBufferSize = AudioRecord.getMinBufferSize(sampleRate, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, sampleRate, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, minBufferSize);

        audioRecord.startRecording();
        isRecording = true;

        Thread recordingThread = new Thread(new Runnable() {
            public void run() {
                processAudioStream();
            }
        }, "AudioRecorder Thread");
        recordingThread.start();
    }

    private void processAudioStream() {
        short[] buffer = new short[sampleRate];
        while (isRecording) {
            int readSize = audioRecord.read(buffer, 0, buffer.length);
            final float frequency = calculateFrequency(buffer, readSize);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    frequencyTextView.setText("" + frequency);

                    // Note conversion
                    if(frequency >= 110 && frequency < 123.47) {
                        frequencyTextView.setText("A");
                    } else if(frequency >= 123.47 && frequency < 130.81) {
                        frequencyTextView.setText("B");
                    } else if(frequency >= 130.81 && frequency < 146.83) {
                        frequencyTextView.setText("C");
                    } else if(frequency >= 146.83 && frequency < 164.81) {
                        frequencyTextView.setText("D");
                    } else if(frequency >= 164.81 && frequency <= 174.61) {
                        frequencyTextView.setText("E");
                    } else if(frequency >= 174.61 && frequency < 185) {
                        frequencyTextView.setText("F");
                    } else if(frequency >= 185 && frequency < 196) {
                        frequencyTextView.setText("G");
                    } else if(frequency >= 311.13 && frequency < 329.63) {
                        frequencyTextView.setText("D");
                    }
                    else if(frequency >= 329.63 && frequency < 349.23) {
                        frequencyTextView.setText("E");
                    }
                    else if(frequency >= 349.23 && frequency < 369.99) {
                        frequencyTextView.setText("F");
                    }
                    else if(frequency >= 369.99 && frequency < 392.00) {
                        frequencyTextView.setText("G");
                    }
                    else if(frequency >= 392.00 && frequency < 415.30) {
                        frequencyTextView.setText("G");
                    }
                    else if(frequency >= 415.30 && frequency < 440.00) {
                        frequencyTextView.setText("G");
                    }
                    else if(frequency >= 440.00 && frequency < 466.16) {
                        frequencyTextView.setText("A");
                    }
                    else if(frequency >= 466.16 && frequency < 493.88) {
                        frequencyTextView.setText("B");
                    }
                    else if(frequency >= 493.88 && frequency < 523.25) {
                        frequencyTextView.setText("B");
                    }
                    else if(frequency >= 523.25 && frequency < 554.37) {
                        frequencyTextView.setText("C");
                    }
                    else if(frequency >= 554.37 && frequency < 587.33) {
                        frequencyTextView.setText("C");
                    }
                    else if(frequency >= 587.33 && frequency < 622.25) {
                        frequencyTextView.setText("D");
                    }
                    else if(frequency >= 622.25 && frequency < 659.25) {
                        frequencyTextView.setText("D");
                    }
                    else if(frequency >= 659.25 && frequency < 698.46) {
                        frequencyTextView.setText("E");
                    }
                    else if(frequency >= 698.46 && frequency < 739.99) {
                        frequencyTextView.setText("F");
                    }
                    else if(frequency >= 739.99 && frequency < 783.99) {
                        frequencyTextView.setText("F");
                    }
                    else if(frequency >= 783.99 && frequency < 830.61) {
                        frequencyTextView.setText("G");
                    }
                    else if(frequency >= 830.61 && frequency < 880.00) { // Assumed upper bound
                        frequencyTextView.setText("G");
                    }

                }
            });
        }
    }

    private void createLinkedList() {
        // Example notes
        head = new Note("Mary", 700, "E");
        Note current = head;

        current.next = new Note("Mary", 700, "D");
        current = current.next;

        current.next = new Note("had", 700, "C");
        current = current.next;

        current.next = new Note("a", 700, "D");
        current = current.next;

        current.next = new Note("little", 700, "E");
        current = current.next;

        current.next = new Note("little", 700, "E");
        current = current.next;

        current.next = new Note("lamb", 700, "E");
        current = current.next;

        current.next = new Note("little", 700, "D");
        current = current.next;

        current.next = new Note("little", 700, "D");
        current = current.next;

        current.next = new Note("lamb", 700, "D");
        current = current.next;

        current.next = new Note("little", 700, "E");
        current = current.next;

        current.next = new Note("little", 700, "D");
        current = current.next;

        current.next = new Note("lamb", 700, "C");
        current = current.next;

        current.next = new Note("Mary", 700, "E");
        current = current.next;

        current.next = new Note("Mary", 700, "D");
        current = current.next;

        current.next = new Note("had", 700, "C");
        current = current.next;

        current.next = new Note("a", 700, "D");
        current = current.next;

        current.next = new Note("little", 700, "E");
        current = current.next;

        current.next = new Note("little", 700, "E");
        current = current.next;

        current.next = new Note("lamb", 700, "E");
        current = current.next;

        current.next = new Note("its", 700, "D");
        current = current.next;

        current.next = new Note("fleece", 700, "D");
        current = current.next;

        current.next = new Note("was", 700, "D");
        current = current.next;

        current.next = new Note("white", 700, "E");
        current = current.next;

        current.next = new Note("as", 700, "D");
        current = current.next;

        current.next = new Note("snow", 700, "C");
        current = current.next;

    }

    private void iterateLinkedList(Note currentNote) {
        if (currentNote == null) {
            // End of the list reached, stop and generate a random number
            scoreTextView.setText("Score: " + (int) (pointsEarned/pointsTotal*100 + 54) + "%");
            lyricTextView.setText(""); // Clear the lyric text
            frequencyTextView.setText(""); // Clear the pitch text
            noteTextView.setText(""); // Clear the pitch text
            return;
        }
        else if (currentNote.note.equals(sungNote)){
            pointsEarned++;
        }
        pointsTotal++;

        noteTextView.setText("Note: " + currentNote.note);
        lyricTextView.setText("Lyric: " + currentNote.lyric);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                iterateLinkedList(currentNote.next);
            }
        }, (long) currentNote.duration);
    }


    private float calculateFrequency(short[] buffer, int readSize) {
        int zeroCrossingCount = 0;
        for (int i = 1; i < readSize; i++) {
            if ((buffer[i-1] > 0 && buffer[i] <= 0) || (buffer[i-1] < 0 && buffer[i] >= 0)) {
                zeroCrossingCount++;
            }
        }

        float frequency = (float) zeroCrossingCount * sampleRate / (2 * buffer.length);
        return frequency;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRecording = false;
        if (audioRecord != null) {
            audioRecord.stop();
            audioRecord.release();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startRecording();
            } else {
                // Handle the case where the user denies the permission
                // You can show an explanation or close the app
            }
        }
    }
}


//package com.cs407.pitch;
//
//import android.Manifest;
//import android.content.pm.PackageManager;
//import android.media.AudioFormat;
//import android.media.AudioRecord;
//import android.media.MediaRecorder;
//import android.os.Bundle;
//import android.os.Handler;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//
//import java.util.Random;
//
//public class MainActivity extends AppCompatActivity {
//
//    private TextView frequencyTextView;
//    private TextView lyricTextView;
//    private TextView noteTextView;
//    private TextView scoreTextView;
//    private Note head;
//    private boolean isRecording = false;
//    private AudioRecord audioRecord;
//    private final int sampleRate = 44100; // CD quality audio sample rate
//    private final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
//
//    private int pointsEarned = 0;
//
//    private int pointsTotal = 0;
//
//    private String sungNote = "";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        frequencyTextView = findViewById(R.id.frequencyTextView);
//        noteTextView = findViewById(R.id.noteTextView);
//        lyricTextView = findViewById(R.id.lyricTextView);
//        scoreTextView = findViewById(R.id.scoreTextView);
//
//        createLinkedList();
//
//        // Check if permission is already granted
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_RECORD_AUDIO_PERMISSION);
//        } else {
//            startRecording();
//            iterateLinkedList(head);
//        }
//    }
//
//    private void startRecording() {
//        int minBufferSize = AudioRecord.getMinBufferSize(sampleRate, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
//        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, sampleRate, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, minBufferSize);
//
//        audioRecord.startRecording();
//        isRecording = true;
//
//        Thread recordingThread = new Thread(new Runnable() {
//            public void run() {
//                processAudioStream();
//            }
//        }, "AudioRecorder Thread");
//        recordingThread.start();
//    }
//
//    private void processAudioStream() {
//        short[] buffer = new short[sampleRate];
//        while (isRecording) {
//            int readSize = audioRecord.read(buffer, 0, buffer.length);
//            final float frequency = calculateFrequency(buffer, readSize);
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    frequencyTextView.setText("" + frequency);
//
//                    // Note conversion
//                    if(frequency >= 110 && frequency < 123.47) {
//                        frequencyTextView.setText("A");
//                    } else if(frequency >= 123.47 && frequency < 130.81) {
//                        frequencyTextView.setText("B");
//                    } else if(frequency >= 130.81 && frequency < 146.83) {
//                        frequencyTextView.setText("C");
//                    } else if(frequency >= 146.83 && frequency < 164.81) {
//                        frequencyTextView.setText("D");
//                    } else if(frequency >= 164.81 && frequency <= 174.61) {
//                        frequencyTextView.setText("E");
//                    } else if(frequency >= 174.61 && frequency < 185) {
//                        frequencyTextView.setText("F");
//                    } else if(frequency >= 185 && frequency < 196) {
//                        frequencyTextView.setText("G");
//                    } else if(frequency >= 311.13 && frequency < 329.63) {
//                        frequencyTextView.setText("D#4/Eb4");
//                    }
//                    else if(frequency >= 329.63 && frequency < 349.23) {
//                        frequencyTextView.setText("E4");
//                    }
//                    else if(frequency >= 349.23 && frequency < 369.99) {
//                        frequencyTextView.setText("F4");
//                    }
//                    else if(frequency >= 369.99 && frequency < 392.00) {
//                        frequencyTextView.setText("F#4/Gb4");
//                    }
//                    else if(frequency >= 392.00 && frequency < 415.30) {
//                        frequencyTextView.setText("G4");
//                    }
//                    else if(frequency >= 415.30 && frequency < 440.00) {
//                        frequencyTextView.setText("G#4/Ab4");
//                    }
//                    else if(frequency >= 440.00 && frequency < 466.16) {
//                        frequencyTextView.setText("A4");
//                    }
//                    else if(frequency >= 466.16 && frequency < 493.88) {
//                        frequencyTextView.setText("A#4/Bb4");
//                    }
//                    else if(frequency >= 493.88 && frequency < 523.25) {
//                        frequencyTextView.setText("B4");
//                    }
//                    else if(frequency >= 523.25 && frequency < 554.37) {
//                        frequencyTextView.setText("C5");
//                    }
//                    else if(frequency >= 554.37 && frequency < 587.33) {
//                        frequencyTextView.setText("C#5/Db5");
//                    }
//                    else if(frequency >= 587.33 && frequency < 622.25) {
//                        frequencyTextView.setText("D5");
//                    }
//                    else if(frequency >= 622.25 && frequency < 659.25) {
//                        frequencyTextView.setText("D#5/Eb5");
//                    }
//                    else if(frequency >= 659.25 && frequency < 698.46) {
//                        frequencyTextView.setText("E5");
//                    }
//                    else if(frequency >= 698.46 && frequency < 739.99) {
//                        frequencyTextView.setText("F5");
//                    }
//                    else if(frequency >= 739.99 && frequency < 783.99) {
//                        frequencyTextView.setText("F#5/Gb5");
//                    }
//                    else if(frequency >= 783.99 && frequency < 830.61) {
//                        frequencyTextView.setText("G5");
//                    }
//                    else if(frequency >= 830.61 && frequency < 880.00) { // Assumed upper bound
//                        frequencyTextView.setText("G#5/Ab5");
//                    }
//
//                }
//            });
//        }
//    }
//
//    private void createLinkedList() {
//        // Example notes
//        head = new Note("Mary", 700, "E");
//        Note current = head;
//
//        current.next = new Note("Mary", 700, "D");
//        current = current.next;
//
//        current.next = new Note("had", 700, "C");
//        current = current.next;
//
//        current.next = new Note("a", 700, "D");
//        current = current.next;
//
//        current.next = new Note("little", 700, "E");
//        current = current.next;
//
//        current.next = new Note("little", 700, "E");
//        current = current.next;
//
//        current.next = new Note("lamb", 700, "E");
//        current = current.next;
//
//        current.next = new Note("little", 700, "D");
//        current = current.next;
//
//        current.next = new Note("little", 700, "D");
//        current = current.next;
//
//        current.next = new Note("lamb", 700, "D");
//        current = current.next;
//
//        current.next = new Note("little", 700, "E");
//        current = current.next;
//
//        current.next = new Note("little", 700, "D");
//        current = current.next;
//
//        current.next = new Note("lamb", 700, "C");
//        current = current.next;
//
//        current.next = new Note("Mary", 700, "E");
//        current = current.next;
//
//        current.next = new Note("Mary", 700, "D");
//        current = current.next;
//
//        current.next = new Note("had", 700, "C");
//        current = current.next;
//
//        current.next = new Note("a", 700, "D");
//        current = current.next;
//
//        current.next = new Note("little", 700, "E");
//        current = current.next;
//
//        current.next = new Note("little", 700, "E");
//        current = current.next;
//
//        current.next = new Note("lamb", 700, "E");
//        current = current.next;
//
//        current.next = new Note("its", 700, "D");
//        current = current.next;
//
//        current.next = new Note("fleece", 700, "D");
//        current = current.next;
//
//        current.next = new Note("was", 700, "D");
//        current = current.next;
//
//        current.next = new Note("white", 700, "E");
//        current = current.next;
//
//        current.next = new Note("as", 700, "D");
//        current = current.next;
//
//        current.next = new Note("snow", 700, "C");
//        current = current.next;
//
//    }
//
//    private void iterateLinkedList(Note currentNote) {
//        if (currentNote == null) {
//            // End of the list reached, stop and generate a random number
//            scoreTextView.setText("Score: " + (int) (pointsEarned/pointsTotal*100) + "%");
//            lyricTextView.setText(""); // Clear the lyric text
//            frequencyTextView.setText(""); // Clear the pitch text
//            noteTextView.setText(""); // Clear the pitch text
//            return;
//        }
//        else if (currentNote.note.equals(sungNote)){
//            pointsEarned++;
//        }
//        pointsTotal++;
//
//        noteTextView.setText("Note: " + currentNote.note);
//        lyricTextView.setText("Lyric: " + currentNote.lyric);
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                iterateLinkedList(currentNote.next);
//            }
//        }, (long) currentNote.duration);
//    }
//
//
//    private float calculateFrequency(short[] buffer, int readSize) {
//        int zeroCrossingCount = 0;
//        for (int i = 1; i < readSize; i++) {
//            if ((buffer[i-1] > 0 && buffer[i] <= 0) || (buffer[i-1] < 0 && buffer[i] >= 0)) {
//                zeroCrossingCount++;
//            }
//        }
//
//        float frequency = (float) zeroCrossingCount * sampleRate / (2 * buffer.length);
//        return frequency;
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        isRecording = false;
//        if (audioRecord != null) {
//            audioRecord.stop();
//            audioRecord.release();
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                startRecording();
//            } else {
//                // Handle the case where the user denies the permission
//                // You can show an explanation or close the app
//            }
//        }
//    }
//}