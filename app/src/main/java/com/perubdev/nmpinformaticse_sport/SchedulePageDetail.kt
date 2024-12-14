package com.perubdev.nmpinformaticse_sport

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.perubdev.nmpinformaticse_sport.databinding.ActivitySchedulePageDetailBinding
import com.squareup.picasso.Picasso
import org.json.JSONObject

class SchedulePageDetail : AppCompatActivity() {
    private lateinit var binding: ActivitySchedulePageDetailBinding
    private var schedules: ArrayList<Schedule> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySchedulePageDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up notification button action
        binding.btnNotify.setOnClickListener {
            Toast.makeText(this, "Notification Created", Toast.LENGTH_LONG).show()
        }

        // Get schedule data from intent
        val scheduleData = intent.getParcelableExtra<Schedule>("scheduleData")
        if (scheduleData != null) {
            getScheduleDetail(scheduleData.idevent)
        } else {
            Toast.makeText(this, "No schedule data available", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getScheduleDetail(idevent: Int) {
        val q = Volley.newRequestQueue(this)
        val url = "https://ubaya.xyz/native/160422023/get_event_detail.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            { response ->
                Log.d("apiresult", response)

                val obj = JSONObject(response)
                if (obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    val sType = object : TypeToken<List<Schedule>>() {}.type
                    schedules = Gson().fromJson(data.toString(), sType) as ArrayList<Schedule>

                    val schUrl = schedules[0].img
                    Picasso.get().load(schUrl).into(binding.imgPreview)
                    binding.txtEvent.text = schedules[0].event_name
                    binding.txtDate.text = schedules[0].date
                    binding.txtTime.text = schedules[0].time
                    binding.txtPlace.text = schedules[0].place
                    binding.txtTeam.text = schedules[0].team_name
                    binding.txtDescription.text = schedules[0].description

                } else {
                    Toast.makeText(this, "Failed to fetch event details", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                Log.e("apiresult", "Error: ${error.message}")
                Toast.makeText(this, "Failed to load data", Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["idevent"] = idevent.toString()
                return params
            }
        }

        q.add(stringRequest)
    }
}
