// SongLibrary.java
package com.example.pitchchamp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SongLibrary extends AppCompatActivity {

    private List<Song> songList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_library);

        // Create a list of songs (you may populate it from a database or other sources)
        songList = generateSampleSongs();

        // Find the RecyclerView by its ID
//        RecyclerView recyclerView = findViewById(R.id.recyclerViewSongs);
//
//        // Set up the RecyclerView with a LinearLayoutManager and an adapter
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        SongAdapter songAdapter = new SongAdapter(songList);
//        recyclerView.setAdapter(songAdapter);
    }

    // Generate sample songs (replace this with your data retrieval logic)
    private List<Song> generateSampleSongs() {
        List<Song> songs = new ArrayList<>();
        songs.add(new Song("Mary Had a Little Lamb", "Sarah Josepha Hale"));
        songs.add(new Song("Song 2", "Artist 2"));
        songs.add(new Song("Song 3", "Artist 3"));
        // Add more songs as needed
        return songs;
    }
}
