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
import com.perubdev.nmpinformaticse_sport.databinding.ActivitySignInBinding

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

            // Perform simple login check (replace with actual validation)
            if (username == "user" && password == "user") {
                // Save login state in SharedPreferences
                sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()

                // Navigate to main activity
                navigateToMainActivity()
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }

        // Sign Up button click
//        binding.btnSignUp.setOnClickListener {
//            // Navigate to the sign-up page (create SignUpActivity if needed)
//            val intent = Intent(this, SignUp::class.java)
//            startActivity(intent)
//        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}