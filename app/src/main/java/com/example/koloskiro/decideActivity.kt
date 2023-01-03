package com.example.koloskiro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import java.util.*

class decideActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_decide)

        val db = Firebase.firestore
        var Object = intent.getSerializableExtra("Object") as RentData
        var deviceType = intent.getSerializableExtra("deviceType") as String
        var idOfRent = intent.getSerializableExtra("idOfRent") as String


        val emailtxt = findViewById<TextView>(R.id.ClientEmail) as TextView
        val deviteText = findViewById<TextView>(R.id.DeviceType) as TextView
        val AcceptButton = findViewById<Button>(R.id.acceptButton) as Button
        val declineButton = findViewById<Button>(R.id.declineButton) as Button


        emailtxt.setText(Object.ClientID.toString())
        deviteText.setText(deviceType)





        AcceptButton.setOnClickListener(){

            val rnds = (1000..9999).random()
            Object.end = "progress"
            Object.pin = rnds.toString()
            Object.toBeConfirmed = false
            val update = db.collection("Rents")
            update.document(idOfRent).set(Object).addOnCompleteListener {

                val docRef = db.collection("KoloSkiro").document(Object.deviceID)
                docRef.get().addOnSuccessListener { documentSnapshot ->
                    val tempKoloSkiro = documentSnapshot.toObject<KoloSkiro>()
                    if (tempKoloSkiro != null) {


                        tempKoloSkiro.isActive = false
                        tempKoloSkiro.inUse = true
                        val update2 = db.collection("KoloSkiro")
                        update2.document(Object.deviceID).set(tempKoloSkiro).addOnCompleteListener {
                            finish()

                        }
                    }

                }
                finish()

            }



        }


        declineButton.setOnClickListener(){



            Object.end = "Declined"
            Object.toBeConfirmed = false
            val update = db.collection("Rents")
            update.document(idOfRent).set(Object).addOnCompleteListener {
                //finish()

            }

            val docRef = db.collection("KoloSkiro").document(Object.deviceID)
            docRef.get().addOnSuccessListener { documentSnapshot ->
                val tempKoloSkiro = documentSnapshot.toObject<KoloSkiro>()
                if (tempKoloSkiro != null) {


                    tempKoloSkiro.isActive = true
                    val update2 = db.collection("KoloSkiro")
                    update2.document(Object.deviceID).set(tempKoloSkiro).addOnCompleteListener {
                        finish()

                    }
                }

                }





        }






    }
}