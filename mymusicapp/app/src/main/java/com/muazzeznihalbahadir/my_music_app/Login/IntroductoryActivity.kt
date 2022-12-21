package com.muazzeznihalbahadir.my_music_app.Login
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.muazzeznihalbahadir.my_music_app.Adapter.LoginAdapter
import com.muazzeznihalbahadir.my_music_app.databinding.ActivityIntroductoryBinding


class IntroductoryActivity : AppCompatActivity() {

    private lateinit var binding : ActivityIntroductoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //tabbarın gösterilmemesindeki ayarı gerçekleştirebilmemize yarım eder
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        binding = ActivityIntroductoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val translationValue = -1600
        val translationValueIki = -2000
        binding.lottie.animate().translationY(translationValue.toFloat()).setDuration(1000).setStartDelay(4000)
        binding.appName.animate().translationY(translationValueIki.toFloat()).setDuration(1000).setStartDelay(4000)
        binding.bgImage.animate().translationY(translationValueIki.toFloat()).setDuration(1000).setStartDelay(4000)


        val adapter = LoginAdapter(supportFragmentManager)
        adapter.addFragment(TransitionTabFragment(),"welcome to pethricor")
        //Adapter'ımızdaki verileri viewPager adapter'a veriyoruz
        binding.viewPager.adapter = adapter
        //Tablar arasında yani viewPager'lar arasında geçisi sağlıyoruz
        binding.lgTabLayout.setupWithViewPager(binding.viewPager)



    }

}