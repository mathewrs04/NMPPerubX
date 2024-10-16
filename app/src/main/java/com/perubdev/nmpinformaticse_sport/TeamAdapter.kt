package com.perubdev.nmpinformaticse_sport

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.perubdev.nmpinformaticse_sport.databinding.TeamCardBinding

class TeamAdapter(private val gameIndex: Int): RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {
    class TeamViewHolder(val binding:TeamCardBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding = TeamCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false)
        return TeamViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return GameData.games[gameIndex].teams.size
    }


    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val game = GameData.games[gameIndex]
        val team = game.teams[position]

        holder.binding.txtTeamName.text = team.name

        holder.binding.cardTeam.setOnClickListener {
            val intent = Intent(holder.itemView.context, TeamPageDetail::class.java)
            intent.putExtra(R.string.game_index.toString(), gameIndex)
            intent.putExtra(R.string.team_index.toString(), position)
            holder.itemView.context.startActivity(intent)
        }
    }



}