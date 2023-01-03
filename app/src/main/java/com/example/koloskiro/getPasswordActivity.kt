package com.example.koloskiro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class getPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_password)



        var Rent = intent.getSerializableExtra("rentObject") as RentData



        val  E1 = findViewById<EditText>(R.id.editText2) as EditText
        val  E2 = findViewById<EditText>(R.id.editText2) as EditText
        val  E3 = findViewById<EditText>(R.id.editText3) as EditText
        val  E4 = findViewById<EditText>(R.id.editText4) as EditText




        E1.setText(Rent.pin.elementAt(0).toString())











    }
}