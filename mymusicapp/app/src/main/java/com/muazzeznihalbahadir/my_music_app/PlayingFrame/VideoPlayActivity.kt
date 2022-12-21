package com.muazzeznihalbahadir.my_music_app.PlayingFrame

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.muazzeznihalbahadir.my_music_app.Model.VideoDetails
import com.muazzeznihalbahadir.my_music_app.R
import com.muazzeznihalbahadir.my_music_app.Search.FavouriteListActivity
import com.muazzeznihalbahadir.my_music_app.Search.VideoListActivity
import com.muazzeznihalbahadir.my_music_app.View.MainActivity
import com.muazzeznihalbahadir.my_music_app.View.OptionsActivity
import com.muazzeznihalbahadir.my_music_app.View.ProfileSettingsActivity
import com.muazzeznihalbahadir.my_music_app.databinding.ActivityVideoPlayBinding



class VideoPlayActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    var videoID = ""
    var tip = ""
    var title = ""
    var liste = ArrayList<String>()

    private lateinit var binding: ActivityVideoPlayBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityVideoPlayBinding.inflate(layoutInflater)
        videoID = intent.getStringExtra("ID").toString()
        tip = intent.getStringExtra("Tip").toString()
        liste = intent.getStringArrayListExtra("liste") as ArrayList<String>
        title = intent.getStringExtra("Title").toString()

        val intentVideoSearch = Intent(this, VideoListActivity::class.java)
        val intentMain = Intent(this, MainActivity::class.java)
        val intentOption = Intent(this, OptionsActivity::class.java)
        val intentProfileSettingsActivity = Intent(this, ProfileSettingsActivity::class.java)

        val view = binding.root
        setContentView(view)
        binding.txtDescription.text = title

        binding.bottomNavigator.setSelectedItemId(R.id.nav_search);

        binding.bottomNavigator.setOnNavigationItemSelectedListener{


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

        binding.youtubePlayer.initialize(getString(R.string.GOOGLE_API_KEY), this)

    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, youTubePlayer: YouTubePlayer?,
                                         wasRestored: Boolean) {
        Log.d(TAG, "onInitializationSuccess: provider is ${provider?.javaClass}")
        Log.d(TAG, "onInitializationSuccess: youTubePlayer is ${youTubePlayer?.javaClass}")
        //Toast.makeText(this, "Initialized Youtube Player successfully", Toast.LENGTH_SHORT).show()

        youTubePlayer?.setPlayerStateChangeListener(playerStateChangeListener)
        youTubePlayer?.setPlaybackEventListener(playbackEventListener)

        if (!wasRestored) {
            if (tip == "playlist")
            {youTubePlayer?.loadPlaylist(videoID)}
            else if (tip == "listele")
            { youTubePlayer?.loadVideos(liste)}
            else{
                youTubePlayer?.loadVideo(videoID)
            }

            Toast.makeText(this, videoID, Toast.LENGTH_SHORT).show()

        }
    }
    override fun onInitializationFailure(provider: YouTubePlayer.Provider?,
                                         youTubeInitializationResult: YouTubeInitializationResult?) {
        val REQUEST_CODE = 0

        if (youTubeInitializationResult?.isUserRecoverableError == true) {
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show()
        } else {
            val errorMessage = "There was an error initializing the YoutubePlayer ($youTubeInitializationResult)"
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()

        }
    }

    private val playbackEventListener = object: YouTubePlayer.PlaybackEventListener {
        override fun onSeekTo(p0: Int) {
        }

        override fun onBuffering(p0: Boolean) {
        }

        override fun onPlaying() {
            //Toast.makeText(applicationContext, "video oynatılıyor", Toast.LENGTH_SHORT).show()
        }

        override fun onStopped() {

            //Toast.makeText(applicationContext, "video bitti", Toast.LENGTH_SHORT).show()


        }

        override fun onPaused() {
            //Toast.makeText(applicationContext, "video duraklatıldı", Toast.LENGTH_SHORT).show()
        }
    }

    private val playerStateChangeListener = object: YouTubePlayer.PlayerStateChangeListener {
        override fun onAdStarted() {
           // Toast.makeText(applicationContext, "Click Ad now, make the video creator rich!", Toast.LENGTH_SHORT).show()

        }

        override fun onLoading() {

        }

        override fun onVideoStarted() {
          // Toast.makeText(applicationContext, "Video has started", Toast.LENGTH_SHORT).show()
        }

        override fun onLoaded(p0: String?) {
        }

        override fun onVideoEnded() {
            //Toast.makeText(applicationContext, "Congratulations! You've completed another video.", Toast.LENGTH_SHORT).show()

        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {
        }
    }



}