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

        // Ambil posisi game dari intent
        val gameIndex = intent.getIntExtra(R.string.achievement_index.toString(), 0)

        // Define spinner options
        val years = arrayOf("All", "2020","2021", "2022", "2023", "2024")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, years)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerAchievement.adapter = adapter

        // Handle spinner item change
        binding.spinnerAchievement.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedYear = years[p2]

                with(GameData.games[gameIndex]) {
                    binding.imgGame.setImageResource(imageId) // Set gambar game
                    binding.txtGame.setText(game)

                    val filteredAchievements = if (selectedYear == "All") {
                        achievements
                    } else {
                        achievements.filter { it.year.toString() == selectedYear }
                    }

                    val achievementsText = if (filteredAchievements.isNotEmpty()) {
                        filteredAchievements.mapIndexed { index, achievement ->
                            "${index + 1}. ${achievement.achievements} ${achievement.year} - ${achievement.team}"
                        }.joinToString(separator = "\n")
                    } else {
                        "No achievements available"
                    }

                    binding.txtAchievement.setText(achievementsText)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // Do nothing
            }
        }

    }
}

