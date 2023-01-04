package com.example.koloskiro

import java.io.Serializable

class KoloSkiro(
    var myID: String,
    var type: String,
    var price: String,
    var owner: String,
    var address: String,
    var TermsOfUse: String,
    var isActive : Boolean,
    var inUse : Boolean): Serializable {

    constructor() : this("","","","","","",false,false){
    }
}