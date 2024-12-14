package com.perubdev.nmpinformaticse_sport

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.perubdev.nmpinformaticse_sport.databinding.ActivityAchievementsDetailBinding
import org.json.JSONObject

class AchievementsDetail : AppCompatActivity() {
    private lateinit var binding: ActivityAchievementsDetailBinding
    private var allAchievements: List<Achievement> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAchievementsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gameId = intent.getIntExtra("game_id", -1)

        if (gameId == -1) {
            binding.txtAchievement.text = "Invalid game ID"
            return
        }

        setupSpinner()
        loadAchievements(gameId)
    }

    private fun setupSpinner() {
        val years = arrayOf("All", "2020", "2021", "2022", "2023", "2024")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, years)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerAchievement.adapter = adapter

        binding.spinnerAchievement.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedYear = binding.spinnerAchievement.selectedItem.toString()
                filterAchievements(selectedYear)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun loadAchievements(gameId: Int) {
        val queue = Volley.newRequestQueue(this)
        val url = "https://ubaya.xyz/native/160422023/get_achievement.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            { response ->
                try {
                    val obj = JSONObject(response)
                    if (obj.getString("result") == "OK") {
                        val data = obj.getJSONArray("data")
                        allAchievements = Gson().fromJson(
                            data.toString(),
                            object : TypeToken<List<Achievement>>() {}.type
                        )

                        Log.d("AchievementsDetail", "Fetched ${allAchievements.size} achievements for gameId $gameId")
                        filterAchievements("All")
                    } else {
                        binding.txtAchievement.text = "No achievements found"
                    }
                } catch (e: Exception) {
                    Log.e("AchievementsDetail", "Error parsing JSON: ${e.message}")
                    binding.txtAchievement.text = "Error loading achievements"
                }
            },
            { error ->
                Log.e("AchievementsDetail", "Volley error: ${error.message}")
                binding.txtAchievement.text = "Error loading achievements"
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                return hashMapOf("idgame" to gameId.toString())
            }
        }
        queue.add(stringRequest)
    }

    private fun filterAchievements(selectedYear: String) {
        val filteredAchievements = if (selectedYear == "All") {
            allAchievements
        } else {
            allAchievements.filter { it.date == selectedYear }
        }

        val achievementsText = if (filteredAchievements.isNotEmpty()) {
            filteredAchievements.mapIndexed { index, achievement ->
                "${index + 1}. ${achievement.name} (${achievement.date}) - ${achievement.description}"
            }.joinToString("\n")
        } else {
            "No achievements available for $selectedYear"
        }

        binding.txtAchievement.text = achievementsText
        Log.d("AchievementsDetail", "Filtered achievements: $filteredAchievements")
    }
}
