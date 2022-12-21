package com.muazzeznihalbahadir.my_music_app.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.muazzeznihalbahadir.my_music_app.Adapter.LoginAdapter
import com.muazzeznihalbahadir.my_music_app.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

       // setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //ViewPager için adapter nesnesi oluşturuyoruz ve kullanacağımız fragment, title'ları ekliyoruz.
        val adapter = LoginAdapter(supportFragmentManager)
        adapter.addFragment(LoginTabFragment(),"giris")
        adapter.addFragment(SignupTabFragment(),"kayıt ol")

        //Adapter'ımızdaki verileri viewPager adapter'a veriyoruz
        binding.lgViewPager.adapter = adapter

        //Tablar arasında yani viewPager'lar arasında geçisi sağlıyoruz
        binding.tabLayout.setupWithViewPager(binding.lgViewPager)




    }
}