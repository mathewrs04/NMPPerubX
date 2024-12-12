package com.perubdev.nmpinformaticse_sport

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.perubdev.nmpinformaticse_sport.databinding.ActivitySchedulePageDetailBinding


class SchedulePageDetail : AppCompatActivity() {
    private lateinit var binding:ActivitySchedulePageDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySchedulePageDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNotify.setOnClickListener {
            Toast.makeText(this, "Notification Created", Toast.LENGTH_LONG).show()
        }

        val index = intent.getIntExtra(R.string.schedule_index.toString(), 0)
        with(ScheduleData.schedules[index]) {
            binding.txtEvent.setText(event)
            binding.txtDate.setText(date)
            binding.txtPlace.setText(place)
            binding.txtTime.setText(time)
            binding.txtTeam.setText(team)
            binding.txtDescription.setText(description)

            val imageName = resources.getResourceEntryName(imageId)
            binding.imgPreview.setImageResource(imageId)
        }
    }
}