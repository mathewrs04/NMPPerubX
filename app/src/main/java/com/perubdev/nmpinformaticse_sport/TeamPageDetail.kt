package com.perubdev.nmpinformaticse_sport

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.perubdev.nmpinformaticse_sport.databinding.ActivityTeamPageDetailBinding

class TeamPageDetail : AppCompatActivity() {
    private lateinit var binding: ActivityTeamPageDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamPageDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gameIndex = intent.getIntExtra(R.string.game_index.toString(), 0)
        val teamIndex = intent.getIntExtra(R.string.team_index.toString(), 0)

        binding.imgTeamGame.setImageResource(GameData.games[gameIndex].imageId)


        binding.recTeamDetail.layoutManager = LinearLayoutManager(this)
        binding.recTeamDetail.setHasFixedSize(true)
        binding.recTeamDetail.adapter = TeamDetailAdapter(gameIndex, teamIndex)
    }
}
