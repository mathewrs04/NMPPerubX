package com.perubdev.nmpinformaticse_sport

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.perubdev.nmpinformaticse_sport.databinding.ActivityMainBinding
import com.perubdev.nmpinformaticse_sport.databinding.DrawerLayoutBinding
import com.squareup.picasso.Picasso
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: DrawerLayoutBinding
    private var whoweare: ArrayList<wwa> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("UserSession", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (!isLoggedIn) {

            val intent = Intent(this, SignIn::class.java)

            startActivity(intent)
            finish()
        }


        binding = DrawerLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.main.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        val drawerToggle = ActionBarDrawerToggle(
            this, binding.root,
            binding.main.toolbar, R.string.app_name, R.string.app_name
        )

        fetchWhoWeAreData()

        drawerToggle.isDrawerIndicatorEnabled = true
        drawerToggle.syncState()

        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.itemApply -> {
                    val intent = Intent(this, ProposalList::class.java)
                    startActivity(intent)
                }
                R.id.itemSignOut -> {
                    Snackbar.make(binding.root, "Signing out...", Snackbar.LENGTH_SHORT).show()

                    clearSession()

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

        binding.main.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.main.bottomNav.selectedItemId = binding.main.bottomNav.menu.getItem(position).itemId
            }
        })

        binding.main.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.ItemWhatWePlay -> {
                    binding.main.viewPager.currentItem = 0
                    binding.main.toolbar.title = "What We Play"
                }
                R.id.ItemWhoWeAre -> {
                    binding.main.viewPager.currentItem = 1
                    binding.main.toolbar.title = "Who We Are"
                }
                R.id.ItemOurSchedule -> {
                    binding.main.viewPager.currentItem = 2
                    binding.main.toolbar.title = "Schedule"
                }
            }
            true
        }
    }



    private fun clearSession() {
        val sharedPref = getSharedPreferences("UserSession", MODE_PRIVATE)
        sharedPref.edit().clear().apply()
    }

    private fun fetchWhoWeAreData() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://ubaya.xyz/native/160422023/get_whoweare.php"

        val stringRequest = StringRequest(
            Request.Method.POST,
            url,
            {
                Log.d("apiresult", it)
                val obj = JSONObject(it)
                if (obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    val sType = object : TypeToken<List<wwa>>() {}.type
                    whoweare = Gson().fromJson(data.toString(), sType) as ArrayList<wwa>

                    if (whoweare.isNotEmpty()) {
                        val imageUrl = whoweare[0].url
                        updateDrawerHeader(imageUrl)
                    }

                } else {
                    Toast.makeText(this, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                }
            },
            {
                Log.e("apiresult", it.message.toString())

            }
        )
        queue.add(stringRequest)
    }

    private fun updateDrawerHeader(imageUrl: String) {
        val headerView = binding.navView.getHeaderView(0)
        val imgDrawerHeader = headerView.findViewById<ImageView>(R.id.imgDrawerBg)

        Picasso.get()
            .load(imageUrl)
            .placeholder(R.drawable.purple)
            .error(R.drawable.purple)
            .into(imgDrawerHeader)
    }


}