package com.muazzeznihalbahadir.my_music_app.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.muazzeznihalbahadir.my_music_app.Adapter.PostsAdapter
import com.muazzeznihalbahadir.my_music_app.databinding.ActivityAdvicePageBinding

class AdvicePageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdvicePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdvicePageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val weather = intent.getStringExtra("weather").toString() //hava durumunu alır
        val posts : ArrayList<String> = ArrayList()

        for (i in 1..10){
            posts.add("Post # $i")
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL,false)
        binding.recyclerView.adapter = PostsAdapter(posts)

        binding.ikincik.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL,false)
        binding.ikincik.adapter = PostsAdapter(posts)
    }
}