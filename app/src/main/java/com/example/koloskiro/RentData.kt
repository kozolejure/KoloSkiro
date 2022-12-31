package com.example.koloskiro

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.LocalTime

class RentData(
    var deviceID: String,
    var ClientID: String,
    var start: LocalDateTime,
    var end: LocalDateTime,
    var toBeConfirmed: Boolean){



    @RequiresApi(Build.VERSION_CODES.O)
    constructor() : this(
        "",
        "",
        LocalDateTime.of(2000, 9, 10, 6, 40, 45),
        LocalDateTime.of(2014, 9, 10, 6, 40, 45),
        true){
    }



}