package com.perubdev.nmpinformaticse_sport

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.perubdev.nmpinformaticse_sport.databinding.ActivitySchedulePageBinding

class SchedulePage : AppCompatActivity() {
    private lateinit var binding:ActivitySchedulePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySchedulePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recSch.layoutManager = LinearLayoutManager(this)
        binding.recSch.setHasFixedSize(true)
        binding.recSch.adapter = ScheduleAdapter()


    }
}