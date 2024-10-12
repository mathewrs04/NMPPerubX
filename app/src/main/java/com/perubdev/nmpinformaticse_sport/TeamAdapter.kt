package com.perubdev.nmpinformaticse_sport

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.perubdev.nmpinformaticse_sport.databinding.TeamCardBinding

class TeamAdapter(): RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {
    class TeamViewHolder(val binding:TeamCardBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding = TeamCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false)
        return TeamViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return GameData.games.size
    }


    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val game = GameData.games[position]
        holder.binding.imgPurp.setImageResource(R.drawable.purple)
        val firstTeamName = game.teams[0].name
        holder.binding.txtTeamName.text = firstTeamName

        holder.binding.cardSchedule.setOnClickListener {
            val intent = Intent(holder.itemView.context, TeamPageDetail::class.java)
            intent.putExtra(R.string.schedule_index.toString(), position)
            holder.itemView.context.startActivity(intent)
        }
    }



}