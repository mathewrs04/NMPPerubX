package com.perubdev.nmpinformaticse_sport

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.perubdev.nmpinformaticse_sport.databinding.ActivityWhatWePlayBinding

class WhatWePlay : AppCompatActivity() {
    private lateinit var binding:ActivityWhatWePlayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWhatWePlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recGame.layoutManager = LinearLayoutManager(this)
        binding.recGame.setHasFixedSize(true)
        binding.recGame.adapter = WhatWePlayAdapter()
    }
}