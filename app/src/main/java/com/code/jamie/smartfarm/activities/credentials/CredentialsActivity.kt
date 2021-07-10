package com.code.jamie.smartfarm.activities.credentials

import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.code.jamie.smartfarm.R
import com.code.jamie.smartfarm.activities.credentials.fragments.LoginFragment
import com.code.jamie.smartfarm.activities.credentials.fragments.RegisterFragment
import com.code.jamie.smartfarm.databinding.ActivityCredentialsBinding

class CredentialsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCredentialsBinding
    private lateinit var explosionAnimation: Animation
    override fun onStart() {
        super.onStart()
        explosionAnimation = AnimationUtils.loadAnimation(this, R.anim.fab_explotion)
        explosionAnimation.duration = 400
        explosionAnimation.interpolator = AccelerateDecelerateInterpolator()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCredentialsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, RegisterFragment())
            .commit()
        binding.registerFab.visibility = View.INVISIBLE

        binding.loginFab.setOnClickListener {
            binding.loginView.animation = explosionAnimation.also { explosionAnimation.start() }
            binding.loginFab.visibility = View.INVISIBLE
            binding.registerFab.visibility = View.VISIBLE
            binding.loginView.visibility = View.INVISIBLE
            inflateFragContainer(LoginFragment())

        }
        binding.registerFab.setOnClickListener {
            binding.registerView.animation = explosionAnimation.also { explosionAnimation.start() }
            binding.registerFab.visibility = View.INVISIBLE
            binding.loginFab.visibility = View.VISIBLE
            binding.registerView.visibility = View.INVISIBLE
            inflateFragContainer(RegisterFragment())
        }

    }

    private fun inflateFragContainer(frag: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,frag)
            .commit()
    }
}