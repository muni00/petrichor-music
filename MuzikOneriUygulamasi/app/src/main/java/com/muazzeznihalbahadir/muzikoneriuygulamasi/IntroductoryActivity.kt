package com.muazzeznihalbahadir.muzikoneriuygulamasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.muazzeznihalbahadir.muzikoneriuygulamasi.databinding.ActivityIntroductoryBinding


class IntroductoryActivity : AppCompatActivity() {

    private lateinit var binding : ActivityIntroductoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_introductory)
        binding = ActivityIntroductoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val translationValue = -1600
        val translationValueIki = -1800
        binding.lottie.animate().translationY(translationValue.toFloat()).setDuration(1000).setStartDelay(4000)
        binding.appName.animate().translationY(translationValueIki.toFloat()).setDuration(1000).setStartDelay(4000)
        binding.logo.animate().translationY(translationValueIki.toFloat()).setDuration(1000).setStartDelay(4000)
        binding.bgImage.animate().translationY(translationValueIki.toFloat()).setDuration(1000).setStartDelay(4000)


    }
}