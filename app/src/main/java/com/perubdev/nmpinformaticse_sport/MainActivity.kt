package com.perubdev.nmpinformaticse_sport

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.perubdev.nmpinformaticse_sport.databinding.ActivityMainBinding
import com.perubdev.nmpinformaticse_sport.databinding.DrawerLayoutBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: DrawerLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =DrawerLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.main.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        var drawerToggle = ActionBarDrawerToggle(this, binding.root,
            binding.main.toolbar, R.string.app_name, R.string.app_name)

        drawerToggle.isDrawerIndicatorEnabled = true
        drawerToggle.syncState()

        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.itemApply -> {
                    // Navigate to Apply Team Activity
                    val intent = Intent(this, ProposalList::class.java)
                    startActivity(intent)
                }
                R.id.itemSignOut -> {
                    // Sign out and return to login screen
                    Snackbar.make(binding.root, "Signing out...", Snackbar.LENGTH_SHORT).show()

                    // Clear user session data if any (example: shared preferences)
                    clearSession()

                    // Redirect to Sign-In Activity
                    val intent = Intent(this, SignIn::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            }
            binding.root.closeDrawer(GravityCompat.START)
            true
        }


        val fragments: ArrayList<Fragment> = ArrayList()
        fragments.add(WhatWePlayFragment())
        fragments.add(WhoWeAreFragment())
        fragments.add(SchedulePageFragment())

        binding.main.viewPager.adapter = MyAdapter(this, fragments)

        //viewpager
        binding.main.viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding.main.bottomNav.selectedItemId = binding.main.bottomNav.menu.getItem(position).itemId
            }
        })

        //bottomnav
        binding.main.bottomNav.setOnItemSelectedListener {
            if(it.itemId == R.id.ItemWhatWePlay){
                binding.main.viewPager.currentItem = 0
            }
            else if(it.itemId == R.id.ItemWhoWeAre){
                binding.main.viewPager.currentItem = 1
            }
            else{
                binding.main.viewPager.currentItem = 2
            }
            true
        }
    }

    private fun clearSession() {
        // Example of clearing user session (SharedPreferences)
        val sharedPref = getSharedPreferences("UserSession", MODE_PRIVATE)
        sharedPref.edit().clear().apply()
    }
}