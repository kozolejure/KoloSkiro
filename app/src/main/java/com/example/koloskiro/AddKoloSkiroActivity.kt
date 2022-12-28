package com.example.koloskiro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


private lateinit var auth: FirebaseAuth

class AddKoloSkiroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val db = Firebase.firestore
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        setContentView(R.layout.activity_add_kolo_skiro)

        val user = FirebaseAuth.getInstance().currentUser
        val email = user?.email.toString()

        val type = findViewById<EditText>(R.id.InputDeviceType) as EditText
        val address = findViewById<EditText>(R.id.inputLocation) as EditText
        val price = findViewById<EditText>(R.id.inputPrice) as EditText
        val termsOfUse = findViewById<EditText>(R.id.InputTermsOfUse) as EditText
        val add = findViewById<Button>(R.id.addButton) as Button





        add.setOnClickListener(){


            if (type.text != null && address.text != null && price.text != null){

                val koloSkiro = db.collection("KoloSkiro")

                val priceValue: Double = price.getText().toString().toDouble()

                var koloSkiroObject = KoloSkiro(
                    type = type.text.toString(),
                    address = address.text.toString(),
                    owner = email,
                    TermsOfUse = termsOfUse.text.toString(),
                    price = priceValue
                )


                koloSkiro.document().set(koloSkiroObject).addOnCompleteListener {

                    val intent = Intent(this,ProviderHomeActivity::class.java)
                    startActivity(intent)




                }

                }

            else{


                //TODO
                //nekaj je šlo narobe




            }




        }













    }
}