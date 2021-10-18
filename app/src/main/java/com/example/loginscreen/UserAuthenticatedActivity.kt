package com.example.loginscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loginscreen.databinding.ActivityUserAuthenticatedBinding

class UserAuthenticatedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserAuthenticatedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserAuthenticatedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogout.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}