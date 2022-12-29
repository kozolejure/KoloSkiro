package com.example.koloskiro

import android.content.ContentValues.TAG
import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.koloskiro.databinding.KoloSkiroProviderItemBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase


private lateinit var auth: FirebaseAuth



class ProviderHomeActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {

        val IDS = ArrayList<String>()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider_home)
        val db = Firebase.firestore
        val user = FirebaseAuth.getInstance().currentUser
        val email = user?.email.toString()
        val refresh = findViewById<ImageButton>(R.id.refreshButton) as ImageButton

        //val editButton = findViewById<Button>(KoloSkiroProviderItemBinding.id.editButton) as Button


        setContentView(R.layout.activity_provider_home)

       // KoloSkiroProviderItemBinding.
        fun getKoloSkiro():List<KoloSkiro>{
            val myKoloSkiro = ArrayList<KoloSkiro>()
            db.collection("KoloSkiro")
                .whereEqualTo("owner", email)
                .addSnapshotListener { value, e ->

                    if (e != null) {
                        Log.w(TAG, "Listen failed.", e)
                        return@addSnapshotListener

                    }
                    IDS.clear()
                    for (doc in value!!) {
                        doc.toObject<KoloSkiro>()?.let {


                            myKoloSkiro.add(it)
                            IDS.add(doc.id)

                           // myKoloSkiro = getKoloSkiro() as ArrayList<KoloSkiro>

                        }

                    }

                }



            return myKoloSkiro

        }


        refresh.setOnClickListener(){



            var myKoloSkiro = getKoloSkiro() as ArrayList<KoloSkiro>
            val list = findViewById<ListView>(R.id.listViewAdmin) as ListView

        }


        var myKoloSkiro = getKoloSkiro() as ArrayList<KoloSkiro>


       // binding.listViewAdmin.isClickable = true
        val list = findViewById<ListView>(R.id.listViewAdmin) as ListView



        Thread.sleep(10)
        list.adapter = MyAdapter(this,myKoloSkiro)
        list.isClickable = true



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


        //val email = findViewById<EditText>(R.id.emainForgoten) as EditText
        val buttonAddKoloSkiro = findViewById<Button>(R.id.addKoloSkiro) as Button

        buttonAddKoloSkiro.setOnClickListener(){
            val intent = Intent(this,AddKoloSkiroActivity::class.java)
            startActivity(intent)
            finish()
        }






    }
}








