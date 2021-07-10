package com.code.jamie.smartfarm.activities.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.code.jamie.smartfarm.R
import com.code.jamie.smartfarm.activities.credentials.CredentialsActivity
import com.code.jamie.smartfarm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var topAnimation: Animation
    private lateinit var bottomAnimation: Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(binding.root)
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_anim)
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_anim)
        binding.imageView.animation = topAnimation
        binding.logo.animation = bottomAnimation

        Handler().postDelayed({
            startActivity(Intent(this, CredentialsActivity::class.java))
            finish()
        }, 3000)

    }
}