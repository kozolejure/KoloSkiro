package com.example.koloskiro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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


            val current = LocalDateTime.now()
            var dateString = current.dayOfMonth.toString()+"."+current.monthValue.toString()+"."+current.year
            val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("d.M.u")
            var dateString2 = LocalDate.parse(dateString, dateFormatter)
            Rent.end = dateString2.toString()


                    KoloSkiro.inUse = false
                    KoloSkiro.isActive = true
                    Rent.counting = false


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