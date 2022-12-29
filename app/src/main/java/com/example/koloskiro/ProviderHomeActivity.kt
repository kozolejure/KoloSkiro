package com.example.koloskiro

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.koloskiro.databinding.KoloSkiroProviderItemBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase


private lateinit var auth: FirebaseAuth



class ProviderHomeActivity : AppCompatActivity() {

    private lateinit var binding: KoloSkiroProviderItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provider_home)
        val db = Firebase.firestore
        val user = FirebaseAuth.getInstance().currentUser
        val email = user?.email.toString()
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

                    for (doc in value!!) {
                        doc.toObject<KoloSkiro>()?.let {


                            myKoloSkiro.add(it)
                           // myKoloSkiro = getKoloSkiro() as ArrayList<KoloSkiro>

                        }

                    }

                }



            return myKoloSkiro

        }


        var myKoloSkiro = getKoloSkiro() as ArrayList<KoloSkiro>


       // binding.listViewAdmin.isClickable = true
        val list = findViewById<ListView>(R.id.listViewAdmin) as ListView



        Thread.sleep(10)
        list.adapter = MyAdapter(this,myKoloSkiro)
        list.isClickable = true







        list.setOnItemClickListener(OnItemClickListener { list, v, pos, id ->

            Log.i(TAG,"Hellos")

        })














        //val email = findViewById<EditText>(R.id.emainForgoten) as EditText
        val buttonAddKoloSkiro = findViewById<Button>(R.id.addKoloSkiro) as Button

        buttonAddKoloSkiro.setOnClickListener(){
            val intent = Intent(this,AddKoloSkiroActivity::class.java)
            startActivity(intent)
            finish()
        }






    }
}








