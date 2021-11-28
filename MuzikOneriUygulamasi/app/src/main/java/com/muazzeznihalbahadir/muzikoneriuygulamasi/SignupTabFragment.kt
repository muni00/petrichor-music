package com.muazzeznihalbahadir.muzikoneriuygulamasi

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.muazzeznihalbahadir.muzikoneriuygulamasi.databinding.FragmentSignupTabBinding
import android.util.Log
import android.widget.Toast


class SignupTabFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentSignupTabBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        initVariabe(inflater)

        binding.buttonSignup.setOnClickListener {
            kayitOl()
        }

        return binding.root
    }

    private fun initVariabe(inflater: LayoutInflater) {
        binding = FragmentSignupTabBinding.inflate(inflater)
        auth = FirebaseAuth.getInstance()
    }


    fun kayitOl() {
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.e("signup", "success")
                Log.e("signup", "$email $password ${task.isSuccessful}")
                val intent = Intent(context, LoginActivity::class.java)
                startActivity(intent)

            }

        }.addOnFailureListener { exception ->
            Log.e("signup", "${exception.printStackTrace()}")
            Toast.makeText(context, exception.localizedMessage, Toast.LENGTH_LONG).show()
        }

    }

}