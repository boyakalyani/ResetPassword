package com.example.resetpassword


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var btn_sign_up:Button
    lateinit var et_password:EditText
    lateinit var et_username:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = FirebaseAuth.getInstance()
        btn_sign_up=findViewById(R.id.btn_sign_up)
        et_username=findViewById(R.id.et_username)
        et_password=findViewById(R.id.et_password)

        btn_sign_up.setOnClickListener {
            signUpUser()
        }

    }

    private fun signUpUser() {
        if (et_username.text.toString().isEmpty()) {
            et_username.error = "Please enter email"
            et_username.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(et_username.text.toString()).matches()) {
            et_username.error = "Please enter valid email"
            et_username.requestFocus()
            return
        }

        if (et_password.text.toString().isEmpty()) {
            et_password.error = "Please enter password"
            et_password.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(et_username.text.toString(), et_password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val user = auth.currentUser
                    user?.sendEmailVerification()
                        ?.addOnCompleteListener { task ->
//                            if (task.isSuccessful) {
//                                startActivity(Intent(this, LoginActivity::class.java))
//                                finish()
//                            }
                            Toast.makeText(this, "please verify your email", Toast.LENGTH_SHORT).show()
                        }
                    val verification=auth.currentUser?.isEmailVerified
                    if (verification==true){
                        val user = auth.currentUser
                        if (user != null) {
                            Toast.makeText(this,"Email Verified",Toast.LENGTH_LONG).show()

                            updateUI(user)
                        }
                    }else {
                        Toast.makeText(this, "Please verify your email", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(
                        baseContext, "Sign Up failed. Try again after some time.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun updateUI(user: FirebaseUser) {

    }
}

//package com.example.resetpassword
//
//
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import android.util.Patterns
//import android.widget.Button
//import android.widget.EditText
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.google.firebase.auth.FirebaseAuth
//
//class SignUpActivity : AppCompatActivity() {
//
//    private lateinit var auth: FirebaseAuth
//    lateinit var btn_sign_up:Button
//    lateinit var et_password:EditText
//    lateinit var et_username:EditText
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_sign_up)
//        auth = FirebaseAuth.getInstance()
//        btn_sign_up=findViewById(R.id.btn_sign_up)
//        et_username=findViewById(R.id.et_username)
//        et_password=findViewById(R.id.et_password)
//
//        btn_sign_up.setOnClickListener {
//            signUpUser()
//        }
//
//    }
//
//    private fun signUpUser() {
//        if (et_username.text.toString().isEmpty()) {
//            et_username.error = "Please enter email"
//            et_username.requestFocus()
//            return
//        }
//
//        if (!Patterns.EMAIL_ADDRESS.matcher(et_username.text.toString()).matches()) {
//            et_username.error = "Please enter valid email"
//            et_username.requestFocus()
//            return
//        }
//
//        if (et_password.text.toString().isEmpty()) {
//            et_password.error = "Please enter password"
//            et_password.requestFocus()
//            return
//        }
//
//        auth.createUserWithEmailAndPassword(et_username.text.toString(), et_password.text.toString())
//            .addOnCompleteListener(this) { task ->
//                if (task.isSuccessful) {
//                    val user = auth.currentUser
//                    user?.sendEmailVerification()
//                        ?.addOnCompleteListener { task ->
//                            if (task.isSuccessful) {
//                                startActivity(Intent(this, LoginActivity::class.java))
//                                finish()
//                            }
//                        }
//                } else {
//                    Toast.makeText(
//                        baseContext, "Sign Up failed. Try again after some time.",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//    }
//}