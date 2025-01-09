package com.perubdev.nmpinformaticse_sport

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.perubdev.nmpinformaticse_sport.databinding.FragmentWhoWeAreBinding
import com.squareup.picasso.Picasso
import org.json.JSONObject

class WhoWeAreFragment : Fragment() {
    private lateinit var binding: FragmentWhoWeAreBinding
    private var whoweare: ArrayList<wwa> = ArrayList()
    private var currentWwa: wwa? = null
    private var likeCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchWhoWeAreData()
    }

    private fun fetchWhoWeAreData() {
        val queue = Volley.newRequestQueue(activity)
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
                        currentWwa = whoweare[0]
                        updateUIWithData(currentWwa!!)
                    }
                } else {
                    Toast.makeText(activity, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                }
            },
            {
                Log.e("apiresult", it.message.toString())

            }
        )
        queue.add(stringRequest)
    }

    private fun updateUIWithData(data: wwa) {
        binding.txtDesc2.text = data.description
        likeCount = data.likes
        updateLikeButtonText()
        updateLikeButtonColor()

        Picasso.get().load(data.url)
            .error(R.drawable.venue1)
            .into(binding.imageView2)
    }

    private fun updateLikesInDatabase(id: Int, updatedLikes: Int) {
        val queue = Volley.newRequestQueue(activity)
        val url = "https://ubaya.xyz/native/160422023/update_likes.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            { response ->
                Log.d("apiresult", response)
                val obj = JSONObject(response)
                if (obj.getString("result") == "OK") {
                    Toast.makeText(activity, "Likes updated successfully!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(activity, "Failed to update likes", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                Log.e("apiresult", error.message ?: "Error occurred")
                Toast.makeText(activity, "Failed to connect to server", Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["id"] = id.toString()
                params["likes"] = updatedLikes.toString()
                return params
            }
        }

        queue.add(stringRequest)
    }

    private fun updateLikeButtonText() {
        binding.btnLike.text = "$likeCount"
    }

    private fun updateLikeButtonColor() {
        if (likeCount > 0) {
            binding.btnLike.setBackgroundColor(Color.rgb(128, 0, 128))
            binding.btnLike.setTextColor(Color.WHITE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWhoWeAreBinding.inflate(inflater, container, false)

        binding.btnLike.setOnClickListener {
            likeCount++
            updateLikeButtonText()
            updateLikeButtonColor()
            currentWwa?.let { data ->
                updateLikesInDatabase(data.id, likeCount)
            }
        }

        return binding.root
    }
}
