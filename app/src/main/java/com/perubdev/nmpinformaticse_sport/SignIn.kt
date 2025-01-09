package com.perubdev.nmpinformaticse_sport

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.perubdev.nmpinformaticse_sport.databinding.ActivitySignInBinding
import com.squareup.picasso.Picasso
import org.json.JSONObject

class SignIn : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private var whoweare: ArrayList<wwa> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchWhoWeAreData()

        val sharedPreferences = getSharedPreferences("UserSession", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        if (isLoggedIn) {
            navigateToMainActivity()
            finish()
        }

        binding.btnSignIn.setOnClickListener {
            val username = binding.txtInputUsername.text.toString()
            val password = binding.txtInputPassword.text.toString()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                login(username, password)
            } else {
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show()
            }
        }


        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }

    private fun login(username: String, password: String) {
        val q = Volley.newRequestQueue(this)
        val url = "https://ubaya.xyz/native/160422023/login_member.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            {
                Log.d("cekparams", it)

                val obj = JSONObject(it)
                if (obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
                    val sType = object : TypeToken<Member>() {}.type
                    val member = Gson().fromJson<Member>(data.getJSONObject(0).toString(), sType)


                    val sharedPreferences = getSharedPreferences("UserSession", Context.MODE_PRIVATE)
                    sharedPreferences.edit().apply {
                        putBoolean("isLoggedIn", true)
                        putString("username", member.username)
                        putInt("idmember", member.idmember)
                        apply()
                    }

                    navigateToMainActivity()
                } else {
                    Toast.makeText(this, obj.getString("message"), Toast.LENGTH_SHORT).show()
                }
            },
            {
                Log.e("cekparams", it.message.toString())
            }
        ) {
            override fun getParams(): MutableMap<String, String> {
                val params = mutableMapOf<String, String>()
                params["username"] = username
                params["password"] = password
                return params
            }
        }

        q.add(stringRequest)
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
                        loadLogoImage(imageUrl)
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

    private fun loadLogoImage(imageUrl: String) {
        Picasso.get()
            .load(imageUrl)
            .placeholder(R.drawable.purple)
            .error(R.drawable.purple)
            .into(binding.imgLogo)
    }


    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}