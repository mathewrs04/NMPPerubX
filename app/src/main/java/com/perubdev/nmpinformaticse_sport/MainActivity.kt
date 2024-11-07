package com.perubdev.nmpinformaticse_sport

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.perubdev.nmpinformaticse_sport.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragments: ArrayList<Fragment> = ArrayList()
        fragments.add(WhatWePlayFragment())
        fragments.add(WhoWeAreFragment())
        fragments.add(SchedulePageFragment())

        binding.viewPager.adapter = MyAdapter(this, fragments)

        //viewpager
        binding.viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding.bottomNav.selectedItemId = binding.bottomNav.menu.getItem(position).itemId
            }
        })

        //bottomnav
        binding.bottomNav.setOnItemSelectedListener {
            if(it.itemId == R.id.ItemWhatWePlay){
                binding.viewPager.currentItem = 0
            }
            else if(it.itemId == R.id.ItemWhoWeAre){
                binding.viewPager.currentItem = 1
            }
            else{
                binding.viewPager.currentItem = 2
            }
            true
        }
    }
}