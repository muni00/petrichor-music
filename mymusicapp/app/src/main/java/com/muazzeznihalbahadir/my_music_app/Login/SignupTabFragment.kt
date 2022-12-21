package com.muazzeznihalbahadir.my_music_app.Login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import com.muazzeznihalbahadir.my_music_app.databinding.FragmentSignupTabBinding
import android.util.Log
import android.widget.Toast
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class SignupTabFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentSignupTabBinding
    private  lateinit var firestore : FirebaseFirestore

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
        firestore = FirebaseFirestore.getInstance()
    }


    fun kayitOl() {
        val userName = binding.userName.text.toString()
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()
        val passwordrp =binding.passwordRp.text.toString()

        if(password == passwordrp ){

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    val userHashMap = hashMapOf<String,Any>()

                    userHashMap.put("userName",userName)
                    userHashMap.put("userEmail",email)

                    firestore.collection("user").add(userHashMap).addOnCompleteListener { itask ->
                        if(itask.isSuccessful){
                            val intent = Intent(context, LoginActivity::class.java)
                            startActivity(intent)

                        }
                    }.addOnFailureListener { exception ->
                        Toast.makeText(context,exception.localizedMessage,Toast.LENGTH_LONG).show()
                    }



                }

            }.addOnFailureListener { exception ->
                Log.e("signup", "${exception.printStackTrace()}")
                Toast.makeText(context, exception.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(context,"Şifre uyuşmazlığı, lütfen tekrar giriniz!",Toast.LENGTH_LONG).show()

        }


    }


}