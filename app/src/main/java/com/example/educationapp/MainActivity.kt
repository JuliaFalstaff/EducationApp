package com.example.educationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.educationapp.databinding.ActivityMainBinding
import com.example.educationapp.ui.MainFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigation()
        initToolBar()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commit()
        }
    }

    private fun initToolBar() {
        val toolbar: Toolbar = binding.layoutAppbarMain.toolbar
        setSupportActionBar(toolbar)
    }

    private fun initBottomNavigation() {
        binding.bottomAppNavigation.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.bottom_menu_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment.newInstance())
                        .commit()
                    true
                }

                R.id.bottom_menu_classes -> {
                    Toast.makeText(applicationContext, "Open Classes fragment", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.bottom_menu_notes -> {
                    Toast.makeText(applicationContext, "Open Notes fragment", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.bottom_menu_favs -> {
                    Toast.makeText(applicationContext, "Open Favourite fragment", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                Toast.makeText(applicationContext, "search toolbar", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_profile -> {
                Toast.makeText(applicationContext, "profile toolbar", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_settings -> {
                Toast.makeText(applicationContext, "settings toolbar", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}