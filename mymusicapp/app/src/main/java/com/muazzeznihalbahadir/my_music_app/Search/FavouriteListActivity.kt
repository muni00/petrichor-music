package com.muazzeznihalbahadir.my_music_app.Search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.muazzeznihalbahadir.my_music_app.Adapter.MyCustomAdapter
import com.muazzeznihalbahadir.my_music_app.Model.VideoDetails
import com.muazzeznihalbahadir.my_music_app.PlayingFrame.VideoPlayActivity
import com.muazzeznihalbahadir.my_music_app.databinding.ActivityFavouriteListBinding

class FavouriteListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavouriteListBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    private var videoDetailsArrayList = ArrayList<VideoDetails>()
    var videoListesiVeriAl= ArrayList<String>()

    val name = ""


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        binding = ActivityFavouriteListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        veriAl()

        binding.videoFavPlay.setOnClickListener {
            val intent = Intent(this, VideoPlayActivity()::class.java)
            intent.putExtra("Tip", "listele") //deneme için aklında bulunsun
            intent.putExtra("Title","Şu Anda Favorilerini Dinliyorsun")
            videoDetailsArrayList.let {
                intent.putStringArrayListExtra("liste",videoListesiVeriAl)
                ContextCompat.startActivity(this, intent, Bundle())
            }
        }
    }

    fun veriAl(){
        try {
            val guncelKullanici = auth.currentUser?.uid.toString()

            firestore.collection("user").document(guncelKullanici).collection("favourite").addSnapshotListener { snapshot, exception ->
                if(exception !=null){
                    Toast.makeText(this,exception.localizedMessage, Toast.LENGTH_LONG).show()
                }else{
                    if(snapshot!=null){
                        if(!snapshot.isEmpty){
                            val documents =snapshot.documents
                            videoDetailsArrayList.clear()
                            for (document in documents){

                                val videoId = document.get("videoId") as String
                                val title = document.get("title") as String
                                val description = document.get("description") as String
                                val url = document.get("storageUrl") as String

                                val vd = VideoDetails(videoId,title,description,url)
                                videoDetailsArrayList.add(vd)
                                videoListesiVeriAl.add(videoId)
                            }
                        }
                    }
                    val list_view = binding.LstFavourite
                    val adapter = MyCustomAdapter(this,videoDetailsArrayList)
                    list_view.adapter = adapter
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}