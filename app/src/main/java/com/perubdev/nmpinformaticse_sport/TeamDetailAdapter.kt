package com.perubdev.nmpinformaticse_sport

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.perubdev.nmpinformaticse_sport.databinding.PlayerCardBinding
import com.squareup.picasso.Picasso

class TeamDetailAdapter(val teamMembers: ArrayList<TeamMember>) :
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
        return teamMembers.size
    }

    override fun onBindViewHolder(holder: TeamDetailViewHolder, position: Int) {
        val url = teamMembers[position].img


        with(holder.binding) {
            val builder = Picasso.Builder(holder.itemView.context)
            builder.listener { picasso, uri, exception -> exception.printStackTrace() }
            Picasso.get().load(url).into(imgPlayer)

            txtPlayerName.text = teamMembers[position].name
            txtRole.text = teamMembers[position].description
        }


    }
}
