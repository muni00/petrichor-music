package com.muazzeznihalbahadir.my_music_app.Login

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.muazzeznihalbahadir.my_music_app.R
import com.muazzeznihalbahadir.my_music_app.View.MainActivity
import com.muazzeznihalbahadir.my_music_app.databinding.FragmentLoginTabBinding

class LoginTabFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentLoginTabBinding
    private  lateinit var firestore : FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        initVariabe(inflater)

        val guncelKullanici = auth.currentUser

        if(guncelKullanici != null){

            val guncelKullaniciEmail = auth.currentUser?.email.toString()
            val guncelKullaniciId = auth.currentUser?.uid.toString()
            //firestore.collection("user").document(guncelKullaniciId).collection("favourite").document("title").set("title")

            Toast.makeText(context,"Hoşgeldin : ${guncelKullaniciEmail}",Toast.LENGTH_LONG).show()

            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }

        binding.buttonLogin.setOnClickListener {
            girisYap()
        }
        binding.txtFgtPss.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Şifremi Unuttum")
            val view = layoutInflater.inflate(R.layout.forgot_pass_dialog,null)
            val userName = view.findViewById<EditText>(R.id.sifremiUnuttumDialog)
            builder.setView(view)
            builder.setPositiveButton("Değiştir",DialogInterface.OnClickListener{_,_->
                sifremiUnuttum(userName)
            })
            builder.setNegativeButton("Vazgeç",DialogInterface.OnClickListener{_,_->

            })
            builder.show()
        }

        return binding.root
    }
    private fun initVariabe(inflater: LayoutInflater) {
        binding = FragmentLoginTabBinding.inflate(inflater)
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

    }

    fun sifremiUnuttum(userName:EditText){
        if (userName.text.toString().isEmpty()){
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(userName.text.toString()).matches()){
            return
        }
        auth.sendPasswordResetEmail(userName.text.toString()).addOnCompleteListener {
            task -> if (task.isSuccessful){
                Toast.makeText(context,"Email adresinizi kontrol ediniz.",Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun girisYap(){
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if (task.isSuccessful){

                val guncelKullaniciEmail = auth.currentUser?.email.toString()
                Toast.makeText(context,"Hoşgeldin : ${guncelKullaniciEmail}",Toast.LENGTH_LONG).show()


                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)

            }
        }.addOnFailureListener { exception ->
            Toast.makeText(context,exception.localizedMessage,Toast.LENGTH_LONG).show()
        }

    }

}