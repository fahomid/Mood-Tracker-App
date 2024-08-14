package com.fahomid.moodtracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView

// MoodDisplayActivity class is responsible for displaying the user's selected mood
class MoodDisplayActivity : AppCompatActivity() {

    // onCreate method is called when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the layout for this activity to activity_mood_display.xml
        setContentView(R.layout.activity_mood_display)

        // Handle window insets for the root layout to ensure proper padding for system UI elements
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemInsets.left, systemInsets.top, systemInsets.right, systemInsets.bottom)
            insets
        }

        // Retrieve the mood data passed from the previous activity
        val mood = intent.getStringExtra("USER_MOOD") ?: "No Mood Selected"

        // Display the retrieved mood in the TextView
        val moodTextView = findViewById<TextView>(R.id.moodTextView)
        val moodMessage = getString(R.string.mood_message, mood)
        moodTextView.text = moodMessage

        // Handle the fragment or any additional logic
        // Create a new instance of MoodFragment and pass the mood data to it
        val fragment = MoodFragment.newInstance(mood)
        // Begin a fragment transaction to replace the fragment container with the new fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
