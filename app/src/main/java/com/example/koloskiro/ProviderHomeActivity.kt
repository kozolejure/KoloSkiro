package com.example.koloskiro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ProviderHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider_home)

        //val email = findViewById<EditText>(R.id.emainForgoten) as EditText
        val buttonAddKoloSkiro = findViewById<Button>(R.id.addKoloSkiro) as Button

        buttonAddKoloSkiro.setOnClickListener(){
            val intent = Intent(this,AddKoloSkiroActivity::class.java)
            startActivity(intent)
        }



    }
}