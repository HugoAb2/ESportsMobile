package com.example.esportsmobile.model

import java.io.Serializable

data class User(
    var name : String,
    var age : Int,
    var country : String,
    var email : String,
    var password : String,
): Serializable
