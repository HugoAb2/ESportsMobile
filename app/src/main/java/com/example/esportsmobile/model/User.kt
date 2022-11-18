package com.example.esportsmobile.model

import java.io.Serializable

data class User(
    val id: String,
    var name : String,
    var age : Int,
    var country : String,
    var email : String,
    var password : String,
    var profile : Int?
): Serializable
