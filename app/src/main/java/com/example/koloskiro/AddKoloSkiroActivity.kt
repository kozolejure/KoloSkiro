package com.example.koloskiro

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*


private lateinit var auth: FirebaseAuth

class AddKoloSkiroActivity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        val db = Firebase.firestore
        val intent = intent
        var isEdit = false
        var IDI =""

        var myObject = KoloSkiro()

            try {
                 myObject = intent.getSerializableExtra("Object") as KoloSkiro
                 IDI = intent.getSerializableExtra("IDI") as String
            }
            catch (ex :Exception){

                myObject.owner = "null"

            }

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
        val active = findViewById<CheckBox>(R.id.IsActive) as CheckBox
        val inusetxt = findViewById<TextView>(R.id.InUseText) as TextView

        if(myObject.owner !="null"){

            address.setText(myObject.address)
            price.setText(myObject.price)
            type.setText(myObject.type)
            termsOfUse.setText(myObject.TermsOfUse)
            active.isChecked = myObject.isActive
            isEdit = true
            add.setText("Uredi")






        }



        add.setOnClickListener(){


            if(isEdit){

                if (type.text != null && address.text != null && price.text != null){
                    val koloSkiro = db.collection("KoloSkiro")
                    val priceValue: Double = price.getText().toString().toDouble()
                    var koloSkiroObject = KoloSkiro(
                        myID = myObject.myID,
                        type = type.text.toString(),
                        address = address.text.toString(),
                        owner = email,
                        TermsOfUse = termsOfUse.text.toString(),
                        price = priceValue.toString(),
                        isActive = active.isChecked,
                        inUse = myObject.inUse   )


                    if(myObject.inUse){

                        inusetxt.visibility = View.VISIBLE

                    }


                    koloSkiro.document(IDI).set(koloSkiroObject).addOnCompleteListener {
                        val intent = Intent(this,ProviderHomeActivity::class.java)
                        startActivity(intent)
                        finish()

                    }

                }

                else{
                    //TODO
                    //nekaj je šlo narobe
                }


            }

            else{


                if (type.text != null && address.text != null && price.text != null){
                    var uniqueID = UUID.randomUUID().toString()
                    val koloSkiro = db.collection("KoloSkiro")
                    val priceValue: Double = price.getText().toString().toDouble()
                    var koloSkiroObject = KoloSkiro(
                        myID =uniqueID,
                        type = type.text.toString(),
                        address = address.text.toString(),
                        owner = email,
                        TermsOfUse = termsOfUse.text.toString(),
                        price = priceValue.toString(),
                        isActive = active.isChecked,
                        inUse = false)


                    koloSkiro.document(uniqueID).set(koloSkiroObject).addOnCompleteListener {
                        val intent = Intent(this,ProviderHomeActivity::class.java)
                        startActivity(intent)
                        finish()

                    }

                }

                else{
                    //TODO
                    //nekaj je šlo narobe
                }
            }
        }













    }
}