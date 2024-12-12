package com.perubdev.nmpinformaticse_sport

import android.content.Context
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
import com.perubdev.nmpinformaticse_sport.databinding.ActivityApplyTeamNewBinding
import org.json.JSONObject

class ApplyTeamNew : AppCompatActivity() {
    var games: ArrayList<Game> = ArrayList()
    var teams: ArrayList<Team> = ArrayList()

    private lateinit var binding: ActivityApplyTeamNewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApplyTeamNewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load games when activity starts
        loadGames()

        // Listener for spinnerGame
        binding.spinnerGame.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (games.isNotEmpty()) {
                    val selectedGame = games[position]
                    loadTeams(selectedGame.idgame)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.btnApply.setOnClickListener {

            val selectedTeamIndex = binding.spinnerTeam.selectedItemPosition

            val selectedTeam = teams[selectedTeamIndex]
            val description = binding.txtDescription.text.toString()


            // Ambil idmember dari SharedPreferences
            val sharedPreferences = getSharedPreferences("UserSession", Context.MODE_PRIVATE)
            val idMember = sharedPreferences.getInt("idmember", -1)
            // Volley request for sending proposal
            val queue = Volley.newRequestQueue(this)
            val url = "https://ubaya.xyz/native/160422023/new_proposal.php"

            val stringRequest = object : StringRequest(
                Request.Method.POST,
                url,
                {
                    val obj = JSONObject(it)
                    if (obj.getString("result") == "OK") {

                        Log.d("apiresult", "Proposal submitted successfully")
                    }
                },
                {

                    Log.e("apiresult", it.message.toString())
                }
            ) {
                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    params["idmember"] = idMember.toString()
                    params["idteam"] = selectedTeam.idteam.toString()
                    params["description"] = description
                    return params
                }
            }
            queue.add(stringRequest)
        }




    }

    private fun loadGames() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://ubaya.xyz/native/160422023/get_game.php"

        val stringRequest = StringRequest(
            Request.Method.POST,
            url,
            {
                val obj = JSONObject(it)
                if (obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    val sType = object : TypeToken<List<Game>>() {}.type
                    games = Gson().fromJson(data.toString(), sType) as ArrayList<Game>

                    val gameNames = games.map { it.name }
                    val adapter = ArrayAdapter(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        gameNames
                    )
                    binding.spinnerGame.adapter = adapter
                }


            },
            {
                Log.e("apiresult", it.message.toString())
            }
        )
        queue.add(stringRequest)
    }

    private fun loadTeams(idgame: Int) {
        val queue = Volley.newRequestQueue(this)
        val url = "https://ubaya.xyz/native/160422023/get_team_game.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            {

                    val obj = JSONObject(it)
                    if (obj.getString("result") == "OK") {
                        val data = obj.getJSONArray("data")
                        val sType = object : TypeToken<List<Team>>() {}.type
                        teams = Gson().fromJson(data.toString(), sType) as ArrayList<Team>

                        val teamNames = teams.map { it.name }
                        val adapter = ArrayAdapter(
                            this,
                            android.R.layout.simple_spinner_dropdown_item,
                            teamNames
                        )
                        binding.spinnerTeam.adapter = adapter
                    }

            },
            {
                Log.e("apiresult", it.message.toString())
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["idgame"] = idgame.toString()
                return params
            }
        }
        queue.add(stringRequest)
    }
}
