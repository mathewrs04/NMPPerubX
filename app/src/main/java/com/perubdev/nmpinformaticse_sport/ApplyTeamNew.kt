package com.perubdev.nmpinformaticse_sport

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.perubdev.nmpinformaticse_sport.databinding.ActivityApplyTeamNewBinding
import org.json.JSONObject

class ApplyTeamNew : AppCompatActivity() {
    var games:ArrayList<GameBank> = ArrayList()
    private lateinit var binding:ActivityApplyTeamNewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApplyTeamNewBinding.inflate(layoutInflater)
        setContentView(binding.root)



//        binding.spinnerGame.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                val q = Volley.newRequestQueue()
//                val url = "https://ubaya.xyz/native/160422023/get_game.php"
//
//                var stringRequest = StringRequest(
//                    Request.Method.POST,
//                    url,
//                    {
//                        Log.d("apiresult", it)
//
//                        val obj = JSONObject(it)
//                        if(obj.getString("result") == "OK") {
//                            val data = obj.getJSONArray("data")
//                            val sType = object : TypeToken<List<GameBank>>() { }.type
//                            games = Gson().fromJson(data.toString(), sType) as
//                                    ArrayList<GameBank>
//                            updateList()
//                            Log.d("apiresult", games.toString())}
//                    },
//                    {
//                        Log.e("apiresult", it.message.toString())
//                    }
//                )
            }

//            override fun onNothingSelected(p0: AdapterView<*>?) {
//
//            }

        }

    //}
//}