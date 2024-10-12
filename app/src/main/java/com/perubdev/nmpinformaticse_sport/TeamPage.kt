package com.perubdev.nmpinformaticse_sport

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.perubdev.nmpinformaticse_sport.databinding.ActivityTeamPageBinding
import androidx.recyclerview.widget.LinearLayoutManager


class TeamPage : AppCompatActivity() {
    private lateinit var binding:ActivityTeamPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recTeam.layoutManager = LinearLayoutManager(this)
        binding.recTeam.setHasFixedSize(true)
        binding.recTeam.adapter = TeamAdapter()
    }

}