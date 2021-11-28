package com.muazzeznihalbahadir.muzikoneriuygulamasi

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.muazzeznihalbahadir.muzikoneriuygulamasi.databinding.FragmentLoginTabBinding

class LoginTabFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentLoginTabBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        initVariabe(inflater)

        binding.buttonLogin.setOnClickListener {
            girisYap()
        }

        return binding.root
    }
    private fun initVariabe(inflater: LayoutInflater) {
        binding = FragmentLoginTabBinding.inflate(inflater)
        auth = FirebaseAuth.getInstance()
    }

    fun girisYap(){
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if (task.isSuccessful){

                val guncelKullanici = auth.currentUser?.email.toString()
                Toast.makeText(context,"Hoşgeldin : ${guncelKullanici}",Toast.LENGTH_LONG).show()

                val intent = Intent(context,MainActivity::class.java)
                startActivity(intent)

            }
        }.addOnFailureListener { exception ->
            Toast.makeText(context,exception.localizedMessage,Toast.LENGTH_LONG).show()
        }

    }


}