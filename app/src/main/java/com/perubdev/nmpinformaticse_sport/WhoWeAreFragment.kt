package com.perubdev.nmpinformaticse_sport

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.perubdev.nmpinformaticse_sport.databinding.ActivityWhoWeAreBinding
import com.perubdev.nmpinformaticse_sport.databinding.FragmentWhoWeAreBinding


private const val KEY_WHO = "games PerubX"

class WhoWeAreFragment : Fragment() {
    private  lateinit var binding: FragmentWhoWeAreBinding
    private var likeCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_who_we_are, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WhoWeAreFragment().apply {
                arguments = Bundle().apply {
                    //putParcelableArrayList(KEY_WHO, games)
                }
            }
    }
}