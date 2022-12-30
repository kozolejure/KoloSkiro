package com.example.koloskiro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class RentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var IDI =""
        var myObject = KoloSkiro()
        var email = ""


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







    }
}