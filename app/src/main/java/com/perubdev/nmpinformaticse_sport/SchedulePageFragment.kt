package com.perubdev.nmpinformaticse_sport

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.perubdev.nmpinformaticse_sport.databinding.FragmentSchedulePageBinding
import org.json.JSONObject


private const val KEY_SCHEDULE = "games PerubX"
class SchedulePageFragment : Fragment() {
    private var schedule: ArrayList<Schedule> = ArrayList()
    private lateinit var binding: FragmentSchedulePageBinding

    fun updateList(){
        val lm = LinearLayoutManager(activity)
        with(binding.recSch) {
            layoutManager = lm
            setHasFixedSize(true)
            adapter = ScheduleAdapter(schedule)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sc = Volley.newRequestQueue(activity)
        val url = "https://ubaya.xyz/native/160422023/get_event.php"

        var stringRequest = StringRequest(
            Request.Method.POST,
            url,
            {
                Log.d("apiresult", it)
                val obj = JSONObject(it)
                if(obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    val sType = object : TypeToken<List<Schedule>>() {}.type
                    schedule = Gson().fromJson(data.toString(), sType) as
                            ArrayList<Schedule>
                    updateList()
                    Log.d("apiresult",schedule.toString())}
            }, {
                Log.e("apiresult", it.message.toString())
            }
        )
        sc.add(stringRequest)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSchedulePageBinding.inflate(inflater, container, false)
        return binding.root
    }
}