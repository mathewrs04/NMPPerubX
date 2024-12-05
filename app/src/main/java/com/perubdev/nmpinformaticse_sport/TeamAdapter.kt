package com.perubdev.nmpinformaticse_sport

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.perubdev.nmpinformaticse_sport.databinding.TeamCardBinding
import com.squareup.picasso.Picasso

class TeamAdapter(val teams: ArrayList<Team>): RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {
    class TeamViewHolder(val binding:TeamCardBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding = TeamCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false)
        return TeamViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return teams.size
    }


    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {

        with(holder.binding) {
            txtTeamName.text = teams[position].name
        }

        holder.binding.cardTeam.setOnClickListener {
            val intent = Intent(holder.itemView.context, TeamPageDetail::class.java)
            intent.putExtra("teamData", teams[position])
            holder.itemView.context.startActivity(intent)
        }
    }



}