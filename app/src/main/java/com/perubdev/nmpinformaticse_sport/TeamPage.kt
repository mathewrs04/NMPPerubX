package com.perubdev.nmpinformaticse_sport

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.perubdev.nmpinformaticse_sport.databinding.ActivityTeamPageBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso
import org.json.JSONObject


class TeamPage : AppCompatActivity() {
    private var teams: ArrayList<Team> = ArrayList()
    private lateinit var binding:ActivityTeamPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamPageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val gameData = intent.getParcelableExtra<Game>("gameData")

        if (gameData!= null) {
            getTeams(gameData.idgame)
            Picasso.get().load(gameData.img).into(binding.imgTeamGame)
        }
    }

    fun updateList() {
        val lm = LinearLayoutManager(this)
        with(binding.recTeam) {
            layoutManager = lm
            setHasFixedSize(true)
            adapter = TeamAdapter(teams)
        }

    }

    private fun getTeams(idgame: Int) {
        val q = Volley.newRequestQueue(this)
        val url = "https://ubaya.xyz/native/160422023/get_team.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            {
                Log.d("apiresult", it)

                val obj = JSONObject(it)
                if (obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    val sType = object : TypeToken<List<Team>>() {}.type
                    teams = Gson().fromJson(data.toString(), sType) as ArrayList<Team>


                    updateList()
                } else {
                    Log.e("apiresult", "No teams found")
                }
            },
            {
                Log.e("apiresult", it.message.toString())
            }
        ) {

            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["idgame"] = idgame.toString()
                return params
            }
        }

        q.add(stringRequest)
    }




}