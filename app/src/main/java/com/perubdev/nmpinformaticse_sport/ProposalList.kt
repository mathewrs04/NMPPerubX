package com.perubdev.nmpinformaticse_sport

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.perubdev.nmpinformaticse_sport.databinding.ActivityProposalListBinding

class ProposalList : AppCompatActivity() {
    private lateinit var binding: ActivityProposalListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProposalListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}