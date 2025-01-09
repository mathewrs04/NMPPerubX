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
import com.squareup.picasso.Picasso
import org.json.JSONObject

class AchievementsDetail : AppCompatActivity() {
    private lateinit var binding: ActivityAchievementsDetailBinding
    private var allAchievements: List<Achievement> = emptyList()
    private var years: List<String> = listOf("All")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAchievementsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gameData = intent.getParcelableExtra<Game>("gameData")

        if (gameData!= null) {
            loadAchievements(gameData.idgame)
            Picasso.get().load(gameData.img).into(binding.imgGame)
            binding.txtGame.text = gameData.name
        }


    }

    private fun setupSpinner(years: List<String>) {

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
            {

                    val obj = JSONObject(it)
                    if (obj.getString("result") == "OK") {
                        val data = obj.getJSONArray("data")
                        allAchievements = Gson().fromJson(
                            data.toString(),
                            object : TypeToken<List<Achievement>>() {}.type
                        )

                        Log.d("AchievementsDetail", "Fetched ${allAchievements.size} achievements for gameId $gameId")

                        years = listOf("All") + allAchievements
                            .map { it.date }
                            .distinct()
                            .sorted()


                        setupSpinner(years)

                        filterAchievements("All")

                    } else {
                        binding.txtAchievement.text = "No achievements found"
                    }

            },
            {
                Log.e("apiresult", it.message.toString())
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
