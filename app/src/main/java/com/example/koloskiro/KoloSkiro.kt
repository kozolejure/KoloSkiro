package com.example.koloskiro

import java.io.Serializable

class KoloSkiro(
    var type: String,
    var price: String,
    var owner: String,
    var address: String,
    var TermsOfUse: String,
    var isActive : Boolean): Serializable {

    constructor() : this("","","","","",false){
    }
}