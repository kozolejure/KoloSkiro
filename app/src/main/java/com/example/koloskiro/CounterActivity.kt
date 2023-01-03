package com.example.koloskiro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class CounterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = Firebase.firestore
        setContentView(R.layout.activity_counter)

        var KoloSkiro = KoloSkiro()



        var Rent = intent.getSerializableExtra("rentObject") as RentData


        val docRef = db.collection("KoloSkiro").document(Rent.deviceID)
        docRef.get().addOnSuccessListener{ documentSnapshot ->
            val KoloSkiroTemp = documentSnapshot.toObject<KoloSkiro>()
            if (KoloSkiroTemp != null) {

                KoloSkiro = KoloSkiroTemp



            }
        }






        val button = findViewById<Button>(R.id.endRentButton) as Button

        button.setOnClickListener(){


                    KoloSkiro.inUse = false
                    KoloSkiro.isActive = true

                    Rent.counting = false
                    Rent.end = "1.1.1."

                    val update = db.collection("Rents")
                    update.document(Rent.myID).set(Rent).addOnCompleteListener {


                        val update = db.collection("KoloSkiro")
                        update.document(Rent.deviceID).set(KoloSkiro).addOnCompleteListener {


                            //Naslednja stran v tem primeru te vrne nazaj
                            finish()

                        }

                }
            }











    }
}