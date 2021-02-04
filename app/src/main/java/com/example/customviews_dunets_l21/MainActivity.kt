package com.example.customviews_dunets_l21

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.customviews_dunets_l21.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupListeners()
    }

    private fun setupListeners() {
        binding.controllerMain.setOnClickListener {
            binding.fanMain.setSpeed(binding.controllerMain.power * 2)
        }
        binding.fanMain.setOnClickListener {
            binding.pcTest.apply {
                title = "Moon"
                category = "Astronomy"
                rating = 5f
                drawable = ContextCompat.getDrawable(context, R.drawable.mock_moon)
            }
        }
    }

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}