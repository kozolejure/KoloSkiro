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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase


private lateinit var auth: FirebaseAuth
private var numOfFails = 0
private var getUser = User()

class MainActivity : AppCompatActivity() {
    val db = Firebase.firestore

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
        var passNotRight = findViewById<TextView>(R.id.passwordorusernamenot) as TextView


        val text = "<u>Pozabljeno geslo?</u>"
        fogotpassword.setText(Html.fromHtml(text))

        //end of setup

        registerClicked.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)

        }
        //pozabljeno geslo gumb/text
        fogotpassword.setOnClickListener {

            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)


        }



        loginButton.setOnClickListener {
            val password = paswordText.text.toString()
            val email = usernameText.text.toString().toLowerCase()

            //prevelrimo ali je kakšno polje prazno
            if (password.isEmpty() or email.isEmpty()) {

                Toast.makeText(
                    this@MainActivity,
                    "Polja ne smejo biti prazna ",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                //če ni prazno gremo tukaj

                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {



                            val docRef = db.collection("Users").document(email)
                            docRef.get().addOnSuccessListener { documentSnapshot ->
                                val tempUser = documentSnapshot.toObject<User>()
                                if (tempUser != null) {
                                    getUser = tempUser
                                }

                                Toast.makeText(
                                    this@MainActivity,
                                    "Lep pozdrav: "+getUser.name,
                                    Toast.LENGTH_SHORT
                                ).show()


                                if (getUser.isProvider) {
                                    val intent = Intent(this, ProviderHomeActivity::class.java)
                                    startActivity(intent)
                                }
                                else{

                                    val intent = Intent(this, ClientHomeActivity::class.java)
                                    startActivity(intent)


                                }



                            }





                        } else {//nepravilno geslo
                           passNotRight.visibility = View.VISIBLE


                            //Preverjamo ali izpišemo ponastavi geslo
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























