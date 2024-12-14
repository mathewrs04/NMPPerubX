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
import org.json.JSONObject

class SignIn : AppCompatActivity() {
    var member:ArrayList<Member> = ArrayList()
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Check if user is already logged in
        val sharedPreferences = getSharedPreferences("UserSession", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        if (isLoggedIn) {
            navigateToMainActivity()
            finish() // Close this activity
        }

        // Sign In button click
        binding.btnSignIn.setOnClickListener {
            val username = binding.txtInputUsername.text.toString()
            val password = binding.txtInputPassword.text.toString()

//             Perform simple login check (replace with actual validation)
            if (username.isNotEmpty() && password.isNotEmpty()) {
                login(username, password)
            } else {
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show()
            }
        }


        binding.btnSignUp.setOnClickListener {
            // Navigate to the sign-up page (create SignUpActivity if needed)
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


                    // Save login status to SharedPreferences
                    val sharedPreferences = getSharedPreferences("UserSession", Context.MODE_PRIVATE)
                    sharedPreferences.edit().apply {
                        putBoolean("isLoggedIn", true)
                        putString("username", member.username)
                        putInt("idmember", member.idmember)
                        apply()
                    }

                    // Navigate to MainActivity
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




    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}