package com.perubdev.nmpinformaticse_sport

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.perubdev.nmpinformaticse_sport.databinding.WhatWePlayCardBinding

class WhatWePlayAdapter(): RecyclerView.Adapter<WhatWePlayAdapter.WhatWePlayViewHolder>() {
    class WhatWePlayViewHolder(val binding: WhatWePlayCardBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WhatWePlayViewHolder {
        val binding = WhatWePlayCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false)
        return WhatWePlayViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return GameData.games.size
    }

    override fun onBindViewHolder(holder: WhatWePlayViewHolder, position: Int) {
        holder.binding.imgGame.setImageResource(GameData.games[position].imageId)
        holder.binding.txtGame.text = GameData.games[position].game
        holder.binding.txtDescription.text = GameData.games[position].description

        holder.binding.btnAchievements.setOnClickListener {
            val intent = Intent(holder.itemView.context, AchievementsDetail::class.java)
            intent.putExtra(R.string.achievement_index.toString(), position)
            holder.itemView.context.startActivity(intent)
        }


    }
}