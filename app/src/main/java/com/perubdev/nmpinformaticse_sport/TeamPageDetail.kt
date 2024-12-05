package com.perubdev.nmpinformaticse_sport

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.perubdev.nmpinformaticse_sport.databinding.ActivityTeamPageBinding
import com.perubdev.nmpinformaticse_sport.databinding.ActivityTeamPageDetailBinding
import com.squareup.picasso.Picasso
import org.json.JSONObject

class TeamPageDetail : AppCompatActivity() {
    private lateinit var binding: ActivityTeamPageDetailBinding

    private var teamMembers: ArrayList<TeamMember> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamPageDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val teamData = intent.getParcelableExtra<Team>("teamData")

        if (teamData!= null) {
            getTeamMembers(teamData.idteam)
            Picasso.get().load(teamData.gameimg).into(binding.imgTeamGame)
        }
    }

    fun updateList() {
        val lm = LinearLayoutManager(this)
        with(binding.recTeamDetail) {
            layoutManager = lm
            setHasFixedSize(true)
            adapter = TeamDetailAdapter(teamMembers)
        }

    }

    private fun getTeamMembers(idteam: Int) {
        val q = Volley.newRequestQueue(this)
        val url = "https://ubaya.xyz/native/160422023/get_team_members.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            {
                Log.d("apiresult", it)

                val obj = JSONObject(it)
                if (obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    val sType = object : TypeToken<List<TeamMember>>() {}.type
                    teamMembers = Gson().fromJson(data.toString(), sType) as ArrayList<TeamMember>


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
                params["idteam"] = idteam.toString()
                return params
            }
        }

        q.add(stringRequest)
    }
}
