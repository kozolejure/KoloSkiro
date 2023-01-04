package com.example.koloskiro

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
//import com.example.koloskiro.databinding.KoloSkiroProviderItemBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import java.time.LocalTime
import kotlin.math.log


private lateinit var auth: FirebaseAuth



class ProviderHomeActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {

        val IDS = ArrayList<String>()
        val IDSRents = ArrayList<String>()

        var RentDateList = ArrayList<RentData>()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider_home)
        val db = Firebase.firestore
        val user = FirebaseAuth.getInstance().currentUser
        val email = user?.email.toString()





        setContentView(R.layout.activity_provider_home)





        fun getKoloSkiro(){
            val docRef = db.collection("KoloSkiro").whereEqualTo("owner",email)
            docRef.get().addOnSuccessListener { documentSnapshot ->
                val tempKoloSkiro = documentSnapshot.toObjects(KoloSkiro::class.java)
                if (tempKoloSkiro.isNotEmpty()) {

                    if (tempKoloSkiro.isNotEmpty()){

                        val list = findViewById<ListView>(R.id.listViewAdmin) as ListView
                        list.adapter = MyAdapter(this, tempKoloSkiro as ArrayList<KoloSkiro>)
                        list.isClickable = true

                        IDS.clear()
                        for(item in tempKoloSkiro){

                            IDS.add(item.myID)

                        }
                    }
                }

            }

        }

        fun getToBeAccepted(){
            val docRef = db.collection("Rents")
                .whereEqualTo("toBeConfirmed", true)
                .whereEqualTo("ownerID", email)
                docRef.get().addOnSuccessListener { documentSnapshot ->
                val tempRents = documentSnapshot.toObjects(RentData::class.java)
                if (tempRents.isNotEmpty()) {
                        val docRef = db.collection("KoloSkiro").document(tempRents.first().deviceID)
                        docRef.get().addOnSuccessListener { documentSnapshot ->
                            val tempUser = documentSnapshot.toObject<KoloSkiro>()


                            if (tempUser != null) {


                                val intent = Intent(this,decideActivity::class.java)
                                intent.putExtra("Object",tempRents.first())
                                intent.putExtra("deviceType",tempUser.type)
                                intent.putExtra("idOfRent", tempRents.first().myID)
                                startActivity(intent)


                            }

                        }

                    }
                }
            }



        val refresh = findViewById<ImageButton>(R.id.refreshButton) as ImageButton

        refresh.setOnClickListener(){

            getKoloSkiro()


        }

        getKoloSkiro()

        val list = findViewById<ListView>(R.id.listViewAdmin) as ListView
        list.setOnItemClickListener{ list, v, pos, id ->

            val idCurent = IDS[pos]
            var clickedItem = KoloSkiro()

            val docRef = db.collection("KoloSkiro").document(idCurent)
            docRef.get().addOnSuccessListener { documentSnapshot ->
                val tempUser = documentSnapshot.toObject<KoloSkiro>()
                if (tempUser != null) {

                    val intent = Intent(this,AddKoloSkiroActivity::class.java)
                    intent.putExtra("Object",tempUser)
                    intent.putExtra("IDI",idCurent)


                    startActivity(intent)
                    finish()

                }


            }


        }


        val buttonAddKoloSkiro = findViewById<Button>(R.id.addKoloSkiro) as Button
        buttonAddKoloSkiro.setOnClickListener(){
           // getKoloSkiro()
            val intent = Intent(this,AddKoloSkiroActivity::class.java)
            startActivity(intent)
            finish()
        }





        val buutonOffer = findViewById<Button>(R.id.IzposojeButton) as Button
        buutonOffer.setOnClickListener(){

            getToBeAccepted()

         }


    }
}








