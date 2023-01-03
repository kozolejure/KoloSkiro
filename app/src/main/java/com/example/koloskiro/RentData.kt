package com.example.koloskiro

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.Serializable
import java.time.LocalDateTime
import java.time.LocalTime

class RentData(
    var myID : String,
    var deviceID: String,
    var ClientID: String,
    var start: String,
    var end: String,
    var toBeConfirmed: Boolean,
    var pin:String ): Serializable {



    @RequiresApi(Build.VERSION_CODES.O)
    constructor() : this(
        "",
        "",
        "",
        "",
        "",
        true,
        ""
        ){
    }





}