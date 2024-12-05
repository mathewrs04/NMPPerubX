package com.perubdev.nmpinformaticse_sport

import android.R
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.perubdev.nmpinformaticse_sport.databinding.ActivityApplyTeamNewBinding
import org.json.JSONObject

class ApplyTeamNew : AppCompatActivity() {
    var games:ArrayList<Game> = ArrayList()
    var teams:ArrayList<Team> = ArrayList()

    private lateinit var binding:ActivityApplyTeamNewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApplyTeamNewBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.spinnerGame.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedGame = games[p2]
                loadTeams(selectedGame.idgame) // Load teams based on selected game
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }



    }

    private fun loadGames(){
        val q = Volley.newRequestQueue(this)
        val url = "https://ubaya.xyz/native/160422023/get_game.php"

        var stringRequest = StringRequest(
            Request.Method.POST,
            url,
            {
                Log.d("apiresult", it)

                val obj = JSONObject(it)
                if(obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    val sType = object : TypeToken<List<Game>>() { }.type
                    games = Gson().fromJson(data.toString(), sType) as
                            ArrayList<Game>

                    Log.d("apiresult", games.toString())}
            },
            {
                Log.e("apiresult", it.message.toString())
            }
        )
    }

    private fun loadTeams(idgame: Int) {
        val queue = Volley.newRequestQueue(this)
        val url = "https://ubaya.xyz/native/160422023/get_team_by_game.php"


        val stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            { response ->
                Log.d("apiresult", response)
                val obj = JSONObject(response)
                if (obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    val sType = object : TypeToken<List<Team>>() {}.type
                    teams = Gson().fromJson(data.toString(), sType) as ArrayList<Team>

                    // Populate spinnerTeam
                    val teamNames = teams.map { it.name } // Get only team names
                    val adapter = ArrayAdapter(
                        this,
                        R.layout.simple_spinner_dropdown_item,
                        teamNames
                    )
                    binding.spinnerTeam.adapter = adapter
                }
            },
            { error ->
                Log.e("apiresult", error.message.toString())
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