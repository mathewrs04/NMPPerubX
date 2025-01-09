package com.perubdev.nmpinformaticse_sport

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.perubdev.nmpinformaticse_sport.databinding.WhatWePlayCardBinding
import com.squareup.picasso.Picasso

class WhatWePlayAdapter(val games:ArrayList<Game>): RecyclerView.Adapter<WhatWePlayAdapter.WhatWePlayViewHolder>() {
    class WhatWePlayViewHolder(val binding: WhatWePlayCardBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WhatWePlayViewHolder {
        val binding = WhatWePlayCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false)
        return WhatWePlayViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: WhatWePlayViewHolder, position: Int) {
        val url = games[position].img


        with(holder.binding) {
            val builder = Picasso.Builder(holder.itemView.context)
            builder.listener { picasso, uri, exception -> exception.printStackTrace() }
            Picasso.get().load(url).into(imgGame)

            txtGame.text = games[position].name
            txtDescription.text = games[position].description
        }

        holder.binding.btnAchievements.setOnClickListener {
            val intent = Intent(holder.itemView.context, AchievementsDetail::class.java)
            intent.putExtra("gameData", games[position])
            holder.itemView.context.startActivity(intent)
        }

        holder.binding.btnTeam.setOnClickListener {
            val intent = Intent(holder.itemView.context, TeamPage::class.java)
            intent.putExtra("gameData",games[position])
            holder.itemView.context.startActivity(intent)
        }


    }
}