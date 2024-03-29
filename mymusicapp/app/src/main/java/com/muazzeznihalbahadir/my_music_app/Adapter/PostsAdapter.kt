package com.muazzeznihalbahadir.my_music_app.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.muazzeznihalbahadir.my_music_app.R

class PostsAdapter(val posts: ArrayList<String>) : RecyclerView.Adapter<PostsAdapter.ViewHolder>(){

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        val firstName: TextView = itemView.findViewById(R.id.firstName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view : View = LayoutInflater.from(parent.context).inflate(R.layout.row_post,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.firstName.text = posts[position]
    }
    override fun getItemCount() = posts.size
}