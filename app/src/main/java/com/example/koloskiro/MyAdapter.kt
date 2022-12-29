package com.example.koloskiro

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MyAdapter(private val context: Activity, private val arrayList :ArrayList<KoloSkiro>): ArrayAdapter<KoloSkiro>(
    context,R.layout.kolo_skiro_provider_item,arrayList){


    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.kolo_skiro_provider_item,null)

        val price : TextView = view.findViewById(R.id.TextPriceCard)
        val location : TextView = view.findViewById(R.id.TextLocationCard)

        price.text = arrayList[position].price.toString()
        location.text =arrayList[position].address.toString()


        return view
    }


}