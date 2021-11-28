package com.muazzeznihalbahadir.muzikoneriuygulamasi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.muazzeznihalbahadir.muzikoneriuygulamasi.databinding.ActivityLoginBinding
import com.muazzeznihalbahadir.muzikoneriuygulamasi.databinding.FragmentTransitionTabBinding
import kotlinx.android.synthetic.main.fragment_transition_tab.*

class TransitionTabFragment : Fragment() {


    private lateinit var binding : FragmentTransitionTabBinding
    lateinit var mView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mView=inflater.inflate(R.layout.fragment_transition_tab, container, false)
        mView.setOnClickListener {

            val intent=Intent(activity,LoginActivity::class.java)
            startActivity(intent)
        }

        return mView
    }



}