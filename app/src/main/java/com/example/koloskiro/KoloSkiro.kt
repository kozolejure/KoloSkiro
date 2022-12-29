package com.example.koloskiro

class KoloSkiro(
    val type: String,
    val price: String,
    val owner: String,
    val address: String,
    var TermsOfUse: String,
    val isActive : Boolean) {

    constructor() : this("","","","","",false){
    }
}