package com.muazzeznihalbahadir.muzikoneriuygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.muazzeznihalbahadir.muzikoneriuygulamasi.adapter.LoginAdapter
import com.muazzeznihalbahadir.muzikoneriuygulamasi.databinding.ActivityIntroductoryBinding



class IntroductoryActivity : AppCompatActivity() {

    private lateinit var binding : ActivityIntroductoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_introductory)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        binding = ActivityIntroductoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val translationValue = -1600
        val translationValueIki = -1800
        binding.lottie.animate().translationY(translationValue.toFloat()).setDuration(1000).setStartDelay(4000)
        binding.appName.animate().translationY(translationValueIki.toFloat()).setDuration(1000).setStartDelay(4000)
        binding.logo.animate().translationY(translationValueIki.toFloat()).setDuration(1000).setStartDelay(4000)
        binding.bgImage.animate().translationY(translationValueIki.toFloat()).setDuration(1000).setStartDelay(4000)


        val adapter = LoginAdapter(supportFragmentManager)
        adapter.addFragment(LoginTabFragment(),"giris")
        //Adapter'ımızdaki verileri viewPager adapter'a veriyoruz
        binding.viewPager.adapter = adapter
        //Tablar arasında yani viewPager'lar arasında geçisi sağlıyoruz
        binding.lgTabLayout.setupWithViewPager(binding.viewPager)

       /* val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)*/

    }

}