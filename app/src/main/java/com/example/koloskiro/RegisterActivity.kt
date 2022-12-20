package com.example.koloskiro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val nameInput = findViewById<EditText>(R.id.name) as EditText
        val surnameInput = findViewById<EditText>(R.id.surname) as EditText
        val addresInput = findViewById<EditText>(R.id.naslov) as EditText
        val hosteCheckbox = findViewById<CheckBox>(R.id.checkBox) as CheckBox
        val passwordInputOne = findViewById<EditText>(R.id.passwordOne)as EditText
        val passwordInputTwo = findViewById<EditText>(R.id.passwordTwo) as EditText
        var emailInput = findViewById<EditText>(R.id.email) as EditText
        var paswordNotSametext = findViewById<TextView>(R.id.notSamePasswords) as TextView
        var registorButton = findViewById<Button>(R.id.registerbutton) as Button
    }
}