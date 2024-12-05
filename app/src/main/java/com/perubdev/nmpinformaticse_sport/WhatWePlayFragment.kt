package com.perubdev.nmpinformaticse_sport

import android.R
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.ListFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import com.perubdev.nmpinformaticse_sport.databinding.FragmentWhatWePlayBinding
import org.json.JSONObject

private const val KEY_GAMES = "games PerubX"
class WhatWePlayFragment : Fragment() {
    private var games: ArrayList<Game> = ArrayList()
    private lateinit var binding: FragmentWhatWePlayBinding

    fun updateList() {
        val lm = LinearLayoutManager(activity)
        with(binding.recGame) {
            layoutManager = lm
            setHasFixedSize(true)
            adapter = WhatWePlayAdapter(games)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val q = Volley.newRequestQueue(activity)
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
                    updateList()
                    Log.d("apiresult", games.toString())}
            },
            {
                Log.e("apiresult", it.message.toString())
            }
        )

        q.add(stringRequest)


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Set up binding and initialize RecyclerView here
        binding = FragmentWhatWePlayBinding.inflate(inflater, container, false)



        return binding.root
    }





}