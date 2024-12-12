package com.perubdev.nmpinformaticse_sport

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.perubdev.nmpinformaticse_sport.ScheduleAdapter.ScheduleViewHolder
import com.perubdev.nmpinformaticse_sport.databinding.ProposalListBinding
import com.perubdev.nmpinformaticse_sport.databinding.SchedulePageCardBinding

class ProposalAdapter(var proposals: ArrayList<Proposal>):
    RecyclerView.Adapter<ProposalAdapter.ProposalViewHolder>() {

    class ProposalViewHolder(val binding: ProposalListBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProposalViewHolder {
        val binding = ProposalListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false)
        return ProposalViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return proposals.size
    }

    override fun onBindViewHolder(holder: ProposalViewHolder, position: Int) {
        holder.binding.txtGame.text = proposals[position].game
        holder.binding.txtStatus.text = proposals[position].status
    }
}