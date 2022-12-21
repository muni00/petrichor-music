package com.muazzeznihalbahadir.my_music_app.Login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.muazzeznihalbahadir.my_music_app.databinding.FragmentTransitionTabBinding

class TransitionTabFragment : Fragment() {


    private lateinit var binding : FragmentTransitionTabBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentTransitionTabBinding.inflate(layoutInflater)
        binding.ltMusic.animate().setDuration(1000).setStartDelay(4000)
        binding.floatingActionButton.setOnClickListener {

            val intent=Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return binding.root
    }



}