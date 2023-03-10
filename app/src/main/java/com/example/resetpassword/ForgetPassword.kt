package com.example.resetpassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ForgetPassword : AppCompatActivity() {

    lateinit var email:EditText
    lateinit var btn:Button
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        email=findViewById(R.id.edtforgetId)
        btn=findViewById(R.id.btnreset)

        auth=FirebaseAuth.getInstance()

        btn.setOnClickListener() {
            val s = email.text.toString().trim()

            auth.sendPasswordResetEmail(s)
                .addOnSuccessListener {
                    Toast.makeText(this, "Please check your email", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
                }
        }
    }
}