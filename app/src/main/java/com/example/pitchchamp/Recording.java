package com.example.pitchchamp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Recording {
    private String title;
    private String dateRecorded;
    private String recordingLength;

    public void setDateRecordedToCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        this.dateRecorded = "Date: " + dateFormat.format(new Date());
    }

    public void setRecordingLengthFromStartTime(long startTime) {
        long endTime = System.currentTimeMillis();
        long recordingTime = endTime - startTime; // time in milliseconds
        long seconds = (recordingTime / 1000) % 60;
        long minutes = (recordingTime / (1000 * 60)) % 60;
        long hours = recordingTime / (1000 * 60 * 60);
        this.recordingLength = String.format(Locale.getDefault(), "Length: %02d:%02d:%02d", hours, minutes, seconds);
    }

    public String getTitle(){
        return title;
    }

    public String getDateRecorded(){
        return dateRecorded;
    }

    public String getRecordingLength(){
        return recordingLength;
    }


    public void setTitle(String input){
        title = input;
    }
    public void setDateRecorded(String input){
        dateRecorded = input;
    }
    public void setRecordingLength(String input){
        recordingLength = input;
    }
}
