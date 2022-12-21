package com.example.koloskiro

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


private lateinit var auth: FirebaseAuth

private var numOfFails = 0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)
        //setup
        auth = Firebase.auth
        val registerClicked = findViewById<Button>(R.id.register) as Button
        val usernameText = findViewById<EditText>(R.id.userName) as EditText
        val paswordText = findViewById<EditText>(R.id.password) as EditText
        val loginButton = findViewById<Button>(R.id.loginButton) as Button
        var fogotpassword = findViewById<TextView>(R.id.passwordReset) as TextView
        val text = "<u>Pozabljeno geslo?</u>"
        fogotpassword.setText(Html.fromHtml(text))

        //end of setup

        registerClicked.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)

        }

        fogotpassword.setOnClickListener {

            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)


        }



        loginButton.setOnClickListener {
            val password = paswordText.text.toString()
            val email = usernameText.text.toString()


            if (password.isEmpty() or email.isEmpty()) {

                Toast.makeText(
                    this@MainActivity,
                    "Polja ne smejo biti prazna ",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                        } else {

                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext, "Poskusi ponovno",
                                Toast.LENGTH_SHORT
                            ).show()

                            numOfFails += 1
                            if (numOfFails >= 2) {

                                fogotpassword.visibility = View.VISIBLE

                            }
                            //updateUI(null)
                        }
                    }

            }
        }

    }
}























