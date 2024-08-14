package com.fahomid.moodtracker

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat

class Settings : AppCompatActivity() {

    // Declare SharedPreferences for storing user preferences
    private lateinit var sharedPreferences: SharedPreferences

    // Declare the SwitchCompat for toggling night mode
    private lateinit var themeSwitch: SwitchCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Initialize SharedPreferences to save and retrieve the night mode state
        sharedPreferences = getSharedPreferences("AppSettings", MODE_PRIVATE)

        // Find the SwitchCompat view in the layout by its ID
        themeSwitch = findViewById(R.id.theme_switch)

        // Get the saved night mode state from SharedPreferences and set the switch accordingly
        val isNightMode = sharedPreferences.getBoolean("NightMode", false)
        themeSwitch.isChecked = isNightMode

        // Apply the night mode based on the saved state
        AppCompatDelegate.setDefaultNightMode(
            if (isNightMode) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )

        // Set a listener for the switch to toggle night mode on or off
        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Enable night mode when switch is checked
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                // Disable night mode when switch is unchecked
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

            // Save the new state of night mode in SharedPreferences
            sharedPreferences.edit().putBoolean("NightMode", isChecked).apply()
        }
    }
}
