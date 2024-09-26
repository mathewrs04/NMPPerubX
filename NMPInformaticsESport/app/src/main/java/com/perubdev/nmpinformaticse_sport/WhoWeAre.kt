package com.perubdev.nmpinformaticse_sport

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.perubdev.nmpinformaticse_sport.databinding.ActivityWhoWeAreBinding



class WhoWeAre : AppCompatActivity() {
    private  lateinit var binding:ActivityWhoWeAreBinding
    private var likeCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWhoWeAreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLike.setOnClickListener {
            likeCount++
            updateLikeButtonText()
            updateLikeButtonColor()
        }
    }

    private fun updateLikeButtonText() {
        binding.btnLike.text = "$likeCount"
    }

    private fun updateLikeButtonColor() {

        if (likeCount >= 1) {
            binding.btnLike.setBackgroundColor(Color.rgb(128, 0, 128))
            binding.btnLike.setTextColor(Color.WHITE)


        }
    }
}