package com.perubdev.nmpinformaticse_sport

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.perubdev.nmpinformaticse_sport.databinding.SchedulePageCardBinding

class ScheduleAdapter(): RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {
    class ScheduleViewHolder(val binding:SchedulePageCardBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val binding = SchedulePageCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false)
        return ScheduleViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return ScheduleData.schedules.size
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.binding.imgLogo.setImageResource(ScheduleData.schedules[position].imageId)
        holder.binding.txtChamp.text = ScheduleData.schedules[position].event
        holder.binding.txtPlace.text = ScheduleData.schedules[position].place
        holder.binding.txtTime.text = ScheduleData.schedules[position].time
    }


}