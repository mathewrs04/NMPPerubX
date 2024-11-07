package com.perubdev.nmpinformaticse_sport

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

private const val KEY_GAMES = "games PerubX"
class WhatWePlayFragment : Fragment() {
    private var games: ArrayList<GameBank> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            games    = it.getParcelableArrayList<GameBank>(KEY_GAMES) as ArrayList<GameBank>
//            listAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, events)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_what_we_play, container, false)
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