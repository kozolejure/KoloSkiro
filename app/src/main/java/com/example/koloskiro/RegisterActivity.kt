package com.example.koloskiro

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private lateinit var auth: FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = Firebase.auth
        val db = Firebase.firestore
        val nameInput = findViewById<EditText>(R.id.name) as EditText
        val surnameInput = findViewById<EditText>(R.id.surname) as EditText
        val addresInput = findViewById<EditText>(R.id.naslov) as EditText
        val hosteCheckbox = findViewById<CheckBox>(R.id.checkBox) as CheckBox
        val passwordInputOne = findViewById<EditText>(R.id.passwordOne)as EditText
        val passwordInputTwo = findViewById<EditText>(R.id.passwordTwo) as EditText
        var emailInput = findViewById<EditText>(R.id.email) as EditText
        var paswordNotSametext = findViewById<TextView>(R.id.notSamePasswords) as TextView
        var registorButton = findViewById<Button>(R.id.registerbutton) as Button





        registorButton.setOnClickListener{


            if (nameInput.text.isEmpty() or surnameInput.text.isEmpty() or addresInput.text.isEmpty()
            or hosteCheckbox.text.isEmpty() or passwordInputOne.text.isEmpty() or passwordInputTwo.text.isEmpty()
            or emailInput.text.isEmpty()){

                Toast.makeText(this@RegisterActivity,
                    "Izpolnite vsa polja ",
                    Toast.LENGTH_SHORT).show()
            }

            else if (passwordInputOne.text.toString() != passwordInputTwo.text.toString()){

                paswordNotSametext.visibility = View.VISIBLE


            }
            else{
                var email = emailInput.text.toString()
                var password = passwordInputOne.text.toString()
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                            val user = auth.currentUser

                            Toast.makeText(this@RegisterActivity, "Registracija uspešna", Toast.LENGTH_SHORT).show()


                            val userObject = User(nameInput.text.toString(),surnameInput.text.toString(),
                                emailInput.text.toString(),addresInput.text.toString(),hosteCheckbox.isChecked())



                            db.collection("Users")
                                .add(userObject)
                                .addOnSuccessListener { documentReference ->
                                    Toast.makeText(this@RegisterActivity, "Add", Toast.LENGTH_SHORT).show()
                                }
                                .addOnFailureListener { e ->
                                    Toast.makeText(this@RegisterActivity, "error", Toast.LENGTH_SHORT).show()
                                }




                            val intent = Intent(this,MainActivity::class.java)
                            startActivity(intent)

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Račun že obstaja ali popravi geslo",
                                Toast.LENGTH_SHORT).show()


                            // updateUI(null)
                        }
                    }






            }




        }

    }
}