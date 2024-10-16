package com.perubdev.nmpinformaticse_sport

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.perubdev.nmpinformaticse_sport.databinding.PlayerCardBinding

class TeamDetailAdapter(private val gameIndex: Int, private val teamIndex: Int) :
    RecyclerView.Adapter<TeamDetailAdapter.TeamDetailViewHolder>() {

    class TeamDetailViewHolder(val binding: PlayerCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamDetailViewHolder {
        val binding = PlayerCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return TeamDetailViewHolder(binding)
    }

    override fun getItemCount(): Int {
        val game = GameData.games[gameIndex]
        val team = game.teams[teamIndex]
        return team.members.size
    }

    override fun onBindViewHolder(holder: TeamDetailViewHolder, position: Int) {
        val game = GameData.games[gameIndex]
        val team = game.teams[teamIndex]

        val player = team.members[position]

        holder.binding.imgPlayer.setImageResource(player.profileImageId)
        holder.binding.txtPlayerName.text = player.name
        holder.binding.txtRole.text = player.role
    }
}
