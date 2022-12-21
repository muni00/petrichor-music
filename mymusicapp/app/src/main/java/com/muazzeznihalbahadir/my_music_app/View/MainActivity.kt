package com.muazzeznihalbahadir.my_music_app.View


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.muazzeznihalbahadir.my_music_app.Model.VideoDetails
import com.muazzeznihalbahadir.my_music_app.PlayingFrame.VideoPlayActivity
import com.muazzeznihalbahadir.my_music_app.R
import com.muazzeznihalbahadir.my_music_app.Search.FavouriteListActivity
import com.muazzeznihalbahadir.my_music_app.Search.VideoListActivity
import com.muazzeznihalbahadir.my_music_app.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var bos = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {

        val intentVideoSearch = Intent(this, VideoListActivity::class.java)
        val intentMain = Intent(this, MainActivity::class.java)
        val intentVideoPlaying = Intent(this, VideoPlayActivity::class.java)
        val intentOption = Intent(this, OptionsActivity::class.java)
        val intentFavouriteListActivity = Intent(this, FavouriteListActivity::class.java)
        val intentProfileSettingsActivity = Intent(this, ProfileSettingsActivity::class.java)




        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.bottomNavigator.setSelectedItemId(R.id.nav_home);

        binding.bottomNavigator.setOnNavigationItemSelectedListener{


            when(it.itemId){
                R.id.nav_home -> {startActivity(intentMain)
                finish()}//Toast.makeText(this, "home : " , Toast.LENGTH_SHORT).show()

                R.id.nav_search -> {startActivity(intentVideoSearch)
                    finish()}//Toast.makeText(this, "search : " , Toast.LENGTH_SHORT).show()
                R.id.nav_fire -> {startActivity(intentOption)
                    finish()}
                R.id.nav_library ->Toast.makeText(this, "setting : " , Toast.LENGTH_SHORT).show()
                R.id.nav_setting -> {startActivity(intentProfileSettingsActivity)}
            }
            true
        }

        binding.bir.setOnClickListener {
            startActivity(intentFavouriteListActivity)
        }
        binding.iki.setOnClickListener {
            intentVideoPlaying.putExtra("ID", "9aRe5mzHPXA")
            intentVideoPlaying.putExtra("Tip","playlist")
            intentVideoPlaying.putStringArrayListExtra("liste",bos)
            intentVideoPlaying.putExtra("Title","Birinci Yeniler")
            startActivity(intentVideoPlaying)
        }
        binding.uc.setOnClickListener {
            intentVideoPlaying.putExtra("ID", "1iHI17s7qns")
            intentVideoPlaying.putExtra("Tip","playlist")
            intentVideoPlaying.putExtra("Title","Alternatif Türkçe")
            intentVideoPlaying.putStringArrayListExtra("liste",bos)
            startActivity(intentVideoPlaying)
        }
        binding.dort.setOnClickListener {
            intentVideoPlaying.putExtra("ID", "8RJJZNaWlyM")
            intentVideoPlaying.putExtra("Tip","playlist")
            intentVideoPlaying.putExtra("Title","Sakin")
            intentVideoPlaying.putStringArrayListExtra("liste",bos)
            startActivity(intentVideoPlaying)
        }
        binding.bes.setOnClickListener {
            intentVideoPlaying.putExtra("ID", "1cS_i-zDdxg")
            intentVideoPlaying.putExtra("Tip","playlist")
            intentVideoPlaying.putExtra("Title","Demo Demo")
            intentVideoPlaying.putStringArrayListExtra("liste",bos)
            startActivity(intentVideoPlaying)
        }
        binding.alti.setOnClickListener {
            intentVideoPlaying.putExtra("ID", "1GpYfKQth-w")
            intentVideoPlaying.putExtra("Tip","playlist")
            intentVideoPlaying.putExtra("Title","Ortadoğu Müziği")
            intentVideoPlaying.putStringArrayListExtra("liste",bos)
            startActivity(intentVideoPlaying)
        }
        binding.yedi.setOnClickListener {
            intentVideoPlaying.putExtra("ID", "u7oNzEdRJTE")
            intentVideoPlaying.putExtra("Tip","playlist")
            intentVideoPlaying.putExtra("Title","playlist")
            intentVideoPlaying.putStringArrayListExtra("liste",bos)
            startActivity(intentVideoPlaying)
        }
        binding.sekiz.setOnClickListener {
            intentVideoPlaying.putExtra("ID", "RDCLAK5uy_nkN2Fde5lIJN38ta7Tyvr8Uona03aHnRo")
            intentVideoPlaying.putExtra("Tip","playlist")
            intentVideoPlaying.putExtra("Title","Anadolu Rock")
            intentVideoPlaying.putStringArrayListExtra("liste",bos)
            startActivity(intentVideoPlaying)
        }

    }


}