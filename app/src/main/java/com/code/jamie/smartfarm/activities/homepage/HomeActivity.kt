package com.code.jamie.smartfarm.activities.homepage

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.code.jamie.smartfarm.R
import com.code.jamie.smartfarm.activities.homepage.fragments.*
import com.code.jamie.smartfarm.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportFragmentManager
            .beginTransaction()
            .add(
                R.id.fragment_container_home,
                HomeFragment()
            )
            .commit()
        supportActionBar?.title = "Home"

        binding.bottomNav.selectedItemId = R.id.home
        val toggle = ActionBarDrawerToggle(
            this,
            binding.root,
            binding.toolbar,
            R.string.drawer_open,
            R.string.drawer_closed
        )
        toggle.syncState()
        binding.marketFab.setOnClickListener {
            inflateContainer(MarketFragment())
        }
        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.orders -> {
                    Toast.makeText(this, "Orders clicked", Toast.LENGTH_SHORT).show()
                    supportActionBar?.setTitle("Orders")
                }
                R.id.news -> {
                    Toast.makeText(this, "News clicked", Toast.LENGTH_SHORT).show()
                    supportActionBar?.setTitle("News")
                }
                R.id.sell -> {
                    Toast.makeText(this, "Sell clicked", Toast.LENGTH_SHORT).show()
                    supportActionBar?.setTitle("Sell")
                }
                R.id.settings -> {
                    Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show()
                    supportActionBar?.setTitle("Settings")
                }
            }
            binding.root.closeDrawer(GravityCompat.START)
            true
        }
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.faq -> {
                    inflateContainer(FAQsFragment())
                    supportActionBar?.setTitle("FAQs")
                }
                R.id.home -> {
                    inflateContainer(HomeFragment())
                    supportActionBar?.setTitle("Home")
                }
                R.id.notification -> {
                    inflateContainer(NotificationFragment())
                    supportActionBar?.setTitle("Notifications")
                }
                R.id.account -> {
                    inflateContainer(AccountFragment())
                    supportActionBar?.setTitle("Account")
                }
            }
            true
        }

    }

    private fun inflateContainer(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragment_container_home,
                fragment
            )
            .commit()
    }
}