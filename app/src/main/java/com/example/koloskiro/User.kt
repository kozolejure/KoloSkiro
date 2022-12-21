package com.example.koloskiro

class User(
    val name : String,
    val surname : String,
    val email : String,
    val address : String,
    var isProvider: Boolean){

        constructor() : this("","","","",true){
    }



}
