package com.example.koloskiro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class getPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_password)

        val TextDeviceName = findViewById<TextView>(R.id.TextDevice) as TextView
        val ProviderEmail = findViewById<TextView>(R.id.TextEmail) as TextView

        val button = findViewById<Button>(R.id.buttonNext) as Button



        var Rent = intent.getSerializableExtra("rentObject") as RentData
        val db = Firebase.firestore


        if (Rent.counting){

            val intent = Intent(this,CounterActivity::class.java)
            intent.putExtra("rentObject",Rent)
            startActivity(intent)

        }



        val docRef = db.collection("KoloSkiro").document(Rent.deviceID)
        docRef.get().addOnSuccessListener { documentSnapshot ->
            val tempKoloSkiro = documentSnapshot.toObject<KoloSkiro>()
            if (tempKoloSkiro != null) {



                ProviderEmail.setText(tempKoloSkiro.owner)
                TextDeviceName.setText(tempKoloSkiro.type)



            }

        }



        val  E1 = findViewById<EditText>(R.id.editText1) as EditText
        val  E2 = findViewById<EditText>(R.id.editText2) as EditText
        val  E3 = findViewById<EditText>(R.id.editText3) as EditText
        val  E4 = findViewById<EditText>(R.id.editText4) as EditText




        E1.setText(Rent.pin.elementAt(0).toString())
        E2.setText(Rent.pin.elementAt(1).toString())
        E3.setText(Rent.pin.elementAt(2).toString())
        E4.setText(Rent.pin.elementAt(3).toString())



        button.setOnClickListener(){


            Rent.counting = true


            val update = db.collection("Rents")
            update.document(Rent.myID).set(Rent).addOnCompleteListener {

                val intent = Intent(this,CounterActivity::class.java)
                intent.putExtra("rentObject",Rent)
                startActivity(intent)
                finish()


            }









        }











    }
}