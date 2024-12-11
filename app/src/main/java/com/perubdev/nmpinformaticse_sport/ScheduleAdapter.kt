package com.perubdev.nmpinformaticse_sport

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.perubdev.nmpinformaticse_sport.databinding.SchedulePageCardBinding
import com.squareup.picasso.Picasso

class ScheduleAdapter(val schedule:ArrayList<Schedule>): RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {
    class ScheduleViewHolder(val binding:SchedulePageCardBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val binding = SchedulePageCardBinding.inflate(LayoutInflater.from(parent.context),
            parent,false)
        return ScheduleViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return schedule.size
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val schUrl = schedule[position].img

        with(holder.binding){
            val builder = Picasso.Builder(holder.itemView.context)
            builder.listener { picasso, uri, exception -> exception.printStackTrace() }
            Picasso.get().load(schUrl).into(imgLogo)
            txtChamp.text = schedule[position].name
            txtDate.text = schedule[position].date
            txtPlace.text = schedule[position].description
        }

        holder.binding.cardSchedule.setOnClickListener {
            val intent = Intent(holder.itemView.context, SchedulePageDetail::class.java)
            intent.putExtra("schedule_index".toString(), position)
            holder.itemView.context.startActivity(intent)
        }
    }
}