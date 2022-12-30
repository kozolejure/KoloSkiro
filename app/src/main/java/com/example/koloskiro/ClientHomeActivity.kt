package com.example.koloskiro

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ListView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class ClientHomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_home)
        val list = findViewById<ListView>(R.id.clientListView) as ListView


        val IDS = ArrayList<String>()
        val db = Firebase.firestore
        val user = FirebaseAuth.getInstance().currentUser
        val email = user?.email.toString()
        val refresh = findViewById<ImageButton>(R.id.ClientRefreshList) as ImageButton



        fun getKoloSkiro():List<KoloSkiro>{
            val myKoloSkiro = ArrayList<KoloSkiro>()
            db.collection("KoloSkiro")
                .whereEqualTo("active", true)
                .addSnapshotListener { value, e ->

                    if (e != null) {
                        Log.w(ContentValues.TAG, "Listen failed.", e)
                        return@addSnapshotListener

                    }
                    IDS.clear()
                    for (doc in value!!) {
                        doc.toObject<KoloSkiro>()?.let {

                            myKoloSkiro.add(it)
                            IDS.add(doc.id)

                        }
                    }
                }
            return myKoloSkiro

        }

        var myKoloSkiro = getKoloSkiro() as ArrayList<KoloSkiro>

        Thread.sleep(10)
        list.adapter = MyAdapter(this,myKoloSkiro)
        list.isClickable = true





        refresh.setOnClickListener(){


            var myKoloSkiro = getKoloSkiro() as ArrayList<KoloSkiro>
            val list = findViewById<ListView>(R.id.clientListView) as ListView
            Thread.sleep(100)
            list.adapter = MyAdapter(this,myKoloSkiro)


        }


        list.setOnItemClickListener{ list, v, pos, id ->




            val idCurent = IDS[pos]
            var clickedItem = KoloSkiro()

            val docRef = db.collection("KoloSkiro").document(idCurent)
            docRef.get().addOnSuccessListener { documentSnapshot ->
                val tempKoloSkiro = documentSnapshot.toObject<KoloSkiro>()
                if (tempKoloSkiro != null) {

                    val intent = Intent(this,RentActivity::class.java)
                    intent.putExtra("Object",tempKoloSkiro)
                    intent.putExtra("IDI",idCurent)
                    intent.putExtra("email",email)
                    startActivity(intent)


                }

            }


        }

    }
}