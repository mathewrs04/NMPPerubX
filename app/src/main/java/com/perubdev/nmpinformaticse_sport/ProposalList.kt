package com.perubdev.nmpinformaticse_sport

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.perubdev.nmpinformaticse_sport.databinding.ActivityProposalListBinding
import org.json.JSONObject


class ProposalList : AppCompatActivity() {
    private var proposals : ArrayList<Proposal> = ArrayList()
    private lateinit var binding: ActivityProposalListBinding

    fun updateList() {
        val lm = LinearLayoutManager(this)
        with(binding.recProposal) {
            layoutManager = lm
            setHasFixedSize(true)
            adapter = ProposalAdapter(proposals)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProposalListBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onResume() {
        super.onResume()

        val q = Volley.newRequestQueue(this)
        val url = "https://ubaya.xyz/native/160422023/get_proposal.php"

        var stringRequest = StringRequest(
            Request.Method.POST,
            url,
            {
                Log.d("apiresult", it)

                val obj = JSONObject(it)
                if(obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    val sType = object : TypeToken<List<Proposal>>() { }.type
                    proposals = Gson().fromJson(data.toString(), sType) as
                            ArrayList<Proposal>
                    updateList()
                    Log.d("apiresult", proposals.toString())}
            },
            {
                Log.e("apiresult", it.message.toString())
            }
        )

        q.add(stringRequest)

        binding.fabAdd.setOnClickListener{
            val intent = Intent(this, ApplyTeamNew::class.java)
            startActivity(intent)
        }
    }
}