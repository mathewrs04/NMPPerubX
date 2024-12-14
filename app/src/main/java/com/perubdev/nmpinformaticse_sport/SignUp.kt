package com.perubdev.nmpinformaticse_sport

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import com.perubdev.nmpinformaticse_sport.databinding.ActivitySignUpBinding
import org.json.JSONObject

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.isEnabled = false

        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            binding.btnSubmit.isEnabled = isChecked
        }


        binding.btnSubmit.setOnClickListener {
            val firstname = binding.txtFname.text.toString()
            val lastname = binding.txtLname.text.toString()
            val username = binding.txtUsername.text.toString()
            val password = binding.txtPassword.text.toString()
            val rpassword = binding.txtRepeat.text.toString()

            val queue = Volley.newRequestQueue(this)
            val url = "https://ubaya.xyz/native/160422023/new_member.php"

            val stringRequest = object : StringRequest(
                Request.Method.POST,
                url,
                {
                    val obj = JSONObject(it)
                    when (obj.getString("result")) {
                        "OK" -> {
                            Snackbar.make(binding.root, obj.getString("message"), Snackbar.LENGTH_SHORT).show()
                            val intent = Intent(this, SignIn::class.java)
                            startActivity(intent)
                            finish()
                        }
                        "ERROR" -> {
                            Snackbar.make(binding.root, obj.getString("message"), Snackbar.LENGTH_SHORT).show()
                        }
                    }
                },
                {
                    Log.e("apiresult", it.message.toString())
                    Snackbar.make(binding.root, "Error: ${it.message}", Snackbar.LENGTH_SHORT).show()
                }
            )  {
                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    params["firstname"] = firstname
                    params["lastname"] = lastname
                    params["username"] = username
                    params["password"] = password
                    params["rpassword"] = rpassword

                    return params
                }
            }
            queue.add(stringRequest)
        }
    }
}