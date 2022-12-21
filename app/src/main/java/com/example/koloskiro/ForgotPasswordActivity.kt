package com.example.koloskiro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        auth = FirebaseAuth.getInstance()

        val email = findViewById<EditText>(R.id.emainForgoten) as EditText
        val btn = findViewById<Button>(R.id.ponastaviButton) as Button
        btn.setOnClickListener{

            auth.sendPasswordResetEmail(email.text.toString())
                .addOnCompleteListener {
                    Toast.makeText(this@ForgotPasswordActivity,
                        "Geslo je ponastavljeno ",
                        Toast.LENGTH_SHORT).show()

                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)



                }.addOnFailureListener{


                    Toast.makeText(this@ForgotPasswordActivity,
                        "Nekaj je šlo narobe ",
                        Toast.LENGTH_SHORT).show()

                }



        }



    }
}