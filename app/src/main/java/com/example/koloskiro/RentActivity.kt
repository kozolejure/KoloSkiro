package com.example.koloskiro

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime
import java.time.LocalTime

class RentActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var IDI =""
        var myObject = KoloSkiro()
        var email = ""
        val rent = findViewById<Button>(R.id.rentButton) as Button
        val db = Firebase.firestore






        setContentView(R.layout.activity_rent)

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


        rent.setOnClickListener(){


            val koloSkiro = db.collection("Rents")

            val current = LocalDateTime.now()
            var RentObject = RentData(IDI,email,current, LocalDateTime.of(2000, 9, 10, 6, 40, 45),true)














        }











    }
}