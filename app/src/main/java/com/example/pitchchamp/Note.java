package com.example.pitchchamp;

public class Note {
    String lyric;
    double duration;
    String note;
    Note next;

    public Note(String lyric, double duration, String note) {
        this.lyric = lyric;
        this.duration = duration;
        this.note = note;
        this.next = null;
    }
}
