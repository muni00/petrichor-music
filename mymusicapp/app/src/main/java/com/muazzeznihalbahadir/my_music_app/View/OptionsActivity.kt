package com.muazzeznihalbahadir.my_music_app.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.muazzeznihalbahadir.my_music_app.databinding.ActivityOptionsBinding

class OptionsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOptionsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val intentHD = Intent(this, MapsActivity::class.java)
        val intentFD = Intent(this, CameraActivity::class.java)

        binding.ivwFD.setOnClickListener {
            startActivity(intentFD)
        }

        binding.ivwHD.setOnClickListener {
            startActivity(intentHD)
        }
    }
}