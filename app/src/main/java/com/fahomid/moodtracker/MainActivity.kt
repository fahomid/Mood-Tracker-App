package com.fahomid.moodtracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Load theme preference before setting the content view
        val sharedPreferences = getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
        val isNightMode = sharedPreferences.getBoolean("NightMode", false)

        // Set the theme mode based on the saved preference
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        // Call the parent constructor and set the activity's layout
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up the MaterialToolbar as the ActionBar
        val toolbar: MaterialToolbar = findViewById(R.id.topAppBar)
        setSupportActionBar(toolbar)

        // Mood Buttons
        val happyButton = findViewById<Button>(R.id.happyButton)
        val sadButton = findViewById<Button>(R.id.sadButton)
        val neutralButton = findViewById<Button>(R.id.neutralButton)

        // Set onClickListeners for mood buttons
        happyButton.setOnClickListener {
            // Handle Happy button click and send the mood to MoodDisplayActivity
            sendMood("Happy")
        }

        sadButton.setOnClickListener {
            // Handle Sad button click and send the mood to MoodDisplayActivity
            sendMood("Sad")
        }

        neutralButton.setOnClickListener {
            // Handle Neutral button click and send the mood to MoodDisplayActivity
            sendMood("Neutral")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                // Handle Settings menu item click
                Log.d("MainActivity", "Settings clicked")

                // Start the Settings activity
                val intent = Intent(this, Settings::class.java)
                startActivity(intent)
                true
            }
            R.id.exit -> {
                // Handle Exit menu item click
                Log.d("MainActivity", "Exit clicked")

                // Finish the activity and exit the app
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun sendMood(mood: String) {
        // Create an intent to start MoodDisplayActivity with the selected mood
        val intent = Intent(this, MoodDisplayActivity::class.java)
        intent.putExtra("USER_MOOD", mood)
        startActivity(intent)
    }
}
