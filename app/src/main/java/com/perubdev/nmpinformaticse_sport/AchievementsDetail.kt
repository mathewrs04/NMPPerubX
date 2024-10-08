package com.perubdev.nmpinformaticse_sport

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.perubdev.nmpinformaticse_sport.databinding.ActivityAchievementsDetailBinding

class AchievementsDetail : AppCompatActivity() {
    private lateinit var binding: ActivityAchievementsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAchievementsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Define spinner options
        val years = arrayOf("All", "2020", "2023", "2024")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, years)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerAchievement.adapter = adapter

        // Handle spinner item change
        binding.spinnerAchievement.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedYear = years[p2]

                // Filter achievements directly on the array
                val filteredAchievements = if (selectedYear == "All") {
                    AchievementsData.achievements
                } else {
                    AchievementsData.achievements.filter { it.year.toString() == selectedYear }.toTypedArray()
                }

                // Group achievements by game
                val achievementsByGame = filteredAchievements.groupBy { it.game }

                // Build the achievement string with numbering
                val achievementsText = if (filteredAchievements.isNotEmpty()) {
                    achievementsByGame.map { (game, achievements) ->
                        "$game:\n" + achievements.mapIndexed { index, achievement ->
                            "${index + 1}. ${achievement.achivements} - ${achievement.team}"
                        }.joinToString(separator = "\n")
                    }.joinToString(separator = "\n\n")
                } else {
                    "No achievements available"
                }

                // Update textView with the formatted achievements
                binding.txtAchievement.text = achievementsText
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // Do nothing
            }
        }
    }
}
