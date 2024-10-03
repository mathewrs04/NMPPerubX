package com.perubdev.nmpinformaticse_sport

import android.R
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.perubdev.nmpinformaticse_sport.databinding.ActivityAchievementsDetailBinding

class AchievementsDetail : AppCompatActivity() {
    private lateinit var binding: ActivityAchievementsDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAchievementsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Define achievements data
        val achievements = mapOf(
            "2023" to arrayOf("Won XYZ Tournament", "Top 3 in ABC Event"),
            "2024" to arrayOf("Champion of DEF Tournament", "Runner-up in GHI Event"),
            "All" to arrayOf(
                "Won XYZ Tournament (2023)",
                "Top 3 in ABC Event (2023)",
                "Champion of DEF Tournament (2024)",
                "Runner-up in GHI Event (2024)"
            )
        )

        // spinner
        val years = arrayOf("All", "2023", "2024")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, years)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerAchievement.adapter = adapter

        // Handle spinner item change
        binding.spinnerAchievement.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedYear = years[p2]
                val achievementsForYear = achievements[selectedYear]

                // Update textView with the achievements
                binding.txtAchievement.text = achievementsForYear?.joinToString(separator = "\n") ?: "No achievements available"
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // Do nothing
            }
        }



    }
}