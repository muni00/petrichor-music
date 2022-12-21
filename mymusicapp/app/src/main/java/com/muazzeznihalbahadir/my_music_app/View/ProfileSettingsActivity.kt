package com.muazzeznihalbahadir.my_music_app.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.muazzeznihalbahadir.my_music_app.Adapter.MyCustomAdapter
import com.muazzeznihalbahadir.my_music_app.Login.LoginActivity
import com.muazzeznihalbahadir.my_music_app.Model.VideoDetails
import com.muazzeznihalbahadir.my_music_app.PlayingFrame.VideoPlayActivity
import com.muazzeznihalbahadir.my_music_app.R
import com.muazzeznihalbahadir.my_music_app.Search.FavouriteListActivity
import com.muazzeznihalbahadir.my_music_app.Search.VideoListActivity
import com.muazzeznihalbahadir.my_music_app.databinding.ActivityProfileSettingsBinding

class ProfileSettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileSettingsBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth


    var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {


        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        super.onCreate(savedInstanceState)

        binding = ActivityProfileSettingsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.bottomNavigator.setSelectedItemId(R.id.nav_setting);
        binding.bottomNavigator.setOnNavigationItemSelectedListener{

            val intentVideoSearch = Intent(this, VideoListActivity::class.java)
            val intentMain = Intent(this, MainActivity::class.java)
            val intentOption = Intent(this, OptionsActivity::class.java)
            val intentProfileSettingsActivity = Intent(this, ProfileSettingsActivity::class.java)

            when(it.itemId){
                R.id.nav_home -> {startActivity(intentMain)
                    finish()}//Toast.makeText(this, "home : " , Toast.LENGTH_SHORT).show()

                R.id.nav_search -> {startActivity(intentVideoSearch)
                    finish()}//Toast.makeText(this, "search : " , Toast.LENGTH_SHORT).show()
                R.id.nav_fire -> {startActivity(intentOption)
                    finish()}
                R.id.nav_library -> Toast.makeText(this, "library : " , Toast.LENGTH_SHORT).show()
                R.id.nav_setting -> {startActivity(intentProfileSettingsActivity)}
            }
            true
        }

        val guncelKullanicie = auth.currentUser?.email.toString()


        binding.txtEmail.isEnabled = false
        binding.txtUserName.isEnabled = false



        binding.btnDuzenleE.setOnClickListener {
            binding.txtUserName.isEnabled = true

            binding.txtUserName.hint = name
            binding.txtEmail.hint = guncelKullanicie
        }
        binding.btnCikis.setOnClickListener {
            auth.signOut()
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.btnKaydet.setOnClickListener {
            binding.txtUserName.isEnabled = false

            name = binding.txtUserName.text.toString()

            veriEkle(name)
        }

    }

    private fun veriEkle(name:String) {

            val guncelKullanici = auth.currentUser?.uid.toString()
        val guncelKullanicie = auth.currentUser?.email.toString()
            val user = hashMapOf(
                "userName" to name,
                "userEmail" to guncelKullanicie
            )

            firestore.collection("user").document(guncelKullanici)
                .set(user)
                .addOnSuccessListener { Toast.makeText(this,"Güncelleme gerçekleştirildi",Toast.LENGTH_LONG).show() }
                .addOnFailureListener { Toast.makeText(this,"hata",Toast.LENGTH_LONG).show() }

    }

}