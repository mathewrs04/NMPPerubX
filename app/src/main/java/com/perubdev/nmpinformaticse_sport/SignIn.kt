package com.perubdev.nmpinformaticse_sport

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.perubdev.nmpinformaticse_sport.databinding.ActivitySignInBinding
import org.json.JSONObject

class SignIn : AppCompatActivity() {
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

        // Sign Up button click
//        binding.btnSignUp.setOnClickListener {
//            // Navigate to the sign-up page (create SignUpActivity if needed)
//            val intent = Intent(this, SignUp::class.java)
//            startActivity(intent)
//        }
    }

    private fun login(username: String, password: String) {
        val q = Volley.newRequestQueue(this)
        val url = "https://ubaya.xyz/native/160422023/login_member.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            {
                try {
                    val obj = JSONObject(it)
                    if (obj.getString("result") == "OK") {
                        // Parse user data
                        val user = Gson().fromJson(obj.getJSONObject("data").toString(), Member::class.java)

                        // Save login state in SharedPreferences
                        val sharedPreferences = getSharedPreferences("UserSession", Context.MODE_PRIVATE)
                        sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
                        sharedPreferences.edit().putString("username", user.username).apply()

                        // Navigate to main activity
                        navigateToMainActivity()
                    } else {
                        Toast.makeText(this, obj.getString("message"), Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                Toast.makeText(this, "Volley error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
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