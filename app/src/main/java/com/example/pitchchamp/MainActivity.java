package com.example.pitchchamp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private ImageView profileImage;
    private TextView userName;
    private TextView userEmail;
    private TextView userBio;
    private Chronometer chronometer;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the toolbar (assuming you have a toolbar in your layout)
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize the chronometer and other views
        chronometer = findViewById(R.id.chronometer);
        profileImage = findViewById(R.id.profile_image);
        userName = findViewById(R.id.user_name);
        userEmail = findViewById(R.id.user_email); // Make sure this ID exists
        userBio = findViewById(R.id.user_bio);     // Make sure this ID exists

        // Start the chronometer
        chronometer.start();

        // Set default values
        userName.setText("Richard Yang");
        userEmail.setText("richard.yang@example.com");
        userBio.setText("A brief bio of Richard Yang...");

        // Setup the DrawerLayout and NavigationView
        drawer = findViewById(R.id.drawer_layout); // Corrected the ID here
        NavigationView navigationView = findViewById(R.id.view_navigation);

        // Setup the ActionBarDrawerToggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Handle navigation item selection
        navigationView.setNavigationItemSelectedListener(item -> {
            // Handle navigation view item clicks here.
            int id = item.getItemId();

            if (id == R.id.nav_account) {

            } else if (id == R.id.nav_preferences) {


                // Handle the preferences action
            } else if (id == R.id.nav_notifications) {


                // Handle the notifications action
            } else if (id == R.id.nav_sign_out) {
                // Handle the sign out action


                Intent intent = new Intent(MainActivity.this, RecordingPlayback.class);
                startActivity(intent);

            } else if (id == R.id.nav_help) {
                // Handle the help action

            }

            drawer.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
