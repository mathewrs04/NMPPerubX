package com.perubdev.nmpinformaticse_sport

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.perubdev.nmpinformaticse_sport.databinding.FragmentSchedulePageBinding
import com.perubdev.nmpinformaticse_sport.databinding.FragmentWhatWePlayBinding




private const val KEY_SCHEDULE = "games PerubX"
class SchedulePageFragment : Fragment() {
    private var schedule: ArrayList<ScheduleBank> = ArrayList()
    private lateinit var binding: FragmentSchedulePageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        arguments?.let {
            schedule   = it.getParcelableArrayList<GameBank>(KEY_SCHEDULE) as ArrayList<ScheduleBank>
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Set up binding and initialize RecyclerView here
        binding = FragmentSchedulePageBinding.inflate(inflater, container, false)

        binding.recSch.layoutManager = LinearLayoutManager(requireContext())
        binding.recSch.setHasFixedSize(true)
        binding.recSch.adapter = ScheduleAdapter()

        return binding.root
    }




    companion object {
        @JvmStatic
        fun newInstance(games: ArrayList<GameBank>) =
            WhatWePlayFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(KEY_SCHEDULE, games)
                }
            }
    }
}