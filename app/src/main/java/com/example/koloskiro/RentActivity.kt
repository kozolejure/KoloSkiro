package com.example.koloskiro

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime
import java.util.*

class RentActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rent)
        var IDI =""
        var myObject = KoloSkiro()
        var email = ""
        val rentAdd = findViewById<Button>(R.id.wantToRent) as Button
        val db = Firebase.firestore


        myObject = intent.getSerializableExtra("Object") as KoloSkiro
        IDI = intent.getSerializableExtra("IDI") as String
        email = intent.getStringExtra("email") as String


        val type = findViewById<TextView>(R.id.modelValue) as TextView
        val price = findViewById<TextView>(R.id.priceValue) as TextView
        val termsOfUse = findViewById<TextView>(R.id.termsOfUseValue) as TextView
        val emailText = findViewById<TextView>(R.id.emailValue) as TextView





        termsOfUse.setText(" "+myObject.TermsOfUse)
        price.setText(" "+myObject.price+" €/h")
        type.setText(" "+myObject.type)
        emailText.setText(" "+email)


        rentAdd.setOnClickListener(){


           myObject.isActive = false


            val update = db.collection("KoloSkiro")
            update.document(IDI).set(myObject).addOnCompleteListener {

            }



            val koloSkiro = db.collection("Rents")

            val current = LocalDateTime.now()
            var dateInString = current.year.toString()+"-"
            dateInString = dateInString + current.monthValue.toString()+"-"
            dateInString = dateInString + current.dayOfMonth.toString()+" "
            dateInString = dateInString + current.hour.toString()+":"
            dateInString = dateInString + current.minute.toString()

            var uniqueID = UUID.randomUUID().toString()

            var RentObject = RentData(uniqueID,IDI,email,dateInString, "null",true,"")

            koloSkiro.document(uniqueID).set(RentObject).addOnCompleteListener {
                val intent = Intent(this,ClientHomeActivity::class.java)
                startActivity(intent)


            }












        }











    }
}