package com.muazzeznihalbahadir.my_music_app.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.muazzeznihalbahadir.my_music_app.Model.VideoDetails
import com.muazzeznihalbahadir.my_music_app.R
import com.muazzeznihalbahadir.my_music_app.PlayingFrame.VideoPlayActivity
import com.muazzeznihalbahadir.my_music_app.databinding.ActivityFavouriteListBinding
import com.squareup.picasso.Picasso


class MyCustomAdapter(private val context: Context, val videoDetailsArrayList : ArrayList<VideoDetails>): BaseAdapter(){

    val firestore : FirebaseFirestore = FirebaseFirestore.getInstance()
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    var videoListesiVeriAl= ArrayList<String>()
    var guncelKullanici = ""



    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return this.videoDetailsArrayList.size
    }

    override fun getItem(p0: Int): Any {
        return this.videoDetailsArrayList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        val rowView = inflater.inflate(R.layout.custom_item, p2, false)
        val imageView = rowView.findViewById<ImageView>(R.id.image_view)
        val favourite = rowView.findViewById<ImageView>(R.id.favourite)
        val textView = rowView.findViewById<TextView>(R.id.mytitle)


        val videoDetails = this.videoDetailsArrayList.get(p0)

        var control = 0
        veriAl()

       if ( videoListesiVeriAl.contains(videoDetails.videoId) ) {
           favourite.setImageResource(R.drawable.ic_favourite)//dolu
           control+=1
       }else{
           favourite.setImageResource(R.drawable.ic_ntfavourite)//boş
       }


        imageView.setOnClickListener {

            val intent = Intent(context, VideoPlayActivity()::class.java)
            intent.putExtra("ID", videoDetails.videoId)
            intent.putExtra("Title", videoDetails.description)
            videoDetailsArrayList.let {
                intent.putStringArrayListExtra("liste",videoListesiVeriAl)
                startActivity(context, intent, Bundle())
            }


        }

        favourite.setOnClickListener {
            control += 1
            if ((control % 2) == 0 ){
                favourite.setImageResource(R.drawable.ic_ntfavourite)//boş
                delete(videoDetails.videoId)} //id alma başarılı
            else{
                favourite.setImageResource(R.drawable.ic_favourite)//dolu
                veriYaz(videoDetails.url,videoDetails.videoId,videoDetails.title,videoDetails.description)
            }

        }
        Picasso.get().load(videoDetails.url).into(imageView)

        textView.text = videoDetails.title
        return rowView
    }

    fun veriAl(){
        try {
            guncelKullanici = auth.currentUser?.uid.toString()

            firestore.collection("user").document(guncelKullanici).collection("favourite").addSnapshotListener { snapshot, exception ->
                if(exception !=null){
                    Toast.makeText(context,exception.localizedMessage,Toast.LENGTH_LONG).show()
                }else{
                    if(snapshot!=null){
                        if(!snapshot.isEmpty){
                            val documents =snapshot.documents
                            videoListesiVeriAl.clear()
                            for (document in documents){
                                val videoId = document.get("videoId") as String
                                videoListesiVeriAl.add(videoId)
                            }
                        }
                    }
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun veriYaz (url:String,videoId:String,title:String,description:String){

        val postHashMap = hashMapOf<String,Any>()
        val tarih = Timestamp.now()
        postHashMap.put("videoId",videoId)
        postHashMap.put("description",description)
        postHashMap.put("storageUrl",url)
        postHashMap.put("title",title)
        postHashMap.put("tarih",tarih)


        firestore.collection("user").document(guncelKullanici).collection("favourite").document(videoId).set(postHashMap).addOnCompleteListener { task ->
            if(task.isSuccessful){
                Toast.makeText(context, "favorilendi", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(context,exception.localizedMessage,Toast.LENGTH_LONG).show()
        }
    }

    fun delete(id: String) {
        guncelKullanici = auth.currentUser?.uid.toString()
        try {
            firestore.collection("user").document(guncelKullanici).collection("favourite").document(id).delete().addOnSuccessListener {
                Toast.makeText(context,"favoriden kaldırıldı", Toast.LENGTH_SHORT).show() // silme başarılı
        }.addOnFailureListener{
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}