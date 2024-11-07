package com.perubdev.nmpinformaticse_sport

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.ListFragment
import androidx.recyclerview.widget.LinearLayoutManager

import com.perubdev.nmpinformaticse_sport.databinding.FragmentWhatWePlayBinding

private const val KEY_GAMES = "games PerubX"
class WhatWePlayFragment : Fragment() {
    private var games: ArrayList<GameBank> = ArrayList()
    private lateinit var binding: FragmentWhatWePlayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        arguments?.let {
            games    = it.getParcelableArrayList<GameBank>(KEY_GAMES) as ArrayList<GameBank>
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Set up binding and initialize RecyclerView here
        binding = FragmentWhatWePlayBinding.inflate(inflater, container, false)

        binding.recGame.layoutManager = LinearLayoutManager(requireContext())
        binding.recGame.setHasFixedSize(true)
        binding.recGame.adapter = WhatWePlayAdapter()

        return binding.root
    }




    companion object {
        @JvmStatic
        fun newInstance(games: ArrayList<GameBank>) =
            WhatWePlayFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(KEY_GAMES, games)
                }
            }
    }
}