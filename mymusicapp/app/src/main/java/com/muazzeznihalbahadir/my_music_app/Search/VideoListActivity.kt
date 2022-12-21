package com.muazzeznihalbahadir.my_music_app.Search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.firestore.FirebaseFirestore
import com.muazzeznihalbahadir.my_music_app.Adapter.MyCustomAdapter
import com.muazzeznihalbahadir.my_music_app.View.MainActivity
import com.muazzeznihalbahadir.my_music_app.Model.VideoDetails
import com.muazzeznihalbahadir.my_music_app.PlayingFrame.VideoPlayActivity
import com.muazzeznihalbahadir.my_music_app.R
import com.muazzeznihalbahadir.my_music_app.View.OptionsActivity
import com.muazzeznihalbahadir.my_music_app.databinding.ActivityVideoListBinding
import org.json.JSONException
import org.json.JSONObject

class VideoListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoListBinding
    private lateinit var firestore: FirebaseFirestore

    private var videoDetailsArrayList = ArrayList<VideoDetails>()

    val name = ""

    var url = "https://youtube.googleapis.com/youtube/v3/search?part=snippet&maxResults=10&q="+name+"&key=AIzaSyDouHkw5ZonMWnkMLwPRWuxYgd8x5GDbSk"

    override fun onCreate(savedInstanceState: Bundle?) {

        firestore = FirebaseFirestore.getInstance()

        super.onCreate(savedInstanceState)
        binding = ActivityVideoListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupSearchView()

        binding.bottomNavigator.setSelectedItemId(R.id.nav_search);
        binding.bottomNavigator.setOnNavigationItemSelectedListener{

            val intentVideoSearch = Intent(this, VideoListActivity::class.java)
            val intentMain = Intent(this, MainActivity::class.java)
            val intentVideoPlaying = Intent(this, VideoPlayActivity::class.java)
            val intentOption = Intent(this, OptionsActivity::class.java)
            val intentFavouriteListActivity = Intent(this, FavouriteListActivity::class.java)

            when(it.itemId){
                R.id.nav_home -> {startActivity(intentMain)
                    finish()}//Toast.makeText(this, "home : " , Toast.LENGTH_SHORT).show()

                R.id.nav_search -> {startActivity(intentVideoSearch)
                    finish()}//Toast.makeText(this, "search : " , Toast.LENGTH_SHORT).show()
                R.id.nav_fire -> {startActivity(intentOption)
                    finish()}
                R.id.nav_library -> Toast.makeText(this, "library : " , Toast.LENGTH_SHORT).show()
                R.id.nav_setting -> Toast.makeText(this, "setting : " , Toast.LENGTH_SHORT).show()
            }
            true
        }
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                Toast.makeText(this@VideoListActivity, p0, Toast.LENGTH_SHORT).show()
                url = "https://youtube.googleapis.com/youtube/v3/search?part=snippet&maxResults=10&q="+p0+"&key=AIzaSyDouHkw5ZonMWnkMLwPRWuxYgd8x5GDbSk"
                displayVideos(url)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {

                return false
            }
        })
    }


    private fun displayVideos(url:String) {

        videoDetailsArrayList.clear()
        val queue = Volley.newRequestQueue(applicationContext)
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {response ->
                try {
                    val jsonObject = JSONObject(response)
                    val jsonArray = jsonObject.optJSONArray("items")

                    for (i in 0 until jsonArray.length()) {

                        val jsonObject1 = jsonArray.getJSONObject(i)
                        val jsonVideoId = jsonObject1.getJSONObject("id")
                        val jsonObjectSnippet = jsonObject1.getJSONObject("snippet")
                        val jsonObjectDefault = jsonObjectSnippet.getJSONObject("thumbnails").getJSONObject("medium")

                        val videoId = jsonVideoId.getString("videoId")
                        val title = jsonObjectSnippet.getString("title")
                        val description = jsonObjectSnippet.getString("description")
                        val url = jsonObjectDefault.getString("url")

                        val vd = VideoDetails(videoId,title,description,url)

                        videoDetailsArrayList.add(vd)

                    }
                    print(videoDetailsArrayList.size)

                    val list_view = binding.ListView
                    val adapter = MyCustomAdapter(this,videoDetailsArrayList)
                    list_view.adapter = adapter

                }catch (e: JSONException){
                    e.printStackTrace()
                }

                Toast.makeText(this, "Response is: ${response.substring(0, 500)}", Toast.LENGTH_SHORT).show()
            },
            { Toast.makeText(this, "That didn't work! : " + it.message , Toast.LENGTH_SHORT).show()})

        queue.add(stringRequest)
    }
}