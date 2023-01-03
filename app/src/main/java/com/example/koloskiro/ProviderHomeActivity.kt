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
        var RentDateList = ArrayList<RentData>()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider_home)
        val db = Firebase.firestore
        val user = FirebaseAuth.getInstance().currentUser
        val email = user?.email.toString()





        setContentView(R.layout.activity_provider_home)





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
        fun getToBeAccepted():List<RentData>{
            val RentDateList = ArrayList<RentData>()
            db.collection("Rents")
                .whereEqualTo("toBeConfirmed", true)
                .addSnapshotListener { value, e ->

                    if (e != null) {
                        Log.w(TAG, "Listen failed.", e)
                        return@addSnapshotListener

                    }
                    IDS.clear()
                    for (doc in value!!) {
                        doc.toObject<RentData>()?.let {

                            RentDateList.add(it)

                        }

                    }

                }

            return RentDateList
        }
        RentDateList = getToBeAccepted() as ArrayList<RentData>

        val refresh = findViewById<ImageButton>(R.id.refreshButton) as ImageButton

        refresh.setOnClickListener(){

            Log.i(TAG,databaseList().count().toString())


            Log.i(TAG,RentDateList.count().toString())

            var myKoloSkiro = getKoloSkiro() as ArrayList<KoloSkiro>
            val list = findViewById<ListView>(R.id.listViewAdmin) as ListView
            Thread.sleep(100)
            list.adapter = MyAdapter(this,myKoloSkiro)


        }

        var myKoloSkiro = getKoloSkiro() as ArrayList<KoloSkiro>

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


        val buttonAddKoloSkiro = findViewById<Button>(R.id.addKoloSkiro) as Button

        buttonAddKoloSkiro.setOnClickListener(){
            getKoloSkiro()
            val intent = Intent(this,AddKoloSkiroActivity::class.java)
            startActivity(intent)
            finish()
        }





        val buutonOffer = findViewById<Button>(R.id.IzposojeButton) as Button
        buutonOffer.setOnClickListener(){


        if (RentDateList.isEmpty()){
            RentDateList = getToBeAccepted() as ArrayList<RentData>

        }

        else{

           var tempRent =  RentDateList.first()

            val docRef = db.collection("KoloSkiro").document(tempRent.deviceID)
            docRef.get().addOnSuccessListener { documentSnapshot ->
                val tempUser = documentSnapshot.toObject<KoloSkiro>()
                Thread.sleep(20)

                if (tempUser != null) {


                    val intent = Intent(this,decideActivity::class.java)
                    intent.putExtra("Object",tempRent)
                    intent.putExtra("deviceType",tempUser.type)
                    intent.putExtra("idOfRent", tempRent.myID)
                    startActivity(intent)


                }

            }
        }

    }


    }
}








